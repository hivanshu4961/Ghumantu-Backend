package com.example.ghumantu.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ghumantu.Model.Place;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Model.Vote;



@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer>{

	List<Vote> getByUser(User user);

	Vote getByPlaceAndUser(Place place, User user);

}
