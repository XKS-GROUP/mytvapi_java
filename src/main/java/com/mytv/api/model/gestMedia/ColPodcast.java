package com.mytv.api.model.gestMedia;

import java.util.List;

import jakarta.persistence.Column;

//import java.sql.Date;
//import java.util.Set;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	List <Long> idPodcasteur;

}
