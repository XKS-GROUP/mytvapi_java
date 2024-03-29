package com.mytv.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.service.gestMedia.CatPodcastService;
import com.mytv.api.service.gestMedia.CategoryLrService;
import com.mytv.api.service.gestMedia.EpisodeService;
import com.mytv.api.service.gestMedia.GenreService;
import com.mytv.api.service.gestMedia.LiveTvSetvice;
import com.mytv.api.service.gestMedia.PaysService;
import com.mytv.api.service.gestMedia.PodcastService;
import com.mytv.api.service.gestMedia.RadioService;
import com.mytv.api.service.gestMedia.SerieService;
import com.mytv.api.service.gestMedia.ServiceFilm;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/")

@SecurityRequirement(name = "bearerAuth")
public class FrontController {

	@Autowired
	private RadioService radioService;
	@Autowired
	private LiveTvSetvice liveService;
	@Autowired
	private PodcastService podcastservice;
	@Autowired
	private ServiceFilm filmService;
	@Autowired
	private SerieService serieService;
	@Autowired
	private EpisodeService episodeService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private CategoryLrService catLrService;
	@Autowired
	private CatPodcastService catpodService;
	@Autowired
	private PaysService paysService;

	//Pays
	@Tag(name = "Pays")
	@GetMapping("pays")
	public List<Pays> showPays(){

		return paysService.show();
	}

	@Tag(name = "Pays")
	@GetMapping("pays/{id}")
	public Optional<Pays> showbyIdPays(@PathVariable Long id){

		return paysService.showById(id);
	}


	//Genre

	@Tag(name = "Genres")
	@GetMapping("genres")
	public List<Genre> showG(){

		return genreService.show();
	}

	@Tag(name = "Genres")
	@GetMapping("genres/{id}")
	public Optional<Genre> showbyIdG(@PathVariable Long id){

		return genreService.showById(id);
	}


	//Categorie LiveTv ou Radio
	@Tag(name = "Categorie Radio et LiveTv")
	@GetMapping("catrl")
	public List<CategoryRL> showCRL(){

		return catLrService.show();
	}


	//Categorie Podcast

	@Tag(name = "Categorie Podcast")
	@GetMapping("catpod")
	public List<CatPodcast> showCP(){

		return catpodService.show();
	}

	
	/*
	 * GESTION DES RADIOS
	 */
	//Radio
	@Tag(name = "Radios")
	@GetMapping("radios")
	public List<Radio> showR(){

		return radioService.show();
	}

