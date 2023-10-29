package com.example.ghumantu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ghumantu.Model.State;




@Repository
public interface StateRepository extends JpaRepository<State, Integer>{


	State getByStateName(String name);
	
}
