package com.mytv.api.podcast.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.intervenant.model.Podcasteur;
import com.mytv.api.podcastcateg.model.CatPodcast;
import com.mytv.api.ressource.model.Language;

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
public class Podcast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPodcast")
	Long idPodcast;

	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir un nom")
	@Column(nullable = false)
	String name ;

	@NotBlank(message = "ce champ ne peut etre vide, un podcast doit forcement avoir un autheur")
	@Column(nullable = false)
	String autheur ;
	
	Long idCollection;
	
	List <Long> idPodcasteur;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<Podcasteur> list_podcasteur;
	
	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir une description")
	String overview;

	@NotNull(message = "une image miniature est requise pour un podcast")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;
	
	@Column(columnDefinition = "TEXT")
	String poster_path;

	
	@NotEmpty(message = "au moins un genre doit etre selectionné")
	Set <Long> categories = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idCatPod",  cascade = CascadeType.ALL)
	List<CatPodcast> list_categories = new ArrayList<>();

	@JsonIgnore
	@JdbcTypeCode(SqlTypes.JSON)
	List<CatPodcast> categ;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	Boolean accessFree;

	@Column(columnDefinition = "TEXT")
	String fileLink;
	
	@Column(columnDefinition = "TEXT")
	String streamLink;
	
	@NotEmpty(message = "ce champ ne peut etre vide, au moins une langue est requise")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> list_langues;
	
	@CreatedDate
	Date addDate;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top;
	
	@Transient
	boolean favorie;

}