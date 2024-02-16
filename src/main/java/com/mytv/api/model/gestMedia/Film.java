package com.mytv.api.model.gestMedia;

import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Film {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFilm")
	Long idFilm;
	
	@Column(nullable = false)
	String name ;
	
	String overview;
	
	String Upcoming;
	
	String Content; 
	
	String Rating;
		
	String posterUrl;
	
	String trailerUrl;
	
	Date releaseDate;
	
	
	boolean download;
	
	String downloadURL;
	
	String country;
	
	int duration;

}