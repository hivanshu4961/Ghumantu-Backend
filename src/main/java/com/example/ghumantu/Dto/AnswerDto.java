package com.example.ghumantu.Dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnswerDto {
	
	private String answer;
	private Integer questionId;
	private String username;
}
