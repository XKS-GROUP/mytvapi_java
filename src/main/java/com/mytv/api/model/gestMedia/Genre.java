package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGenre", nullable = false)
	Long idGenre;

	@Column(nullable = false, length = 80, unique = true)
	@NotBlank(message = "Ce champ ne puis etre vide")
	String name;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;

	@NotBlank(message = "Une image est requise")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String img_path;

}
