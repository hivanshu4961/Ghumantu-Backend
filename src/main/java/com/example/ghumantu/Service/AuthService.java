package com.example.ghumantu.Service;

import java.time.Instant;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ghumantu.jwt.*;
//import com.example.ghumantu.jwt.*;
import com.example.ghumantu.Dto.LoginRequest;
import com.example.ghumantu.Dto.PlaceResponse;
import com.example.ghumantu.Dto.RegisterRequest;
import com.example.ghumantu.Exception.SpringException;
import com.example.ghumantu.Model.Place;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Repository.PlaceRepository;
import com.example.ghumantu.Repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final MailService mailService;
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtUtil jwtProvider;
	private final AuthenticationManager authenticationManager;
	private final PlaceRepository placeRepository;
	
	public JwtResponse login(LoginRequest loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()->new SpringException("user doesn't exist"));
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername()); 
		String token = jwtProvider.generateToken(userDetails);
		return JwtResponse.builder()
				.token(token)
				.username(loginRequest.getUsername())
				.userId(user.getUserId())
				.expiryTime(Instant.now().plusMillis(jwtProvider.getExpirationTime()) )
				.build();
		
	}
	
	private PlaceResponse MapPlaceToPlaceId(Place place) {
		return PlaceResponse.builder()
				.address(place.getAddress())
				.description(place.getDescripton())
				.budget(place.getBudget())
				.disLikes(place.getDisLikes())
				.likes(place.getLikes())
				.image(place.getMain_image())
				.placeId(place.getPlaceId())
				.placeName(place.getPlaceName())
				.build();
	}
	
	@Transactional(readOnly = true)
	public User getCurrentUser() throws SpringException {
		User user = userRepository.findByUsername(getUsername()).orElseThrow(()-> new SpringException("User Not Found!!"));
        
        return user;
	}
	
	public boolean existsEmail(String email) {
		User user = userRepository.getByEmail(email);
		
		if(user == null)return false;
		return true;
	}
	
	public String getUsername() {
		   SecurityContext context = SecurityContextHolder.getContext();
		   Authentication authentication = context.getAuthentication();
		   if (authentication == null)
		     return null;
		   Object principal = authentication.getPrincipal();
		   if (principal instanceof UserDetails) {
		     return ((UserDetails) principal).getUsername();
		   } else {
		     return principal.toString();
		   }
	}

	public User getByEmail(String email) {
		return userRepository.getByEmail(email);
	}
	
	public User saveUser(String email,String username)throws Exception {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setEmail(email);
		registerRequest.setPassword(passwordEncoder.encode("iamgroot"));
		registerRequest.setUsername(username);
		return registerGoogle(registerRequest);
	}
	
	public User registerGoogle(RegisterRequest registerRequest) {
		User newUser = new User();
		newUser.setEmail(registerRequest.getEmail());
		newUser.setPassword(passwordEncoder.encode("iamgroot"));
		newUser.setUsername(registerRequest.getUsername());
		
		userRepository.saveAndFlush(newUser);
		return newUser;
	}
	
	@Transactional
	public String addToWishlist(Integer placeId) {
		User user = getCurrentUser();
		Place place = placeRepository.getById(placeId);
		List<Place> likedPlaces = user.getLikedPlaces();
		
		likedPlaces.add(place);
		user.setLikedPlaces(likedPlaces);
		userRepository.save(user);
	
		
		return "added";
	}
	
	@Transactional
	public String removeFromWishlist(Integer placeId) {
		User user = getCurrentUser();
		Place place = placeRepository.getById(placeId);
		List<Place> likedPlaces = user.getLikedPlaces();
		likedPlaces.remove(place);
		user.setLikedPlaces(likedPlaces);
		
		userRepository.save(user);
		
		return "removed";
	}

	public List<PlaceResponse> getWishlist() {
		return getCurrentUser().getLikedPlaces()
				.stream()
				.map(this::MapPlaceToPlaceId)
				.collect(Collectors.toList());
	}
	
	
}
