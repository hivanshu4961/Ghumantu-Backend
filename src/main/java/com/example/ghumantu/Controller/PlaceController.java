package com.example.ghumantu.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghumantu.Dto.PlaceDto;
import com.example.ghumantu.Dto.PlaceResponse;
import com.example.ghumantu.Service.AuthService;
import com.example.ghumantu.Service.PlaceService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
	
	private final PlaceService placeService;
	private final AuthService authService;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody PlaceDto placeDto) throws IOException{
		placeService.save(placeDto);
		return new ResponseEntity<String>("saved",HttpStatus.OK);
	}
	
	@GetMapping("/by-id/{id}")
	public ResponseEntity<PlaceResponse> getPlace(@PathVariable Integer id){
		return new ResponseEntity<PlaceResponse>(placeService.getPlace(id),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PlaceResponse>> getAllPlaces(){
		return new ResponseEntity<List<PlaceResponse>>(placeService.getAllPlaces(),HttpStatus.OK);
	}
	
	@GetMapping("/{state}")
	public ResponseEntity<List<PlaceResponse>> getAllPlacesByState(@PathVariable String state){
		return new ResponseEntity<List<PlaceResponse>>(placeService.getAllPlacesByState(state),HttpStatus.OK);
	}
	
	@PutMapping("/add-to-wishlist/{placeId}")
	public ResponseEntity<String> addToWishlist(@PathVariable Integer placeId){
		return new ResponseEntity<String>(authService.addToWishlist(placeId),HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-from-wishlist/{placeId}")
	public ResponseEntity<String> removeFromWishlist(@PathVariable Integer placeId){
		return new ResponseEntity<String>(authService.removeFromWishlist(placeId),HttpStatus.OK);
	}
}
