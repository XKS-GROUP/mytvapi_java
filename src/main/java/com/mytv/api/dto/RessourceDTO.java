package com.mytv.api.dto;

import java.io.Serializable;
import java.util.List;

import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.ressource.Language;
import com.mytv.api.model.ressource.Pays;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RessourceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private List<Pays> pays;
	
	private List<Language> Langues;
	
	private List<CategoryRL> Categs_Radio_Livetv;
	
	private List<Genre> genres_film_serie;

	private List<CatPodcast> Categs_Podcast;

	public RessourceDTO(List<Pays> pays, List<Language> langues, List<CategoryRL> categs_Radio_Livetv,
			List<Genre> genres_film_serie, List<CatPodcast> categs_Podcast) {
		super();
		this.pays = pays;
		Langues = langues;
		Categs_Radio_Livetv = categs_Radio_Livetv;
		this.genres_film_serie = genres_film_serie;
		Categs_Podcast = categs_Podcast;
	}

	
	/*
	public FavoriteAllResponse(List<FavFilm> film, List<FavSerie> serie, List<FavRadio> radio, List<FavPodcast> podcast,
			List<FavLiveTv> livetv) {
		super();
		this.film = film;
		this.serie = serie;
		this.radio = radio;
		this.podcast = podcast;
		this.livetv = livetv;
	}*/
	
}
