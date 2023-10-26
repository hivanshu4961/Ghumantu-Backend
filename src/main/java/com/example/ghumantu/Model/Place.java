package com.example.ghumantu.Model;

//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Data
@Builder
public class Place {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer placeId;
	
//	@NotNull(message = "name cant be null")
	private String placeName;
	
	@Nullable
	private String budget;
	
	@Column(name = "main_image",length = 1000)
	private String main_image;
	
	@Column
	private Integer likes;
	
	@Column
	private Integer disLikes;
	
	@Column(length = 1000)
	private String descripton;
	
//	@NotNull(message = "address is necessary")
	private String address;
	
	@ManyToMany(mappedBy = "likedPlaces")
	private List<User> users;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId", referencedColumnName = "stateId")
	private State state;
	
}
