package com.example.ghumantu.Dto;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ReviewDto {
	private String review;
	private Integer placeId;
	private Integer reviewId;
	private String username;
	private Instant createdDate;
}
