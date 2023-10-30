package com.example.ghumantu.Dto;

//import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PlaceResponse {

	private Integer placeId;
	private Integer likes;
	private Integer disLikes;
	private String placeName;
	private String description;
	private String budget;
	private String address;
	private String subCategory;
	private String image;
}
