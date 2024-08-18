package com.mytv.api.replay.intervenant.model;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intervenant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String fistName;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String lastName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	Date dob;
	
	@Column(columnDefinition = "TEXT")
	String imageUrl;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false, columnDefinition = "TEXT")
	String biography;

	@NotNull(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	int pays;

}
