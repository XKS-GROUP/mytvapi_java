package com.mytv.api.serie.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.model.Director;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.saison.model.Saison;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	boolean Upcoming;

	@CreatedDate
	Date addDate;

	@JsonFormat(pattern="dd-MM-yyyy")
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
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> list_langues = new ArrayList<>();

	String popularity;

	String vote_average;

	String vote_count;
	
	float note;

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;

	List <Long> acteurList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idActor",  cascade = CascadeType.ALL)
	List<Actor> acteurs;

	List <Long> directorList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idDirector",  cascade = CascadeType.ALL)
	List<Director> directors;

	List<Long> genreList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idGenre",  cascade = CascadeType.ALL)
	List<Genre> genres;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idSerie",  cascade = CascadeType.ALL)
	@JsonManagedReference
	List<Saison> idSaison;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top;

}
