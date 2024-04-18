package com.mytv.api.model.gestMedia;

import java.sql.Date;
//import java.util.Set;

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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String name;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String overView;

	@NotBlank(message = "Ce champ ne peut etre vide, un episode doit forcement faire reference a une serie")
	@Column(nullable = false)
	Long idSerie;

	@NotBlank(message = "Ce champ ne peut etre vide, un episode doit forcement faire reference a une saison")
	@Column(nullable = false)
	Long idSaison;

	@CreatedDate
	Date addDate;

	Date realeaseDate;

	@Column(nullable = false)
	int numero;

	String duration;

	@NotNull(message = "un satus par defaut doit etre attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	@NotNull(message = "une image miniature est requise pour un episode")
	String backdrop_path;

	String posterUrl;

	String trailer;

	@NotNull(message = "un fichier fideo initial est requis pour un episode")
	String videoFile;

	String videoFile480pLocal;
	String videoFile480pUrl;

	String videoFile720pLocal;
	String videoFile720pUrl;

	String videoFile1080pLocal;
	String videoFile1080pUrl;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;

	String  downloadURL;
}
