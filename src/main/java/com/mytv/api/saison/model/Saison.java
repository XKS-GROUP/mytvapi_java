package com.mytv.api.saison.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mytv.api.episode.model.Episode;
import com.mytv.api.serie.model.Serie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Saison {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSaison;

	@NotBlank(message = "ce champs ne peut pas etre null")
	@Column(nullable = false)
	String name;

	@NotBlank(message = "ce champs ne peut pas etre null")
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	@ManyToOne
	@JoinColumn(name = "idSerie")
	@JsonBackReference
	Serie idSerie;

	Long serieRef;
	
	@NotBlank(message = "une image miniature est requise pour une serie")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;

	@Column(columnDefinition = "TEXT")
	String poster_path;

	@Column(columnDefinition = "TEXT")
	String trailerURL;
	
	@Column(columnDefinition = "TEXT")
	String trailerFile;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	@CreatedDate
	Date addDate;

	@JsonFormat(pattern="dd-MM-yyyy")
	Date releaseDate;
	
	List<Long> idEpisodes = new ArrayList<>();
	
	@NotNull(message = "ce champ ne peut etre vide, au moins une langue est requise")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idSaison", cascade = CascadeType.DETACH)
	@JsonManagedReference
	List<Episode> episodes;
	
}
