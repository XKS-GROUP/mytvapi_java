package com.mytv.api.model.gestMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerie;

	@NotBlank(message = "une serie doit forcement avoir un nom ")
	@Column(nullable = false, columnDefinition = "TEXT")
	String name;

	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	
	@OneToMany(mappedBy = "idSaison")
	List<Saison> saison;

	List<Long> idSaison;
	
	boolean Upcoming;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	String IMDbRating;

	String ContentRating;

	Long budget;

	@Column(columnDefinition = "TEXT")
	String trailerUrl;
	
	@Column(columnDefinition = "TEXT")
	String trailer;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean AccessFree;

	@NotNull(message = "une image miniature est requise pour une serie")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;

	@Column(columnDefinition = "TEXT")
	String poster_path;

	List<Long> Langue;

	String popularity;

	String vote_average;

	String vote_count;


	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;


	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;


	List <Long> acteurList = new ArrayList<>();

	List <Long> directorList = new ArrayList<>();

	List<Long> genreList = new ArrayList<>();

}
