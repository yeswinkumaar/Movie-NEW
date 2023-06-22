package com.MovieApps;

/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.MovieApps.Controller.UserController;
import com.MovieApps.model.Movie;
import com.MovieApps.model.User;
import com.MovieApps.service.MovieService;
import com.MovieApps.service.TicketService;
import com.MovieApps.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private MovieService movieService;
    @Mock
    private TicketService ticketService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, movieService, ticketService);
    }
    @Test
    void registerUser_ValidUser_ReturnsOkResponseWithRegisteredUser() {
        User user = new User("testuser", "password", "Test User", null, null, null, null, null);

        when(userService.registerUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).registerUser(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void registerUser_InvalidUser_ReturnsBadRequestResponse() {
        User user = new User("testuser", "password", null, null, null, null, null, null);

        doThrow(new IllegalArgumentException("Invalid user details")).when(userService).registerUser(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid user details", response.getBody());

        verify(userService, times(1)).registerUser(user);
        verifyNoMoreInteractions(userService);
    }
    @Test
    void registerUser_NullUser_ReturnsBadRequestResponse() {
        User user = null;

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User cannot be null", response.getBody());

        verifyNoInteractions(userService);
    }
    
    //login test cases
    @Test
    void resetPassword_UserNotFound_ReturnsNotFoundResponse1() {
        String loginId = "testuser";
        String newPassword = "newpassword";

        when(userService.resetPassword(loginId, newPassword)).thenReturn(null);

        ResponseEntity<?> response = userController.resetPassword(loginId, newPassword);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        verify(userService, times(1)).resetPassword(loginId, newPassword);
        verifyNoMoreInteractions(userService);
    }
    @Test
    void loginUser_InvalidCredentials_ReturnsUnauthorizedResponse() {
        String loginId = "testuser";
        String password = "password";

        when(userService.login(loginId, password)).thenReturn(null);

        ResponseEntity<?> response = userController.loginUser(loginId, password);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid login credentials", response.getBody());

        verify(userService, times(1)).login(loginId, password);
        verifyNoMoreInteractions(userService);
    }
    //reset-password
    @Test
    void resetPassword_UserNotFound_ReturnsNotFoundResponse() {
        String loginId = "testuser";
        String newPassword = "newpassword";

        when(userService.resetPassword(loginId, newPassword)).thenReturn(null);

        ResponseEntity<?> response = userController.resetPassword(loginId, newPassword);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        verify(userService, times(1)).resetPassword(loginId, newPassword);
        verifyNoMoreInteractions(userService);
    }
    @Test
    void getAllMovies_ReturnsListOfMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", null, null, null));
        movies.add(new Movie("Movie 2", null, null, null));

        when(movieService.getAllMovies()).thenReturn(movies);

        UserController userController = new UserController(null, movieService, null);
        List<Movie> result = userController.getAllMovies();

        assertEquals(movies.size(), result.size());
        assertEquals(movies, result);

        verify(movieService, times(1)).getAllMovies();
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void searchMovies_ReturnsListOfMatchingMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", null, null, null));
        movies.add(new Movie("Movie 2", null, null, null));

        String keyword = "Movie";

        when(movieService.searchMovies(keyword)).thenReturn(movies);

        UserController userController = new UserController(null, movieService, null);
        List<Movie> result = userController.searchMovies(keyword);

        assertEquals(movies.size(), result.size());
        assertEquals(movies, result);

        verify(movieService, times(1)).searchMovies(keyword);
        verifyNoMoreInteractions(movieService);
    }
}*/
import com.MovieApps.Controller.UserController;
import com.MovieApps.Excep.NotFoundException;
import com.MovieApps.model.Movie;
import com.MovieApps.model.Ticket;
import com.MovieApps.model.User;
import com.MovieApps.service.MovieService;
import com.MovieApps.service.TicketService;
import com.MovieApps.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private MovieService movieService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void registerUser_ValidUser_ReturnsOkResponseWithRegisteredUser() {
        User user = new User("testuser", "password", "Test User", null, null, null, null, null);

        when(userService.registerUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).registerUser(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void registerUser_InvalidUser_ReturnsBadRequestResponse() {
        User user = new User("testuser", "password", null, null, null, null, null, null);

        doThrow(new IllegalArgumentException("Invalid user details")).when(userService).registerUser(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid user details", response.getBody());

        verify(userService, times(1)).registerUser(user);
        verifyNoMoreInteractions(userService);
    }
    //login test cases
    @Test
    void resetPassword_UserNotFound_ReturnsNotFoundResponse1() {
        String loginId = "testuser";
        String newPassword = "newpassword";

        when(userService.resetPassword(loginId, newPassword)).thenReturn(null);

        ResponseEntity<?> response = userController.resetPassword(loginId, newPassword);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        verify(userService, times(1)).resetPassword(loginId, newPassword);
        verifyNoMoreInteractions(userService);
    }
    @Test
    void loginUser_InvalidCredentials_ReturnsUnauthorizedResponse() {
        String loginId = "testuser";
        String password = "password";

        when(userService.login(loginId, password)).thenReturn(null);

        ResponseEntity<?> response = userController.loginUser(loginId, password);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid login credentials", response.getBody());

        verify(userService, times(1)).login(loginId, password);
        verifyNoMoreInteractions(userService);
    }
    //reset-password
    @Test
    void resetPassword_UserNotFound_ReturnsNotFoundResponse() {
        String loginId = "testuser";
        String newPassword = "newpassword";

        when(userService.resetPassword(loginId, newPassword)).thenReturn(null);

        ResponseEntity<?> response = userController.resetPassword(loginId, newPassword);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        verify(userService, times(1)).resetPassword(loginId, newPassword);
        verifyNoMoreInteractions(userService);
    }
    @Test
    void getAllMovies_ReturnsListOfMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", null, null, null));
        movies.add(new Movie("Movie 2", null, null, null));

        when(movieService.getAllMovies()).thenReturn(movies);

        UserController userController = new UserController(null, movieService, null);
        List<Movie> result = userController.getAllMovies();

        assertEquals(movies.size(), result.size());
        assertEquals(movies, result);

        verify(movieService, times(1)).getAllMovies();
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void searchMovies_ReturnsListOfMatchingMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", null, null, null));
        movies.add(new Movie("Movie 2", null, null, null));

        String keyword = "Movie";

        when(movieService.searchMovies(keyword)).thenReturn(movies);

        UserController userController = new UserController(null, movieService, null);
        List<Movie> result = userController.searchMovies(keyword);

        assertEquals(movies.size(), result.size());
        assertEquals(movies, result);

        verify(movieService, times(1)).searchMovies(keyword);
        verifyNoMoreInteractions(movieService);
    }
    @Test
    public void testRegisterUser() {
        User user = new User();
        when(userService.registerUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginUserWithValidCredentials() {
        User user = new User();
        String loginId = "test";
        String password = "password";
        when(userService.login(loginId, password)).thenReturn(user);

        ResponseEntity<?> response = userController.loginUser(loginId, password);

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginUserWithInvalidCredentials() {
        String loginId = "test";
        String password = "invalid";
        when(userService.login(loginId, password)).thenReturn(null);

        ResponseEntity<?> response = userController.loginUser(loginId, password);

        assertEquals("Invalid login credentials", response.getBody());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testResetPasswordWithValidCredentials() {
        User user = new User();
        String loginId = "test";
        String newPassword = "newPassword";
        when(userService.resetPassword(loginId, newPassword)).thenReturn(user);

        ResponseEntity<?> response = userController.resetPassword(loginId, newPassword);

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testResetPasswordWithInvalidCredentials() {
        String loginId = "test";
        String newPassword = "newPassword";
        when(userService.resetPassword(loginId, newPassword)).thenReturn(null);

        ResponseEntity<?> response = userController.resetPassword(loginId, newPassword);

        assertEquals("User not found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Existing test cases...

   /* @Test
    public void testBookTicket() throws NotFoundException {
        String movieName = "Avengers";
        String theatreName = "CinemaCity";
        int numberOfTickets = 2;
        String seatNumber = "A1";
        String status = "BOOKED";

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Ticket booked successfully");

        doNothing().when(ticketService).bookTicket(movieName, theatreName, numberOfTickets, seatNumber, status);

        ResponseEntity<String> response = userController.bookTicket(movieName, theatreName, numberOfTickets, seatNumber, status);

        assertEquals(expectedResponse, response);
        verify(ticketService, times(1)).bookTicket(movieName, theatreName, numberOfTickets, seatNumber, status);
    }*/

    
    @Test
    public void testDeleteMovieWithExistingMovie() throws NotFoundException {
        String movieName = "Avengers";
        String id = "1234";

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Movie deleted successfully");

        doNothing().when(movieService).deleteMovie(movieName, id);

        ResponseEntity<String> response = userController.deleteMovie(movieName, id);

        assertEquals(expectedResponse, response);
        verify(movieService, times(1)).deleteMovie(movieName, id);
    }

    @Test
    public void testDeleteMovieWithNonExistingMovie() throws NotFoundException {
        String movieName = "Avengers";
        String id = "1234";

        doThrow(NotFoundException.class).when(movieService).deleteMovie(movieName, id);

        ResponseEntity<String> response = userController.deleteMovie(movieName, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Movie not found", response.getBody());
        verify(movieService, times(1)).deleteMovie(movieName, id);
    }

    // Additional test cases for other methods in UserController
    @Test
    public void testGetAllMovies() {
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Avengers", "Action", null, null));
        expectedMovies.add(new Movie("Titanic", "Romance", null, null));

        when(movieService.getAllMovies()).thenReturn(expectedMovies);

        List<Movie> movies = userController.getAllMovies();

        assertEquals(expectedMovies, movies);
        verify(movieService, times(1)).getAllMovies();
    }

    @Test
    public void testSearchMovies() {
        String keyword = "Avengers";
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Avengers", "Action", keyword, null));
        expectedMovies.add(new Movie("Avengers: Endgame", "Action", keyword, null));

        when(movieService.searchMovies(keyword)).thenReturn(expectedMovies);

        List<Movie> movies = userController.searchMovies(keyword);

        assertEquals(expectedMovies, movies);
        verify(movieService, times(1)).searchMovies(keyword);
    }

    @Test
    public void testAddMovie() {
        Movie movie = new Movie("Avengers", "Action", null, null);

        ResponseEntity<?> expectedResponse = ResponseEntity.ok(movie);

        when(movieService.addMovies(movie)).thenReturn(movie);

        ResponseEntity<?> response = userController.addMovie(movie);

        assertEquals(expectedResponse, response);
        verify(movieService, times(1)).addMovies(movie);
    }



   

  

    @Test
    public void testDeleteMovie_Success() throws NotFoundException {
        String movieName = "Avengers";
        String id = "123";

        doNothing().when(movieService).deleteMovie(movieName, id);

        ResponseEntity<String> response = userController.deleteMovie(movieName, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Movie deleted successfully", response.getBody());
        verify(movieService, times(1)).deleteMovie(movieName, id);
    }

    @Test
    public void testDeleteMovie_NotFoundException() throws NotFoundException {
        String movieName = "Avengers";
        String id = "123";

        doThrow(new NotFoundException("Movie not found")).when(movieService).deleteMovie(movieName, id);

        ResponseEntity<String> response = userController.deleteMovie(movieName, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Movie not found", response.getBody());
        verify(movieService, times(1)).deleteMovie(movieName, id);
    }

    @Test
    public void testDeleteMovie_Exception() throws NotFoundException {
        String movieName = "Avengers";
        String id = "123";

        doThrow(new RuntimeException("Error occurred")).when(movieService).deleteMovie(movieName, id);

        ResponseEntity<String> response = userController.deleteMovie(movieName, id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred", response.getBody());
        verify(movieService, times(1)).deleteMovie(movieName, id);
    }
    
}









