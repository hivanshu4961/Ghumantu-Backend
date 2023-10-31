package com.example.ghumantu.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghumantu.Dto.VoteDto;
import com.example.ghumantu.Service.VoteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/place/vote")
public class VoteController {
	
	private final VoteService voteService;
	
	@PostMapping
	public ResponseEntity<String> vote(@RequestBody VoteDto voteDto){
		voteService.vote(voteDto);
		return new ResponseEntity<String>("voted",HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<VoteDto>> likedAndDislikedByUser(){
		return new ResponseEntity<List<VoteDto>>(voteService.likedAndDislikedByUser(),HttpStatus.OK);
	}
}
