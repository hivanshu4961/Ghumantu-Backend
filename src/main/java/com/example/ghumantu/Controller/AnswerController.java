package com.example.ghumantu.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghumantu.Dto.AnswerDto;
import com.example.ghumantu.Dto.AnswerRes;
import com.example.ghumantu.Service.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/answers")
public class AnswerController {
	
	private final AnswerService answerService;
	
	@PostMapping("/post-answer")
	public ResponseEntity<String> postAnswer(@RequestBody AnswerDto answerDto){
		answerService.postAnswer(answerDto);
		return new ResponseEntity<String>("Posted",HttpStatus.OK);
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<List<AnswerRes>> getAnswers(@PathVariable Integer questionId){
		return new ResponseEntity<List<AnswerRes>>(answerService.getAnswers(questionId),HttpStatus.OK); 
	}
}
