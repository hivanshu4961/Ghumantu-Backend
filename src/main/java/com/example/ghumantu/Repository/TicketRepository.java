package com.example.ghumantu.Repository;

import com.example.ghumantu.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ghumantu.Model.Ticket;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{


    List<Ticket> getByUser(User user);
}
