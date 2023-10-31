package com.example.ghumantu.Service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ghumantu.Dto.VoteDto;
import com.example.ghumantu.Model.Place;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Model.Vote;
import com.example.ghumantu.Repository.PlaceRepository;
import com.example.ghumantu.Repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {

	private final VoteRepository voteRepository;
	private final PlaceRepository placeRepository;
	private final AuthService authService;
	
	@Transactional
	public void vote(VoteDto voteDto) {
		Place place = placeRepository.getById(voteDto.getPlaceId());
		User user = authService.getCurrentUser();
		Vote vote = voteRepository.getByPlaceAndUser(place,user);
		System.out.println(voteDto);
		if(vote == null) {
			if(voteDto.getVoteType().equals("like")) {
				place.setLikes(place.getLikes()+1);
			}
			else {
				place.setDisLikes(place.getDisLikes()+1);
			}
			voteRepository.save(MapVoteDtoToVote(voteDto, user, place));
			placeRepository.save(place);
		}
		else {
			if(voteDto.getVoteType().equals("like") && vote.getVoteType().equals("like")) {
				place.setLikes(place.getLikes()-1);
				voteRepository.delete(vote);
			}
			else if(voteDto.getVoteType().equals("like") && vote.getVoteType().equals("dislike")) {
				place.setLikes(place.getLikes()+1);
				place.setDisLikes(place.getDisLikes()-1);
				voteRepository.delete(vote);
				voteRepository.save(MapVoteDtoToVote(voteDto,user,place));
			}
			else if(voteDto.getVoteType().equals("dislike") && vote.getVoteType().equals("dislike")) {
				place.setDisLikes(place.getDisLikes()-1);
				voteRepository.delete(vote);
			}
			else if(voteDto.getVoteType().equals("dislike") && vote.getVoteType().equals("like")) {
				place.setLikes(place.getLikes()-1);
				place.setDisLikes(place.getDisLikes()+1);
				voteRepository.delete(vote);
				voteRepository.save(MapVoteDtoToVote(voteDto, user, place));
			}
			placeRepository.save(place);
		}
	}
	
	private Vote MapVoteDtoToVote(VoteDto voteDto, User user,Place place) {
		return Vote.builder()
				.place(place)
				.user(user)
				.voteType(voteDto.getVoteType())
				.build();
	}
	
	private VoteDto getPlaceIdFromPlace(Vote vote) {
		return VoteDto.builder()
				.placeId(vote.getPlace().getPlaceId())
				.voteType(vote.getVoteType())
				.build();
	}

	public List<VoteDto> likedAndDislikedByUser() {
		User user = authService.getCurrentUser();
		
		return voteRepository.getByUser(user).stream()
				.map(this::getPlaceIdFromPlace)
				.collect(Collectors.toList());
	}
}
