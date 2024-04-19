package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Lang")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLang;

	@NotBlank(message = "ce champ ne peut pas etre vide")
	@Column(length = 50, nullable = false)
	String name;
	
	@Column(columnDefinition = "TEXT")
	String flag_file;

	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique, ce champ represente le code unique d'une langue")
	@Column(nullable = false, unique = true)
	String slug;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

}
