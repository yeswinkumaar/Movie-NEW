package com.MovieApps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MovieApps.Excep.NotFoundException;
import com.MovieApps.model.Movie;
import com.MovieApps.model.Ticket;
import com.MovieApps.repo.MovieRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> searchMovies(String keyword) {
        return movieRepository.findByNameContainingIgnoreCase(keyword);
    }
    public void deleteMovie(String movieName, String id) throws NotFoundException {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            if (movie.getName().equals(movieName)) {
                movieRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Movie name does not match");
            }
        } else {
            throw new NotFoundException("Movie not found");
        }
    }
    public Movie addMovies(Movie movie) {
		return movieRepository.save(movie);
    	
    }
}
