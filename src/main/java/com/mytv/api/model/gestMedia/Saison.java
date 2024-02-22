package com.mytv.api.model.gestMedia;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
	
	String posterUrl;
	
	String trailerURL;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	Date releaseDate;
	
	@OneToMany(mappedBy = "idEpisode",cascade = CascadeType.REMOVE)
	List<Episode> episode;
	
	

}
