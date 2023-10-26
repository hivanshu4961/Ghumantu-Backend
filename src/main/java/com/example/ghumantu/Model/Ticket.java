package com.example.ghumantu.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName ="userId")
	private User user;
	
	@Column
	private Integer cost;
	
	@Column
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "placeId", referencedColumnName ="placeId")
	private Place place;
	
	@Column
	private String ticketId;
}
