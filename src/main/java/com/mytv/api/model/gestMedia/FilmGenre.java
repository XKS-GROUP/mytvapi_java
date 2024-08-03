package com.mytv.api.model.gestMedia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class FilmGenre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idFilmGenre;
   
	/*
	@ManyToOne
	@JoinColumn(name = "idFilm", insertable = true, updatable = true)
	private Film film;
	*/
	
	//@ManyToOne
	//@JoinColumn(name = "idGenre", insertable = true, updatable = true)
	private Long genre;

}

