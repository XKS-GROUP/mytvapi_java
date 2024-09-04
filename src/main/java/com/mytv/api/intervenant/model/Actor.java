package com.mytv.api.intervenant.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mytv.api.ressource.model.Pays;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idActor;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String fistName;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String lastName;
	
	String TMDB;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	Date dob;

	@Column(columnDefinition = "TEXT")
	String imageUrl;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String biography;

	@NotEmpty(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	List <Long> pays = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idPays",  cascade = CascadeType.ALL)
	List<Pays> list_pays = new ArrayList<>();

}
