package com.mytv.api.model.gestMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerie;

	@NotBlank(message = "une serie doit forcement avoir un nom ")
	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	String overview;

	
	@OneToMany(mappedBy = "idSaison")
	List<Saison> saison;

	boolean Upcoming;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	String IMDbRating;

	String ContentRating;

	Long budget;


	String trailerUrl;
	String trailer;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean AccessFree;

	@NotNull(message = "une image miniature est requise pour une serie")
	String backdrop_path;

	String poster_path;

	String Langue;

	String popularity;

	String vote_average;

	String vote_count;


	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;


	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;


	List <String> acteurList = new ArrayList<>();

	List <String> directorList = new ArrayList<>();

	List<String> genreList = new ArrayList<>();

}
