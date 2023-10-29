package com.example.ghumantu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ghumantu.Model.Answer;
import com.example.ghumantu.Model.Question;

//import com.PlaceFinder.CollegeProject.Model.Answer;
//import com.PlaceFinder.CollegeProject.Model.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

	List<Answer> getByQuestion(Question question);

}
