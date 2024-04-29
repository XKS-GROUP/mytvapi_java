package com.mytv.api.model.gestMedia;



import java.util.ArrayList;
import java.util.List;

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
	
	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir une description")
	String overview;

	@NotNull(message = "une image miniature est requise pour un podcast")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;
	
	@Column(columnDefinition = "TEXT")
	String poster_path;

	@NotNull(message="Un podcast doit forcement avoir une categori")
	List <Long> categories = new ArrayList<>();

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	Boolean accessFree;

	@Column(columnDefinition = "TEXT")
	String fileLink;
	
	@Column(columnDefinition = "TEXT")
	String streamLink;
	
	@NotNull(message = "ce champ ne peut etre vide, au moins une langue est requise")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();

}