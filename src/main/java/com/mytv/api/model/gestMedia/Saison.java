package com.mytv.api.model.gestMedia;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Saison {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSaison;

	@NotBlank(message = "ce champs ne peut pas etre null")
	@Column(nullable = false)
	String name;

	@NotBlank(message = "ce champs ne peut pas etre null")
	@Column(nullable = false)
	String overview;

	@NotNull(message ="Ce champs ne peut pas etre null ou vide, une saison doit forcement faire reference a une serie")
	@Column(nullable = false)
	Long idSerie;

	@NotNull(message = "une image miniature est requise pour une serie")
	String backdrop_path;

	String poster_path;

	String trailerURL;
	String trailerFile;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	@OneToMany(mappedBy = "idEpisode",cascade = CascadeType.REMOVE)
	List<Episode> episode;

}
