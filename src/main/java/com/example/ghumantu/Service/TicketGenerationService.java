package com.example.ghumantu.Service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ghumantu.Dto.TicketRequest;
import com.example.ghumantu.Dto.TicketResponse;
import com.example.ghumantu.Exception.SpringException;
import com.example.ghumantu.Model.Place;
import com.example.ghumantu.Model.Ticket;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Repository.PlaceRepository;
import com.example.ghumantu.Repository.TicketRepository;
import com.example.ghumantu.Repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@Data
public class TicketGenerationService {
	
	
	private final UserRepository userRepository;
	private final PlaceRepository placeRepository;
	private final TicketRepository ticketRepository;
	private final AuthService authService;
	
	public TicketResponse generateTicket(TicketRequest payload) throws SpringException {
		User user = authService.getCurrentUser();
		Place place = placeRepository.getByPlaceName(payload.getPlaceName());
		
		if(place == null) {
			throw new SpringException("place not found");
		}
		
		String uniqueID = UUID.randomUUID().toString();
		
		Ticket ticket = new Ticket();
		ticket.setPlace(place);
		ticket.setUser(user);
		ticket.setCost(payload.getCost()*payload.getQuantity());
		ticket.setQuantity(payload.getQuantity());
		ticket.setTicketId(uniqueID);
		
		ticketRepository.save(ticket);
		
		return TicketResponse.builder()
				.cost(payload.getCost())
				.placeName(payload.getPlaceName())
				.ticketId(ticket.getTicketId())
				.quantity(payload.getQuantity())
				.email(user.getEmail())
				.build();
	}
}
