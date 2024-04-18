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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Film {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFilm")
	Long idFilm;

	Long IMDbId;

	@NotBlank(message = "ce champ ne peut etre vide, un film doit forcement posseder un nom")
	@Column(nullable = false)
	String name;

	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour un film")
	@Column(nullable = false)
	String overview;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean Upcoming;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	String IMDbRating;

	String ContentRating;

	float budget;


	String trailerUrl;
	String trailer;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean movieAccessFree;

	@NotNull(message = "une image miniature est requise pour un film")
	String backdrop_path;

	String poster;

	String Langue;

	String popularity;

	String vote_average;

	String vote_count;

	//@NotNull(message = "un fichier video initial est requis pour un film")
	String videoFile;

	String videoFile480pLocal;
	String videoFile480pUrl;

	String videoFile720pLocal;
	String videoFile720pUrl;

	String videoFile1080pLocal;
	String videoFile1080pUrl;

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;

	String  downloadURL;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;


	List <String> acteurList = new ArrayList<>();

	List <String> directorList = new ArrayList<>();

	List<String> genreList = new ArrayList<>();

}