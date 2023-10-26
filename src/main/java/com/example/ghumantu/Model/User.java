package com.example.ghumantu.Model;

import java.time.Instant;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
//	@NotNull(message = "username can't be null")
	private String username;
	
//	@Email
//	@NotNull(message = "email can't be null")
	private String email;
	
//	@NotNull(message = "Password can't be null")
	private String password;
	
	@ManyToMany
	private List<Place> likedPlaces;
	
	@Column
	private Long adharNumber;
	
	@Column 
	private Long phoneNumber;

	
}
