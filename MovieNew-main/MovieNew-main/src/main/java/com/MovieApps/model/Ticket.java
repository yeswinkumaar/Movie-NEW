package com.MovieApps.model;

import java.util.List;

import javax.annotation.processing.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Document(collection = "Tickets")
public class Ticket {
		@Id
	    private String id;
	    private String movieId;
	    private String theatreName;
	    private int numTickets;
	    private List<String> seatNumbers;
	    private String status;

}
