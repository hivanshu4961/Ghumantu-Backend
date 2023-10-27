package com.example.ghumantu.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghumantu.Dto.PlaceResponse;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/PlaceFinder/auth")
@AllArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@GetMapping("/current-user")
	public ResponseEntity<User> getCurrentUser(){
		return new ResponseEntity<User>(authService.getCurrentUser(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PlaceResponse>> getWishlist(){
		return new ResponseEntity<List<PlaceResponse>>(authService.getWishlist(),HttpStatus.OK);
	}
}
