package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterRequest {
	
	private String username;
	private String password;
	private String email;
}
