package com.example.ghumantu.Service;

import com.example.ghumantu.Dto.TicketResponse;
import com.example.ghumantu.Exception.SpringException;
import com.example.ghumantu.Model.Ticket;
import com.example.ghumantu.Model.User;
import com.example.ghumantu.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AuthService authService;

    public List<Ticket> getMyTickets(){
        try {
            User user = authService.getCurrentUser();
            return ticketRepository.getByUser(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new SpringException("user not found");
        }

    }
}
