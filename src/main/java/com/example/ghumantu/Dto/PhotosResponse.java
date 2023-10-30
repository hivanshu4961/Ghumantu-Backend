package com.example.ghumantu.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotosResponse {
	private Instant createdDate;
	private byte[] image;
	private Integer photoId;
	private String username;
}
