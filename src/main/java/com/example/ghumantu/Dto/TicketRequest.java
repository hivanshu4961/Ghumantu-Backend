package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketRequest {

	private Integer cost;
	private String placeName;
	private Integer quantity;
	private String username;
}
