package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketResponse {
	
	private String ticketId;
	private String username;
	private Integer quantity;
	private Integer cost;
	private String placeName;
	private String email;
}
