package com.mytv.api.model.gestMedia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Setter
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFilm")
	Long idFilm;

	Long IMDbId;

	@NotBlank(message = "ce champ ne peut etre vide, un film doit forcement posseder un nom")
	@Column(nullable = false, columnDefinition = "TEXT")
	String name;

	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour un film")
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean Upcoming;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	String IMDbRating;

	String ContentRating;

	float budget;

	@Column(columnDefinition = "TEXT")
	String trailerUrl;
	
	@Column(columnDefinition = "TEXT")
	String trailer;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean movieAccessFree;

	@NotNull(message = "une image miniature est requise pour un film")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;
	
	@Column(columnDefinition = "TEXT")
	String poster;

	List<Long> Langue = new ArrayList<Long>();

	String popularity;

	String vote_average;

	String vote_count;

	//@NotNull(message = "un fichier video initial est requis pour un film")
	@Column(columnDefinition = "TEXT")
	String videoFile;

	@Column(columnDefinition = "TEXT")
	String videoFile480pLocal;
	
	@Column(columnDefinition = "TEXT")
	String videoFile480pUrl;

	@Column(columnDefinition = "TEXT")
	String videoFile720pLocal;
	
	@Column(columnDefinition = "TEXT")
	String videoFile720pUrl;
	
	@Column(columnDefinition = "TEXT")
	String videoFile1080pLocal;
	
	@Column(columnDefinition = "TEXT")
	String videoFile1080pUrl;

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;

	@Column(columnDefinition = "TEXT")
	String  downloadURL;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;

	List <Long> acteurList = new ArrayList<>();

	List <Long> directorList = new ArrayList<>();

	List<Long> genreList = new ArrayList<>();

}