	@Tag(name = "Radios")
	@GetMapping("radios/{id}")
	public Optional<Radio> showbyIdR(@PathVariable Long id){

		return radioService.showById(id);
	}
	@Tag(name = "Radios")
	@GetMapping("radiosbynamecontain/{name}")
	public List<Radio> showbyNameContain(@PathVariable String nom){

		return radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Radios")
	@PostMapping("radioAddcomment/{idRadio}")
	public List<Radio> radioAddComment(@PathVariable Long idRadio){

		return null;//radioService.showByNameContaining(nom);
	}
	@Tag(name = "Radios")
	@DeleteMapping("radioAddcomment/{idRadio}")
	public List<Radio> radioDelComment(@PathVariable Long idRadio){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Radios")
	@GetMapping("radioShowFavorie")
	public List<Radio> radioShowLike(){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Radios")
	@PostMapping("radioAddlike/{idRadio}")
	public List<Radio> radioAddLike(@PathVariable Long idRadio){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Radios")
	@PostMapping("radioDellike/{idRadio}")
	public List<Radio> radioDelLike(@PathVariable Long idRadio){

		return null;//radioService.showByNameContaining(nom);
	}
	

	@Tag(name = "Radios")
	@GetMapping("radioShowFavorie/{idRadio}")
	public List<Radio> radioShowFavorie(){

		return null;//radioService.showByNameContaining(nom);
	}
	@Tag(name = "Radios")
	@PostMapping("radioAddFavorie/{idRadio}")
	public List<Radio> radioAddFavorie(@PathVariable Long idRadio){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Radios")
	@PostMapping("radioDelFavorie/{idRadio}")
	public List<Radio> radioDelFavorie(@PathVariable Long idRadio){

		return null;//radioService.showByNameContaining(nom);
	}

	/*
	 * GESTION DES LIVES TV 
	 */
	//ROUTES LiveTV
	@Tag(name = "Lives")
	@GetMapping("lives")
	public List<LiveTv> showL(){

		return liveService.show();
	}

	@Tag(name = "Lives")
	@GetMapping("lives/{id}")
	public Optional<LiveTv> showbyIdL(@PathVariable Long id){

		return liveService.showById(id);
	}
    
	@Tag(name = "Lives")
	@GetMapping("livesbynamecontain/{nom}")
	public List<LiveTv> showLbyNameContainL(@PathVariable String nom){

		return liveService.showByNameContaining(nom);
	}
	
	//COM
	@Tag(name = "Lives")
	@GetMapping("liveShowcomment/{idlive}")
	public List<Radio> liveShowComment(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Lives")
	@PostMapping("liveAddcomment/{idlive}")
	public List<Radio> liveAddComment(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Lives")
	@DeleteMapping("liveDelcomment/{idlive}")
	public List<Radio> liveDelComment(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	
	//LIKE
	@Tag(name = "Lives")
	@GetMapping("liveShowlike/{idlive}")
	public List<Radio> liveShowLike(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Lives")
	@PostMapping("liveAddlike/{idlive}")
	public List<Radio> liveAddLike(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	@Tag(name = "Lives")
	@DeleteMapping("liveDellike/{idlive}")
	public List<Radio> liveDelLike(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	
	/*
	 * GESTION DES FAVORIES
	 */
	//FAVORIES
	@Tag(name = "Lives")
	@GetMapping("liveShowFavorie/{idlive}")
	public List<Radio> liveShowFavorie(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	@Tag(name = "Lives")
	@PostMapping("liveAddFavorie/{idlive}")
	public List<Radio> liveAddFavorie(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}
	@Tag(name = "Lives")
	@DeleteMapping("liveDelFavorie/{idlive}")
	public List<Radio> liveDelFavorie(@PathVariable Long idlive){

		return null;//radioService.showByNameContaining(nom);
	}

	
	
	/*
	 * GESTION DES PODCASTS
	 */
	//Podcast
	@Tag(name = "Podcasts")
	@GetMapping("podcasts")
	public List<Podcast> showP(){

		return podcastservice.show();
	}

	@Tag(name = "Podcasts")
	@GetMapping("podcasts/{id}")
	public Optional<Podcast> showbyIdP(@PathVariable Long id){

		return podcastservice.showById(id);
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcastsbynamecontain/{name}")
	public List<Podcast> showbyIdP(@PathVariable String name){

		return podcastservice.showByNameContaining(name);
	}

	
	/*
	 * GESTION DES FILMS
	 */
	//Films
	@Tag(name = "Films")
	@GetMapping("movies")
	public List<Film> showM(){

		return filmService.show();
	}

	@Tag(name = "Films")
	@GetMapping("movies/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){

		return filmService.showById(id);
	}
    
	@Tag(name = "Films")
	@GetMapping("moviesbynamecontain/{id}")
	public List<Film> showbyIdM(@PathVariable String name){

		return filmService.showByNameContaining(name);
	}
	
	/*
	 * GESTION DES SERIES
	 */
	//Series
	@Tag(name = "Series")
	@GetMapping("series")
	public List<Serie> showS(){

		return serieService.show();
	}

	@Tag(name = "Series")
	@GetMapping("series/{id}")
	public Optional<Serie> showbyIdS(@PathVariable Long id){

		return serieService.showById(id);
	}
	@Tag(name = "Series")
	@GetMapping("seriesbynamecontain/{name}")
	public List<Serie> showbyIdS(@PathVariable String name){

		return serieService.showbyNameContaining(name);
	}
	
	
	/*
	 * GESTION DES EPISODES
	 */
	//Episodes
	@Tag(name = "Episodes")
	@GetMapping("episodes")
    public List<Episode> showE(){

		return episodeService.show();
	}

	@Tag(name = "Episodes")
	@GetMapping("episodes/{id}")
	public Optional<Episode> showbyIdE(@PathVariable Long id){

		return episodeService.showById(id);
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodesbynamecontain/{name}")
	public List<Episode> showbyIdE(@PathVariable String name){

		return episodeService.showByNameContain(name);
	}
}
