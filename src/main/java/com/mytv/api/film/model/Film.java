package com.mytv.api.film.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.model.Director;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.model.Pays;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

	@JsonFormat(pattern="dd-MM-yyyy")
	private Date releaseDate;
	
	String epoque;

	String IMDbRating;

	String ContentRating;

	float budget;
	
	float recette;
	
	@Column( columnDefinition = "TEXT")
	String prix;

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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> list_langues;

	String popularity;

	String vote_average;

	String vote_count;
	
	float note;

	//@NotNull(message = "un fichier video initial est requis pour un film")
	@Column(columnDefinition = "TEXT")
	String videoFile;

	@JsonIgnore
	@Column(columnDefinition = "TEXT")
	String videoFile480pLocal;
	
	@Column(columnDefinition = "TEXT")
	String videoFile480pUrl;
	
	@JsonIgnore
	@Column(columnDefinition = "TEXT")
	String videoFile720pLocal;
	
	@Column(columnDefinition = "TEXT")
	String videoFile720pUrl;
	
	@JsonIgnore
	@Column(columnDefinition = "TEXT")
	String videoFile1080pLocal;
	
	@Column(columnDefinition = "TEXT")
	String videoFile1080pUrl;

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;

	@Column(columnDefinition = "TEXT")
	String  downloadURL;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;
	
	String duration;
	
	
	List <Long> acteurList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idActor",  cascade = CascadeType.ALL)
	List<Actor> acteurs;
	
	List <Long> directorList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idDirector",  cascade = CascadeType.ALL)
	List<Director> directors;

	@NotEmpty(message = "au moins un genre doit etre selectionné")
	List<Long> genreList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idGenre",  cascade = CascadeType.ALL)
	List<Genre> genres;
	
	@NotEmpty(message = "au moins un pays doit etre selectionné")
	List<Long>  country = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idPays",  cascade = CascadeType.ALL)
	List<Pays> list_country = new ArrayList<>();
	
	@Transient
	boolean favorie;
}