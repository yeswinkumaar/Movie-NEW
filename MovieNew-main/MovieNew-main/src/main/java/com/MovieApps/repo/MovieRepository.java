package com.MovieApps.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.MovieApps.model.Movie;
@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

	List<Movie> findByNameContainingIgnoreCase(String keyword);

	Movie findByName(String movieName);
	
}