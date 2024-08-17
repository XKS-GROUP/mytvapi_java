package com.mytv.api.response;

import java.io.Serializable;
import java.util.List;

import com.mytv.api.film.model.FavFilm;
import com.mytv.api.livetv.model.FavLiveTv;
import com.mytv.api.podcast.model.FavPodcast;
import com.mytv.api.radio.model.FavRadio;
import com.mytv.api.serie.model.FavSerie;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FavoriteAllResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FavFilm> film;
	
	private List<FavSerie> serie;
	
	private List<FavRadio> radio;
	
	private List<FavPodcast> podcast;

	private List<FavLiveTv> livetv;

	public FavoriteAllResponse(List<FavFilm> film, List<FavSerie> serie, List<FavRadio> radio, List<FavPodcast> podcast,
			List<FavLiveTv> livetv) {
		super();
		this.film = film;
		this.serie = serie;
		this.radio = radio;
		this.podcast = podcast;
		this.livetv = livetv;
	}
	
}
