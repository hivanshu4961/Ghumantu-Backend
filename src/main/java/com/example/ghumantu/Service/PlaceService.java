package com.example.ghumantu.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ghumantu.Dto.PlaceDto;
import com.example.ghumantu.Dto.PlaceResponse;
import com.example.ghumantu.Exception.SpringException;
import com.example.ghumantu.Model.Place;
import com.example.ghumantu.Model.State;
import com.example.ghumantu.Repository.PlaceRepository;
import com.example.ghumantu.Repository.StateRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaceService {

	private final PlaceRepository placeRepository;
	private final StateRepository stateRepository;
	
	public void save(PlaceDto placeDto) throws IOException {
		Place place = placeRepository.getByPlaceName(placeDto.getPlaceName());
		if(place != null) {
			throw new SpringException("place already present");
		}
		else {
			placeRepository.save(MapPlaceDtoToPlace(placeDto));
		}
		
		
	}
	
	
	private Place MapPlaceDtoToPlace(PlaceDto placeDto) throws IOException{
		return Place.builder()
				.address(placeDto.getAddress())
				.budget(placeDto.getBudget())
				.descripton(placeDto.getDescription())
				.placeName(placeDto.getPlaceName())
				.main_image(placeDto.getImage())
				.users(null)
				.likes(0)
				.disLikes(0)
				.build();
	}
	
	//Map place to placeResponse
	private PlaceResponse MapPlaceToPlaceResponse(Place place) {
		return PlaceResponse.builder()
		.address(place.getAddress())
		.description(place.getDescripton())
		.budget(place.getBudget())
		.placeId(place.getPlaceId())
		.likes(place.getLikes())
		.image(place.getMain_image())
		.placeName(place.getPlaceName())
		.disLikes(place.getDisLikes())
		.build();
	}
	
	public PlaceResponse getPlace(Integer placeId) {
		Place place = placeRepository.findById(placeId).orElseThrow(()-> new SpringException("place not found"));
		return MapPlaceToPlaceResponse(place);
	}
	
	public List<PlaceResponse> getAllPlaces(){
		List<Place> places = placeRepository.findAll();
		//System.out.println(places);
		return places.stream()
				.map(this :: MapPlaceToPlaceResponse)
				.collect(Collectors.toList());

	}


	
	
//	get places of a state
	public List<PlaceResponse> getAllPlacesByState(String name) throws SpringException{
		//check if given Category exist
		State state = stateRepository.getByStateName(name);
		if(state == null) {
			//System.out.println(categoryCheck);
			throw new SpringException("state doesn't exist");
		}
		return placeRepository.getByState(state)
				.stream()
				.map(this :: MapPlaceToPlaceResponse)
				.collect(Collectors.toList());
	}
	
}
