package com.MovieApps.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.MovieApps.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

	// Define any additional query methods if needed
}