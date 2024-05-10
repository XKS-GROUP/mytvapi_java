package com.mytv.api.model.gestMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSerie", nullable = true)
	@JsonBackReference
    //@OnDelete(action = OnDeleteAction.SET_NULL)
	Serie serie;

	@NotNull(message = "une image miniature est requise pour une serie")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;

	@Column(columnDefinition = "TEXT")
	String poster_path;

	@Column(columnDefinition = "TEXT")
	String trailerURL;
	
	@Column(columnDefinition = "TEXT")
	String trailerFile;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	
	@OneToMany(mappedBy = "idSaison", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JsonManagedReference
	List<Episode> episodes = new ArrayList<>() ;
	
	List<Long> idEpisodes = new ArrayList<>();
	
	@NotNull(message = "ce champ ne peut etre vide, au moins une langue est requise")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();

}
