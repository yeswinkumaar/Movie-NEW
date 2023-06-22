package com.MovieApps.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "movies")
public class Movie  {
	/*  	@Id
	    private String id;
	    private String name;
	    private String theatreName;
	    private List<Ticket> tickets;*/
	@Id
    private String id;
    private String name;
    private String theatreName;
    private String releaseDate;
		
}
