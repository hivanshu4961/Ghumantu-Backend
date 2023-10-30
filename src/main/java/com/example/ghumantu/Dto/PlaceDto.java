package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaceDto {

	private String placeName;
	private String description;
	private String budget;
	private String address;
	private String subCategory;
	private String image;
}
