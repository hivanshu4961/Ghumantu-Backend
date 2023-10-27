package com.example.ghumantu.jwt;

import java.time.Instant;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtResponse {
	private String token;
	private String username;
	private Instant expiryTime;
	private Integer userId;
//	private List<Integer> likedPlaces;
}
