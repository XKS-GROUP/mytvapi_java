package com.mytv.api.model.gestMedia;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Radio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRadio")
	Long idRadio;

	@NotBlank(message = "ce champ ne peut etre vide, une radio doit obligatoirement avoir un nom")
	@Column(nullable = false)
	String name ;

	@Column(nullable = false)
	Long category;

	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour une radio")
	@Column(columnDefinition = "TEXT", nullable = false)
	String overview;
	
	@NotNull(message = "un logo est requis pour une radio")
	@Column(columnDefinition = "TEXT")
	String logo_path;

	@NotNull(message = "une image miniature est requise pour une radio")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;

	@NotNull(message = "ce champ ne peut etre vide, une valeur par defaut doit etre attribuer")
	@Column(columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	@NotBlank(message = "ce champ ne peut etre vide, une radio doit avoir une source")
	@Column(nullable = false, columnDefinition = "TEXT")
	String streamLink;
	

	@NotNull(message = "ce champ ne peut etre vide, une radio doit etre représenter par un pays")
	@Column(nullable = false)
	Long country;
	

}