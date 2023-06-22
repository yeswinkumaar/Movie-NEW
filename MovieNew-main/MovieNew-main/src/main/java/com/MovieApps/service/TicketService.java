package com.MovieApps.service;

import java.util.List;

import org.apache.el.parser.AstString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MovieApps.Excep.NotFoundException;
import com.MovieApps.model.Movie;
import com.MovieApps.model.Ticket;
import com.MovieApps.repo.MovieRepository;
import com.MovieApps.repo.TicketRepository;

@Service
public class TicketService {
	private final TicketRepository ticketRepository;
	private final MovieRepository movieRepository;
	public TicketService(TicketRepository ticketRepository, MovieRepository movieRepository) {
		this.ticketRepository = ticketRepository;
		this.movieRepository = movieRepository;
	}
	   public List<Ticket> getAllTicket() {
	        return ticketRepository.findAll();
	    }
	  public Ticket addTicket(Ticket ticket) {
			return ticketRepository.save(ticket);
	    	
	    }
	  public String bookTickets(String movieName, Ticket ticket) {
	        Movie movie = movieRepository.findByName(movieName);
	        if (movie == null) {
	            return "Movie not found.";
	        }

	        ticket.setMovieId(movie.getId());
	        ticketRepository.save(ticket);
	        return "Tickets booked successfully.";
	    }
	  public String updateTicketStatus(String movieName, String ticketId, String status) {
	        Movie movie = movieRepository.findByName(movieName);
	        if (movie == null) {
	            return "Movie not found.";
	        }

	        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
	        if (ticket == null) {
	            return "Ticket not found.";
	        }

	        ticket.setStatus(status);
	        ticketRepository.save(ticket);
	        return "Ticket status updated successfully.";
	    }
}
