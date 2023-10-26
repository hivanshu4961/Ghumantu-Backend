package com.example.ghumantu.Model;
import java.util.Arrays;

import com.example.ghumantu.Exception.SpringException;

//import com.PlaceFinder.CollegeProject.Exception.SpringException;


public enum VoteType {
	LIKE(1),DISLIKE(-1),
	;
	
	private Integer type;
	
	VoteType(Integer type){
		
	}
	public static VoteType lookup(Integer type) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new SpringException("Vote not found"));
    }
	public Integer getType() {
		return type;
	}
}
