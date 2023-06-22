package com.MovieApps.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MovieApps.Excep.NotFoundException;
import com.MovieApps.model.Movie;
import com.MovieApps.model.Ticket;
import com.MovieApps.model.User;
import com.MovieApps.service.MovieService;
import com.MovieApps.service.TicketService;
import com.MovieApps.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;

@RestController
//@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin
public class UserController {
	private UserService userService;
	private final MovieService movieService;
	private final TicketService ticketService;
	public UserController(UserService userService, MovieService movieService, 
			TicketService ticketService) {
		
		this.userService = userService;
		this.movieService = movieService;
		this.ticketService = ticketService;
	}

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration;

	@PostMapping("/register")
	@Operation(summary = "Register a new user")

	public ResponseEntity<?> registerUser(
		    @RequestBody User user
		) {
		try {
			User registeredUser = userService.registerUser(user);

			return ResponseEntity.ok(registeredUser);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/login")
	@Operation(summary = "User login")

	public ResponseEntity<?> loginUser(@RequestParam String loginId, @RequestParam String password) {
		User user = userService.login(loginId, password);
		if (user != null) {
			String token = generateJwtToken(user);
			System.out.println("Generated token: " + token);
			return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(user);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
	}

	@PostMapping("/reset-password")
	@Operation(summary = "reset-password")

	public ResponseEntity<?> resetPassword(@RequestParam String loginId, @RequestParam String newPassword) {
		User user = userService.resetPassword(loginId, newPassword);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}

	public String generateJwtToken(User user) {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(user.getLoginId())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)).signWith(key).compact();
	}

	private Claims extractClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
	}

	@SuppressWarnings("unused")
	private boolean isTokenExpired(String token) {
		Date expirationDate = extractClaimsFromToken(token).getExpiration();
		return expirationDate.before(new Date());
	}
	//MovieService
	@GetMapping("/all")
	@Operation(summary = "all-Movies")
	public List<Movie> getAllMovies() {
		
		return movieService.getAllMovies();
	}
	@GetMapping("/allTicket")
	@Operation(summary = "all-Ticket")
	public List<Ticket> getAllTicket() {
		
		return ticketService.getAllTicket();
	}

	@GetMapping("/search")
	@Operation(summary = "Search-Movies")

	public List<Movie> searchMovies(@RequestParam("keyword") String keyword) {

		return movieService.searchMovies(keyword);
	}

	@PostMapping("/addMovie")
	@Operation(summary = "Add-Movies")

	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		try {
			Movie registeredMovie = movieService.addMovies(movie);
			return ResponseEntity.ok(registeredMovie);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PostMapping("/addTicket")
	@Operation(summary = "Add-Ticket")

	public ResponseEntity<?> addTicket(@RequestBody Ticket ticket) {
		try {
			Ticket registeredTicket = ticketService.addTicket(ticket);
			return ResponseEntity.ok(registeredTicket);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/{moviename}/add")
	@Operation(summary = "Add-Movies-Ticket")

	public ResponseEntity<String> bookTickets(@PathVariable("moviename") String movieName, @RequestBody Ticket ticket) {
		return ResponseEntity.ok(ticketService.bookTickets(movieName, ticket));
	}

	@PutMapping("/{moviename}/update/{ticketId}")
	@Operation(summary = "Update-Ticket-Status")

	public ResponseEntity<String> updateTicketStatus(@PathVariable("moviename") String movieName,
			@PathVariable("ticketId") String ticketId, @RequestParam("status") String status) {
		return ResponseEntity.ok(ticketService.updateTicketStatus(movieName, ticketId, status));
	}

	@DeleteMapping("/{moviename}/delete/{id}")
	@Operation(summary = "Delete-Movies")

	public ResponseEntity<String> deleteMovie(@PathVariable("moviename") String movieName,
			@PathVariable("id") String id) {
		try {
			movieService.deleteMovie(movieName, id);
			return ResponseEntity.ok("Movie deleted successfully");
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}
}

