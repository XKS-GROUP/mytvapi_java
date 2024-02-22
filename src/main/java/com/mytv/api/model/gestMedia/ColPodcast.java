package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;

//import java.sql.Date;
//import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ColPodcast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;
	
	
	@NotBlank(message="ce champ ne peut etre nulle")
	@Column(nullable = false)
	String name;
	
	@NotBlank(message="ce champ ne peut etre nulle")
	@Column(nullable = false)
	String overView;
	
	//@ManyToOne
	//@JoinColumn(name = "idSerie")
	@NotNull(message = "un satus par defaut doit etre attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	String posterUrl;
	
}
