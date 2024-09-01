package com.mytv.api.podcastCollecton.model;

import java.util.ArrayList;
import java.util.List;

import com.mytv.api.intervenant.model.Podcasteur;
import com.mytv.api.podcastcateg.model.CatPodcast;
import com.mytv.api.ressource.model.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

//import java.sql.Date;
//import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColPodcast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idColPd;

	@NotBlank(message="ce champ ne peut etre nulle")
	@Column(nullable = false)
	String name;

	@NotBlank(message="ce champ ne peut etre nulle")
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	@NotBlank(message = "Une image est requise")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String cover;

	@NotNull(message = "un satus par defaut doit etre attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top;
	
	@Transient
	boolean favorie;
	
	List <Long> idPodcasteur = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<Podcasteur> list_podcasteur = new ArrayList<>();
	
	List <Long> categories = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idCatPod",  cascade = CascadeType.ALL)
	List<CatPodcast> list_categories = new ArrayList<>();
	
	@NotNull(message = "ce champ ne peut etre vide, au moins une langue est requise")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> list_langues = new ArrayList<>();;

}
