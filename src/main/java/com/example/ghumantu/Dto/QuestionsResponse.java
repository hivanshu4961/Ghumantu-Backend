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
public class QuestionsResponse {
	private String question;
	private Instant askedDate;
	private Integer questionId;
	private Integer userId;
	private boolean resolved;
	private String username;
}
