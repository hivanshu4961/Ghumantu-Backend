package com.example.ghumantu.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghumantu.Dto.LoginRequest;
import com.example.ghumantu.Dto.googleLoginDto;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Service.AuthService;
import com.example.ghumantu.jwt.JwtResponse;




@RestController
public class OauthController {
	
	@Value("${google.clientId}")
	private String googleClientId;
	
	@Value("${secretPsw}")
	private String secretPsw;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse>Login(@RequestBody googleLoginDto payload) throws Exception{
		User user = new User();
        if(authService.existsEmail(payload.getEmail())) {
        	user = authService.getByEmail(payload.getEmail());
        }
        else {
        	user = authService.saveUser(payload.getEmail(),payload.getUsername());
        }
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(user.getUsername());
        loginRequest.setPassword(secretPsw);
        JwtResponse jwtResponse = authService.login(loginRequest);
        return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.OK);
	}
	
}
