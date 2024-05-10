package com.mytv.api.model.gestMedia;

import java.sql.Date;
//import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String name;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	@ManyToOne
	@JoinColumn(name = "idSerie", insertable = true, updatable = false)
	@JsonBackReference
	Saison idSaison;

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
	@Column(columnDefinition = "TEXT")
	String backdrop_path;

	@Column(columnDefinition = "TEXT")
	String posterUrl;
	
	@Column(columnDefinition = "TEXT")
	String trailer;

	@Column(columnDefinition = "TEXT")
	String videoUrl;
    
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

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;

	@Column(columnDefinition = "TEXT")
	String  downloadURL;
}
