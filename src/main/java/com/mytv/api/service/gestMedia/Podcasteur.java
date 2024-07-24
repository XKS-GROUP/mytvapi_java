package com.mytv.api.service.gestMedia;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Podcasteur {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String fistName;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String lastName;
	
	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false, columnDefinition = "TEXT")
	String biography;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	Date dob;
	
	
}
