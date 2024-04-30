package com.mytv.api.dto;

import java.io.Serializable;
import java.util.List;

import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.Language;
import com.mytv.api.model.gestMedia.Pays;

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
	
	private List<Language> Lang;
	
	private List<CategoryRL> CatRL;
	
	private List<Genre> genre;

	private List<CatPodcast> CatPodcast;

	public RessourceDTO(List<Pays> pays, List<Language> lang, List<CategoryRL> catRL, List<Genre> genre,
			List<com.mytv.api.model.gestMedia.CatPodcast> catPodcast) {
		super();
		this.pays = pays;
		Lang = lang;
		CatRL = catRL;
		this.genre = genre;
		CatPodcast = catPodcast;
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
