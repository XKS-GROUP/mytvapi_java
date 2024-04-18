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
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Podcast {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPodcast")
	Long idPodcast;

	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir un nom")
	@Column(nullable = false)
	String name ;

	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir une description")
	String overview;

	@NotNull(message = "une image miniature est requise pour un podcast")
	String backdrop_path;

	String poster_path;

	@NotNull(message="Un podcast doit forcement avoir une categori")
	Long category;

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	String fileLink;

	String streamlink;

}