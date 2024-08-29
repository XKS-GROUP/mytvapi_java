package com.mytv.api.controller.front;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.model.FavEpisode;
import com.mytv.api.episode.model.LikeEpisode;
import com.mytv.api.episode.repository.FavEpisodeRepository;
import com.mytv.api.episode.repository.LikeEpisodeRepository;
import com.mytv.api.episode.service.EpisodeService;
import com.mytv.api.episode.service.FavEpisodeService;
import com.mytv.api.episode.service.LikeEpisodeService;
import com.mytv.api.film.model.FavFilm;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.model.LikeFilm;
import com.mytv.api.film.repository.FavFilmRepository;
import com.mytv.api.film.repository.LikeFilmRepository;
import com.mytv.api.film.service.FavFilmService;
import com.mytv.api.film.service.LikeFilmService;
import com.mytv.api.film.service.ServiceFilm;
import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.model.Director;
import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.intervenant.repository.DirectorRepository;
import com.mytv.api.live.service.FavLiveService;
import com.mytv.api.livetv.model.FavLiveTv;
import com.mytv.api.livetv.model.LikeLivetv;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.repository.FavLiveRepository;
import com.mytv.api.livetv.repository.LikeLivetvRepository;
import com.mytv.api.livetv.service.LikeLiveService;
import com.mytv.api.livetv.service.LiveTvSetvice;
import com.mytv.api.podcast.model.FavPodcast;
import com.mytv.api.podcast.model.LikePodcast;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.repository.FavPodcastRepository;
import com.mytv.api.podcast.repository.LikePodcastRepository;
import com.mytv.api.podcast.service.FavPodcastService;
import com.mytv.api.podcast.service.LikePodcastService;
import com.mytv.api.podcast.service.PodcastService;
import com.mytv.api.podcastCollecton.model.ColPodcast;
import com.mytv.api.podcastCollecton.repository.CollectionPodcastRepository;
import com.mytv.api.podcastCollecton.repository.FavColPodcastRepository;
import com.mytv.api.podcastCollecton.repository.LikeColPodcastRepository;
import com.mytv.api.podcastCollecton.service.ColPodcastService;
import com.mytv.api.podcastCollecton.service.FavColPodcastService;
import com.mytv.api.podcastCollecton.service.LikeColPodcastService;
import com.mytv.api.podcastcateg.model.CatPodcast;
import com.mytv.api.podcastcateg.service.CatPodcastService;
import com.mytv.api.radio.model.FavRadio;
import com.mytv.api.radio.model.LikeRadio;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.repository.FavRadioRepository;
import com.mytv.api.radio.repository.LikeRadioRepository;
import com.mytv.api.radio.service.FavRadioService;
import com.mytv.api.radio.service.LikeRadioService;
import com.mytv.api.radio.service.RadioService;
import com.mytv.api.response.FavoriteAllResponse;
import com.mytv.api.ressource.model.CategorieLive;
import com.mytv.api.ressource.model.CategoryRL;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.model.Pays;
import com.mytv.api.ressource.service.CategorieLiveService;
import com.mytv.api.ressource.service.CategoryLrService;
import com.mytv.api.ressource.service.GenreService;
import com.mytv.api.ressource.service.LangueService;
import com.mytv.api.ressource.service.PaysService;
import com.mytv.api.saison.model.FavSaison;
import com.mytv.api.saison.model.LikeSaison;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.repository.FavSaisonRepository;
import com.mytv.api.saison.repository.LikeSaisonRepository;
import com.mytv.api.saison.service.FavSaisonService;
import com.mytv.api.saison.service.LikeSaisonService;
import com.mytv.api.saison.service.SaisonService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.serie.model.FavSerie;
import com.mytv.api.serie.model.LikeSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.repository.FavSerieRepository;
import com.mytv.api.serie.repository.LikeSerieRepository;
import com.mytv.api.serie.service.FavSerieService;
import com.mytv.api.serie.service.LikeSerieService;
import com.mytv.api.serie.service.SerieService;
import com.mytv.api.service.CommonFunction;
import com.mytv.api.setting.model.SocialSetting;
import com.mytv.api.setting.service.SettingService;
import com.mytv.api.user.model.User;
import com.mytv.api.user.service.WUserService;
import com.mytv.api.util.model.Publicite;
import com.mytv.api.util.model.Slider;
import com.mytv.api.util.service.PubliciteService;
import com.mytv.api.util.service.SliderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/")
@SecurityRequirement(name = "bearerAuth")
public class FrontController {

	@Autowired
	private CommonFunction fnc;
	
	@Autowired
	SettingService settingService;
	
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
	
	@Autowired
	private LangueService langService;
    
	//Pour le lives tv
	@Autowired
	private CategorieLiveService categliveService;
    
    @Autowired
    private ActorRepository actorRep;
    
    @Autowired
    private DirectorRepository directorsRep;
    
    @Autowired
    private CollectionPodcastRepository colPodRep;
    
	@Autowired
    private SliderService sliderService;

	@Autowired
	PubliciteService pubService;
	
	
	
	//Radio FAV LIKE
	@Autowired
	private LikeRadioService likeradioService;
	@Autowired
	private FavRadioService favradioService;
	@Autowired
	private LikeRadioRepository likeradioRep;
	@Autowired
	private FavRadioRepository favradioRep;

	//LIVETV FAV LIKE
	@Autowired
	private FavLiveService favliveService;
	@Autowired
	private LikeLiveService likeliveService;
	@Autowired
	private LikeLivetvRepository likeliveRep;
	@Autowired
	private FavLiveRepository favliveRep;
	
	//PODCAST FAV LIKE COM
	@Autowired
	private FavPodcastService favpodService;
	@Autowired
	private LikePodcastService likepodService;
	@Autowired
	private LikePodcastRepository likepodRep;
	@Autowired
	private FavPodcastRepository favpodRep;
	
	//COLLECTION PODCAST FAV LIKE COM
	@Autowired
	private FavColPodcastService favcolpodService;
	@Autowired
	private LikeColPodcastService likecolpodService;
	@Autowired
	private LikeColPodcastRepository likecolpodRep;
	@Autowired
	private FavColPodcastRepository favcolpodRep;
	@Autowired
	private ColPodcastService colpodservice;
	
	//FILM FAV LIKE COM
	@Autowired
	private FavFilmService favfilmService;
	@Autowired
	private LikeFilmService likefilmService;
	@Autowired
	private LikeFilmRepository likefilmRep;
	@Autowired
	private FavFilmRepository favfilmRep;
	
	//SERIE FAV LIKE COM
	@Autowired
	private FavSerieService favserieService;
	@Autowired
	private LikeSerieService likeserieService;
	@Autowired
	private LikeSerieRepository likeserieRep;
	@Autowired
	private FavSerieRepository favserieRep;
	
	//EPISODE FAV LIKE COM
	@Autowired
	private FavEpisodeService favepisodeService;
	@Autowired
	private LikeEpisodeService likeepisodeService;
	@Autowired
	private LikeEpisodeRepository likeepisodeRep;
	@Autowired
	private FavEpisodeRepository favepisodeRep;
	
	//SAISON FAV LIKE COM
	@Autowired
	private SaisonService saisonService;
	@Autowired
	private FavSaisonService favsaisonService;
	@Autowired
	private LikeSaisonService likesaisonService;
	@Autowired
	private LikeSaisonRepository likesaisonRep;
	@Autowired
	private FavSaisonRepository favsaisonRep;
	@Autowired
	WUserService userService;
	

	
	/*
     * 
     * RESSOURCES
     * 
     * 
     * Renvoi l'ensemble des genres et categories des medias
     * 
     * 
     */
    
	@Tag(name = "Ressource")
    @GetMapping("ressources/all")
    public <R> Object getRessources(@RequestParam (required = false) List<String> resources) {
    	
    	List<Pays> pays = paysService.show();
    	
    	List<Slider> sliders = sliderService.show();
    	
    	List<Publicite> pubs = pubService.show();
    	
    	List<Language> Langues = langService.show();
    	
    	List<CategoryRL> CatRL = catLrService.show();
    	
    	List<Genre> genre = genreService.show();

    	List<CatPodcast> CatPodcast = catpodService.show();
    	
    	List <Actor> acteurs = actorRep.findAll();
    	
    	List<Director> directeurs = directorsRep.findAll();
    	
    	List<CategorieLive> categorieLive = categliveService.show();

    	
        if (resources == null || resources.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "sliders", sliders, "pubs", pubs,"pays",pays,"langues", Langues, "cat_radio_livetv", CatRL, "categlives",categorieLive,"genres", 
        					genre, "catpodcast", CatPodcast, "acteurs", acteurs, "directeurs", directeurs));
        }
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, resources.stream().map(res -> {
            switch (res.toLowerCase()) {
	            case "sliders":
	                return Map.of("sliders",sliders);
	            case "pubs":
	                return Map.of("pubs", pubs);
                case "pays":
                    return Map.of("pays",pays);
                case "langues":
                    return Map.of("langues", Langues);
                case "cat_radio_live":
                	return Map.of("cat_radio_live", CatRL);
                case "genres":
                	return Map.of("genres", genre);
                case "catpodcast":
                	return Map.of("catpodcast", CatPodcast);
                case "acteurs":
                	return Map.of("acteurs", acteurs);
                case "directeurs":
                	return Map.of("directeurs", directeurs);
                case "categlives":
                	return Map.of("categlives",categorieLive);
                default:
                	return Map.of("erreurs", "Ressource inconnue: " + res);
            }
        }).toArray());
    }
    
    
    /*
     * 
     * Renvoi l'ensemble des MEDIAS 
     * 
     * 
     */
    
    @Tag(name = "Ressource")
    @GetMapping("/medias/all/")
    public <R> Object getAllMedia(@RequestParam (required = false) List<String> media) {
    	
    	List<Film> films = filmService.show();
    	
    	List<Radio> radios = radioService.show();
    	
    	List<Podcast> podcasts = podcastservice.show();
    	
    	List<ColPodcast> colpodcasts = colPodRep.findAll();

    	List<Serie> series = serieService.show();
    	
    	List <Saison> saisons = saisonService.show();
    	
    	List<Episode> episodes = episodeService.show();
    	

    	
        if (media == null || media.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "films ",films,"radios", radios, "podcasts", podcasts, "colpodcasts",colpodcasts,"series", 
        					series, "saisons", saisons, "episodes", episodes));
        }
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, media.stream().map(res -> {
            
        	
        	switch (res.toLowerCase()) {
                case "films":
                    return Map.of("films",films);
                case "radios":
                    return Map.of("radios", radios);
                case "podcasts":
                	return Map.of("podcasts", podcasts);
                case "colpodcasts":
                	return Map.of("colpodcasts", colpodcasts);
                case "series":
                	return Map.of("series", series);
                case "saisons":
                	return Map.of("saisons", saisons);
                case "episodes":
                	return Map.of("episodes",episodes);
                default:
                	return Map.of("erreurs", "media inconnue: " + res);
            }
        }).toArray());
    }
    
    
    /*
     * 
     * Slider, publicite
     * 
     * 
     * 
     */
    
    @Tag(name = "Ressource")
    @GetMapping("/utils/all")
    public <R> Object getAllUtil(@RequestParam (required = false) List<String> media) {
    	
    	List<Film> films = filmService.show();
    	
    	List<Radio> radios = radioService.show();
    	
    	List<Podcast> podcasts = podcastservice.show();
    	
    	List<ColPodcast> colpodcasts = colPodRep.findAll();

    	List<Serie> series = serieService.show();
    	
    	List <Saison> saisons = saisonService.show();
    	
    	List<Episode> episodes = episodeService.show();
    	

    	
        if (media == null || media.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "films ",films,"radios", radios, "podcasts", podcasts, "colpodcasts",colpodcasts,"series", 
        					series, "saisons", saisons, "episodes", episodes));
        }
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, media.stream().map(res -> {
            
        	
        	switch (res.toLowerCase()) {
                case "films":
                    return Map.of("films",films);
                case "radios":
                    return Map.of("radios", radios);
                case "podcasts":
                	return Map.of("podcasts", podcasts);
                case "colpodcasts":
                	return Map.of("colpodcasts", colpodcasts);
                case "series":
                	return Map.of("series", series);
                case "saisons":
                	return Map.of("saisons", saisons);
                case "episodes":
                	return Map.of("episodes",episodes);
                default:
                	return Map.of("erreurs", "media inconnue: " + res);
            }
        }).toArray());
    }

	
	/*
	 * 
	 * 
	 * GESTION DES RADIOS
	 * 
	 * 
	 * 
	 */
	
	@Tag(name = "Radios")
	@GetMapping("radios")
	public ResponseEntity<Object> radio_show(){

		return fnc.radio_show();
	}
	
	@Tag(name = "Radios")
	@GetMapping("radios/all/")
	public ResponseEntity<Object> radio_show_page(Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
		
		return fnc.radio_show_page(p, categ, langue, pays);
	}
	
	@Tag(name = "Radios")
	@GetMapping("radios/search/")
	public ResponseEntity<Object> radio_search(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
		
		return fnc.radio_search(s, categ, langue, pays, p);
	}

	@Tag(name = "Radios")
	@GetMapping("radios/{id}")
	public ResponseEntity<Object> radio_show_by_id(@PathVariable Long id){

		return fnc.radio_show_by_id(id);
	}
	
	//LIKE
	
	//AFFICHE LIKE PAR RADIO
	@Tag(name = "Radios")
	@GetMapping("radios/likes/show/likebyRadio/{idRadio}")
	public ResponseEntity<Object> radioLikebyRadio(@PathVariable Long idRadio){
		
		Radio r = radioService.showById(idRadio).get();
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, likeradioService.findByUser(r));
	}
	
	//AFFICHE NOMBRE LIKE PAR RADIO
	@Tag(name = "Radios")
	@GetMapping("radios/like/show/nblikebyRadio/{idRadio}")
	public ResponseEntity<Object> radioNbLike(@PathVariable Long idRadio){
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("nbLike",
				likeradioService.nbretotalLike(radioService.showById(idRadio).get())));
	}
	
	//ADD LIKE
	@Tag(name = "Radios")
	@PostMapping("radios/likes/add/{idRadio}")
	public ResponseEntity<Object> radioAddLike(@PathVariable Long idRadio){
		Radio r = radioService.showById(idRadio).get();
		User u = userService.findCurrentUser();
		
		if(likeradioRep.findByUserAndRadio(u, r).isPresent()) {
			Long id = likeradioRep.findByUserAndRadio(u, r).get().getIdLike();
			
			return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
					likeradioService.removeLike(id) );
		}
		else {
			
			LikeRadio lk = new LikeRadio();
			lk.setRadio(r);
			lk.setUser(u);
			
			return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
					likeradioService.addLike(lk));
		}
	}
	
	//DELETE LIKE
	@Tag(name = "Radios")
	@DeleteMapping("radios/likes/delete/{idLike}")
	public ResponseEntity<Object> radioDelLike(@PathVariable Long id){
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, likeradioService.removeLike(id));
		 
	}
	
	/*
	 * GESTION DES FAVORIES
	 */
	//FAVORIES
	@Tag(name = "Radios")
	@PostMapping("radios/favories/add/{idRadio}")
	public ResponseEntity<Object> radioShowFavorie(@PathVariable Long idRadio){
    
		Radio r = radioService.showById(idRadio).get();
		User u = userService.findCurrentUser();
		
		if(favradioRep.findByUserAndRadio(u, r).isPresent()) {
			Long id = favradioRep.findByUserAndRadio(u, r).get().getIdFav();
			
			return EntityResponse.generateResponse("RETIREZ DES FAVORIES ", HttpStatus.OK, 
					favradioService.remove(id) );
		}
		else {
			
			FavRadio fr = new FavRadio();
			fr.setRadio(r);
			fr.setUser(u);
			
			return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
					favradioService.addFav(fr));
		}
	}
	
	
	@Tag(name = "Radios")
	@GetMapping("radios/favories/all/")
	public ResponseEntity<Object> radioFavorieAll(){

		return EntityResponse.generateResponse("Liste des radios favorie", HttpStatus.OK, 
				favradioService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Radios")
	@DeleteMapping("radios/favories/{idFavorie}")
	public boolean radioDelFavorie(@PathVariable Long idFav){

		return favradioService.remove(idFav);
		
	}
	
	
	/*
     * 
     * ACTEURS
     * 
     */
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs")
	public ResponseEntity<Object> showActor(){

		return fnc.actor_show();
	}
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs/all/")
	public ResponseEntity<Object> actor_show_paging(
			@RequestParam (required = false) List<Long> pays,
			Pageable p){

    	return fnc.actor_show_paging(p, pays);
	}
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs/search/")
	public ResponseEntity<Object> acteur_search(
			@RequestParam (required = false) String s,
			@RequestParam (required = false) List<Long> pays,
			Pageable p){

    	return fnc.actor_search(p, s, pays) ;
	}
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs/{id}")
	public ResponseEntity<Object> showActorById(@PathVariable long id){

		return fnc.actor_show_by_id(id);
	}
    
    /*
     * 
     * 
     * CRUD Directors
     * 
     * 
     */
    
    @Tag(name = "Directeur")
	@GetMapping("directeurs")
	public ResponseEntity<Object> showDir(){

		return fnc.showDir();
	}
    
    @Tag(name = "Directeur")
	@GetMapping("directeurs/all/")
	public ResponseEntity<Object> showDirPaging(Pageable p){

		return fnc.showDirPaging(p);
	}
    
    @Tag(name = "Directeur")
	@GetMapping("directeurs/{id}")
	public ResponseEntity<Object> showDirById(@PathVariable long id){

		return showDirById(id);
	}
	
    /*
	 * 
	 * Langue 
	 * 
	 * 
	 */
    
	@Tag(name = "Langue")
	@GetMapping("langs")
	public ResponseEntity<Object> showLang(){

		return fnc.showLang();
	}
	
	@Tag(name = "Langue")
	@GetMapping("langs/all/")
	public ResponseEntity<Object> showLangPaging(Pageable p){

		return fnc.showLangPaging(p);
	}

	@Tag(name = "Langue")
	@GetMapping("langs/{id}")
	public ResponseEntity<Object> showLangById(@PathVariable Long id){

		return fnc.showLangById(id);
	}
	
	@Tag(name = "Langue")
	@GetMapping("langs/search/")
	public ResponseEntity<Object> showLangByName( 
			@RequestParam String s, Pageable p){

		return fnc.showLangByName(s, p);
	}
	
	/*
	 * 
	 * Podcasteur 
	 * 
	 * 
	 */
	
	@Tag(name = "Podcasteur")
	@GetMapping("podcasteurs")
	public ResponseEntity<Object> podShow(){

		return fnc.podShow();
	}
    
    @Tag(name = "Podcasteur")
	@GetMapping("podcasteurs/all/")
	public ResponseEntity<Object> podPaging(Pageable p){

		return fnc.podPaging(p);
	}
    
    @Tag(name = "Podcasteur")
	@GetMapping("podcasteurs/{id}")
	public ResponseEntity<Object> podShowById(@PathVariable long id){

		return fnc.podShowById(id);
	}
	
	/*
	 * 
	 * Pays
	 * 
	 * 
	 */
	
	@Tag(name = "Pays")
	@GetMapping("pays")
	public ResponseEntity<Object> showPays(){

		return fnc.showPays();
	}
	
	@Tag(name = "Pays")
	@GetMapping("pays/all/")
	public ResponseEntity<Object> showPaysPaging(Pageable p){

		return fnc.showPaysPaging(p);
	}

	@Tag(name = "Pays")
	@GetMapping("pays/{id}")
	public ResponseEntity<Object> showbyIdPays(@PathVariable Long id){

		return fnc.showbyIdPays(id);
	}
	

	/*
	 * 
	 * GENRE DE FILMs ET SERIES
	 * 
	 */
	
	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres")
	public ResponseEntity<Object> showG(){

		return fnc.showG();
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/all/")
	public ResponseEntity<Object> showPage(Pageable p){

		return fnc.showPage(p);
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/search/")
	public ResponseEntity<Object> showByName(
			@RequestParam String s, 
			Pageable p){

		return fnc.showByName(s, p);
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/search/contain/")
	public ResponseEntity<Object> showByNameContain(
			@RequestParam String s, 
			Pageable p){

		return fnc.showByNameContain(s, p);		
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/{id}")
	public ResponseEntity<Object> showbyIdG(@PathVariable Long id){

		return fnc.showbyIdG(id);
	}
	
	/*
	 * 
	 * 
	 * Categorie LiveTv ou Radio
	 * 
	 * 
	 */
	
	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl")
	public ResponseEntity<Object> showCRL(){

		return fnc.showCRL();
	}

	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl/all/")
	public ResponseEntity<Object> showCRLPaging(Pageable p){

		return fnc.showCRLPaging(p);
	}

	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl/{id}")
	public ResponseEntity<Object> showbyIdCRL(@PathVariable Long id){

		return fnc.showbyIdCRL(id);
	}
	
	/*
	 * 
	 * Categorie Podcast
	 * 
	 * 
	 */

	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod")
	public ResponseEntity<Object> showCP(){

		return fnc.showCP();
	}
	
	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod/all/")
	public ResponseEntity<Object> showCP(Pageable p){

		return fnc.showCP(p);
	}

	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod/{id}")
	public ResponseEntity<Object> showbyIdCP(@PathVariable Long id){

		return fnc.showbyIdCP(id);
	}

	
	/*
	 * 
	 * 
	 * COLLECTION DE PODCAST
	 * 
	 * 
	 */
	
	@Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections")
	public ResponseEntity<Object> showCollection(){

		return fnc.showCollection();
	}
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/all/")
	public ResponseEntity<Object> showCollPaging(Pageable p){

		return fnc.showCollPaging(p);
	}
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/search/")
	public ResponseEntity<Object> searchCollection(
		@RequestParam String s, 
		Pageable p){

    	return fnc.searchCollection(s, p);

    }
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/{id}")
	public ResponseEntity<Object> showCollectionById(@PathVariable long id){

		return fnc.showCollectionById(id);
	}
	
    //LIKE
	
  	//AFFICHE LIKE PAR COLLECTION PODCAST
  	@Tag(name = "Podcast Collection")
  	@GetMapping("podcast/collections/likes/bycolPodcast/{idCol}")
  	public ResponseEntity<Object> col_podcast_Likebycol(@PathVariable Long idCol){
  		
  		ColPodcast cl =  colpodservice.showById(idCol).get();
  		
  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,"");
  	}
  	
  	//AFFICHE NOMBRE LIKE PAR COLLECTION PODCAST
  	@Tag(name = "Podcast Collection")
  	@GetMapping("podcasts/likes/nblikebyPodcast/{idColPod}")
  	public ResponseEntity<Object> col_podcast_NbLike(@PathVariable Long idColPod){
  		
  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
  				likecolpodService.nbretotalLike(colpodservice.showById(idColPod).get()));
  	}
  	
  	//ADD LIKE
  	@Tag(name = "Podcast Collection")
  	@PostMapping("podcast/collections/likes/add/{idColPod}")
  	public ResponseEntity<Object> col_podcast_AddLike(@PathVariable Long idColPod){
  		
  		Podcast l = podcastservice.showById(idColPod).get();
  		User u = userService.findCurrentUser();
  		
  		if(likepodRep.findByUserAndPodcast(u, l).isPresent()) {
  			Long id = likepodRep.findByUserAndPodcast(u, l).get().getIdLike();
  			
  			return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
  					likepodService.removeLike(id) );
  		}
  		else {
  			
  			LikePodcast lt = new LikePodcast();
  			lt.setPodcast(l);
  			lt.setUser(u);
  			
  			return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
  					likepodService.addLike(lt));
  		}
  	}
  	
  	//DELETE LIKE
  	@Tag(name = "Podcast Collection")
  	@DeleteMapping("podcast/collections/likes/delete/{idLike}")
  	public ResponseEntity<Object> col_podcast_DelLike(@PathVariable Long id){
  		
  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
  				likepodService.removeLike(id));
  		 
  	}
  	
  	/*
  	 * GESTION DES FAVORIES
  	 */
  	//FAVORIES
  	
  	@Tag(name = "Podcast Collection")
  	@PostMapping("podcast/collections/favories/add/{idColPod}")
  	public ResponseEntity<Object> col_podcast_show_fav(@PathVariable Long idColPod){
      
  		Podcast l = podcastservice.showById(idColPod).get();
  		User u = userService.findCurrentUser();
  		
  		if(favpodRep.findByUserAndPodcast(u, l).isPresent()) {
  			
  			Long id = favpodRep.findByUserAndPodcast(u, l).get().getIdFav();
  			
  			return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
  					favpodService.remove(id) );
  		}
  		else {
  			
  			FavPodcast fl = new FavPodcast();
  			fl.setPodcast(l);
  			fl.setUser(u);
  			
  			return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
  					favpodService.addFav(fl));
  		}
  	}
  	
  	@Tag(name = "Podcast Collection")
  	@GetMapping("podcast/collections/favories/all/")
  	public ResponseEntity<Object> col_podcast_fav_all(){

  		return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
  				favpodService.findByUser(userService.findCurrentUser()));
  	}
  	
  	@Tag(name = "Podcast Collection")
  	@DeleteMapping("podcast/collections/favories/all/{idFavorie}")
  	public ResponseEntity<Object> col_podcast_supp_fav(@PathVariable Long idFav){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
  				favpodService.remove(idFav));
  		
  	}
    
    
	
	/*
	 * GESTION DES PODCASTS
	 */
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasts")
	public ResponseEntity<Object> showP(){

		return fnc.showP();
		
	}

	@Tag(name = "Podcasts")
	@GetMapping("podcasts/all/")
	public ResponseEntity<Object> showPodcastByPage(Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue){
		
		return fnc.showPodcastByPage(p, categ, langue);
	}

	@Tag(name = "Podcasts")
	@GetMapping("podcasts/{id}")
	public ResponseEntity<Object> showbyIdP(@PathVariable Long id){

		return fnc.showbyIdP(id);
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/search/")
	public ResponseEntity<Object> showbyIdP(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue){
		
		return fnc.showbyIdP(s, p, categ, langue);
	}

	
	//LIKE
	
	//AFFICHE LIKE PAR PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/likes/byPodcast/{idPod}")
	public ResponseEntity<Object> podcastLikebyLive(@PathVariable Long idPod){
		
		Podcast l = podcastservice.showById(idPod).get();
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					likepodService.findByPodcast(l));
	}
	
	//AFFICHE NOMBRE LIKE PAR PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/likes/nblikebyPodcast/{idPodcast}")
	public ResponseEntity<Object> podcastNbLike(@PathVariable Long idPodcast){
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likepodService.nbretotalLike(podcastservice.showById(idPodcast).get()));
	}
	
	//ADD LIKE
	@Tag(name = "Podcasts")
	@PostMapping("podcasts/likes/add/{idPod}")
	public ResponseEntity<Object> podcastAddLike(@PathVariable Long idPod){
		
		Podcast l = podcastservice.showById(idPod).get();
		User u = userService.findCurrentUser();
		
		if(likepodRep.findByUserAndPodcast(u, l).isPresent()) {
			Long id = likepodRep.findByUserAndPodcast(u, l).get().getIdLike();
			
			return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
					likepodService.removeLike(id) );
		}
		else {
			
			LikePodcast lt = new LikePodcast();
			lt.setPodcast(l);
			lt.setUser(u);
			
			return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
					likepodService.addLike(lt));
		}
	}
	
	//DELETE LIKE
	@Tag(name = "Podcasts")
	@DeleteMapping("podcasts/likes/delete/{idLike}")
	public ResponseEntity<Object> podcastDelLike(@PathVariable Long id){
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
				likepodService.removeLike(id));
		 
	}
	
	/*
	 * GESTION DES FAVORIES
	 */
	//FAVORIES
	
	@Tag(name = "Podcasts")
	@PostMapping("podcasts/favories/add/{idPod}")
	public ResponseEntity<Object> podcastShowFavorie(@PathVariable Long idPod){
    
		Podcast l = podcastservice.showById(idPod).get();
		User u = userService.findCurrentUser();
		
		if(favpodRep.findByUserAndPodcast(u, l).isPresent()) {
			
			Long id = favpodRep.findByUserAndPodcast(u, l).get().getIdFav();
			
			return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
					favpodService.remove(id) );
		}
		else {
			
			FavPodcast fl = new FavPodcast();
			fl.setPodcast(l);
			fl.setUser(u);
			
			return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
					favpodService.addFav(fl));
		}
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/favories/all/")
	public ResponseEntity<Object> podcastFavorieAll(){

		return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
				favpodService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Podcasts")
	@DeleteMapping("podcasts/favories/all/{idFavorie}")
	public ResponseEntity<Object> podcastDelFavorie(@PathVariable Long idFav){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				favpodService.remove(idFav));
		
	}


	
	/*
	 * GESTION DES FILMS
	 */
	@Tag(name = "Films")
	@GetMapping("films")
	public ResponseEntity<Object> showM(){

		return fnc.showM();
	}
	
	@Tag(name = "Films")
	@GetMapping("films/all/")
	public ResponseEntity<Object> showMovieByPage(
			Pageable p, 
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){

		return fnc.film_show_page(p, genre, langue, pays);

	}
	

	@Tag(name = "Films")
	@GetMapping("films/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable Long id){

		return fnc.film_show_by_id(id);
	}
    
	@Tag(name = "Films")
	@GetMapping("films/search/")
	public ResponseEntity<Object> search(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){

		return fnc.film_search(s, p, genre, langue, pays);
	}
	
////////////////////////////////////////////////////////////////////////
/*
 * DEBUT DU MODUL
 */


		
		//LIKE
		
		//AFFICHE LIKE PAR FILM
		@Tag(name = "Films")
		@GetMapping("films/likes/show/byfilm/{idfilm}")
		public ResponseEntity<Object> filmLikebyLive(@PathVariable Long idfilm){
			
			Film l = filmService.showById(idfilm).get();
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					likefilmService.findByFilm(l));
			
		}
		
		//AFFICHE NOMBRE LIKE PAR PODCAST
		@Tag(name = "Films")
		@GetMapping("films/likes/nblikebyFilm/{idfilm}")
		public ResponseEntity<Object> filmNbLike(@PathVariable Long idfilm){
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					likepodService.nbretotalLike(podcastservice.showById(idfilm).get()));
		}
		
		//ADD LIKE
		@Tag(name = "Films")
		@PostMapping("films/likes/add/{idfilm}")
		public ResponseEntity<Object> filmAddLike(@PathVariable Long idfilm){
			
			Film l = filmService.showById(idfilm).get();
			User u = userService.findCurrentUser();
			
			if(likefilmRep.findByUserAndFilm(u, l).isPresent()) {
				Long id = likefilmRep.findByUserAndFilm(u, l).get().getIdLike();
				
				return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
						likefilmService.removeLike(id) );
			}
			else {
				
				LikeFilm lt = new LikeFilm();
				lt.setFilm(l);
				lt.setUser(u);
				
				return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
						likefilmService.addLike(lt));
			}
		}
		
		//DELETE LIKE
		@Tag(name = "Films")
		@DeleteMapping("films/likes/delete/{idLike}")
		public ResponseEntity<Object> filmDelLike(@PathVariable Long id){
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					likefilmService.removeLike(id));
			 
		}
		
		/*
		 * GESTION DES FAVORIES
		 */
		//FAVORIES
		
		@Tag(name = "Films")
		@PostMapping("films/favories/add/{idfilm}")
		public ResponseEntity<Object> filmShowFavorie(@PathVariable Long idfilm){
	    
			Film l = filmService.showById(idfilm).get();
			User u = userService.findCurrentUser();
			
			if(favfilmRep.findByUserAndFilm(u, l).isPresent()) {
				
				Long id = favfilmRep.findByUserAndFilm(u, l).get().getIdFav();
				
				return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
						favfilmService.remove(id) );
			}
			else {
				
				FavFilm fl = new FavFilm();
				fl.setFilm(l);
				fl.setUser(u);
				
				return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
						favfilmService.addFav(fl));
			}
		}
		
		@Tag(name = "Films")
		@GetMapping("films/favories/all/")
		public ResponseEntity<Object> filmAllFavorie(){

			return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
					favfilmService.findByUser(userService.findCurrentUser()));
		}
		
		@Tag(name = "Films")
		@DeleteMapping("films/favories/delete/{idFavorie}")
		public boolean filmDelFavorie(@PathVariable Long idFavorie){

			return favfilmService.remove(idFavorie);
			
		}
	

/*
 * FIN DU MODULE		
 */
////////////////////////////////////////////////////////////////////////////////////		
	
		
	
	/*
	 * GESTION DES SERIES
	 */
	//Series
	@Tag(name = "Series")
	@GetMapping("series")
	public ResponseEntity<Object> showS(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.show());
	}
	
	@Tag(name = "Series")
	@GetMapping("series/all/")
	public ResponseEntity<Object> showSerieByPage(Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue){
		
		if(genre != null && langue ==null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByGenre(genre, p));
			
		}
		else if (langue != null && genre == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByLangue(langue, p));
		}
		else if (langue != null && genre != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByGenreAndLangue(genre, langue, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showPage(p));
			
		}
	}

	@Tag(name = "Series")
	@GetMapping("series/{id}")
	public ResponseEntity<Object> showbyIdS(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				serieService.showById(id));
	}
	@Tag(name = "Series")
	@GetMapping("series/search/")
	public ResponseEntity<Object> showbyIdS(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue){
		
		if(genre != null && langue ==null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByGenre(s, genre, p));
			
		}
		else if (langue != null && genre==null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByLangue(s, langue, p));
		}
		else if(langue != null && genre !=null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByLangueAndGenre(s, langue, genre, p));
			
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.search(s, p));
			
		}
	}
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	
	//LIKE
	
	//AFFICHE LIKE PAR SERIE
	@Tag(name = "Series")
	@GetMapping("series/likes/show/likebySerie/{idSerie}")
	public ResponseEntity<Object> serieLikebyLive(@PathVariable Long idSerie){
	
	Serie l = serieService.showById(idSerie).get();
	
	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likeserieService.findBySerie(l));
	
	}
	
	//AFFICHE NOMBRE LIKE PAR PODCAST
	@Tag(name = "Series")
	@GetMapping("series/likes/show/nblikebySerie/{idSerie}")
	public ResponseEntity<Object> serieNbLike(@PathVariable Long idSerie){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likeserieService.nbretotalLike(serieService.showById(idSerie).get()));
	}
	
	//ADD LIKE
	@Tag(name = "Series")
	@PostMapping("series/likes/add/{idSerie}")
	public ResponseEntity<Object> serieAddLike(@PathVariable Long idSerie){
	
	Serie s = serieService.showById(idSerie).get();
	User u = userService.findCurrentUser();
	
	if(likeserieRep.findByUserAndSerie(u, s).isPresent()) {
	Long id = likeserieRep.findByUserAndSerie(u, s).get().getIdLike();
	
	return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
	likeserieService.removeLike(id) );
	}
	else {
	
	LikeSerie ls = new LikeSerie();
	ls.setSerie(s);
	ls.setUser(u);
	
	return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
	   likeserieService.addLike(ls));
	}
	}
	
	//DELETE LIKE
	@Tag(name = "Series")
	@DeleteMapping("series/likes/delete/{idLike}")
	public ResponseEntity<Object> serieDelLike(@PathVariable Long idLike){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, likeserieService.removeLike(idLike));
	
	}
	
	/*
	* GESTION DES FAVORIES
	*/
	//FAVORIES
	
	@Tag(name = "Series")
	@PostMapping("series/favories/add/{idSerie}")
	public ResponseEntity<Object> serieShowFavorie(@PathVariable Long idSerie){
	
	Serie l = serieService.showById(idSerie).get();
	User u = userService.findCurrentUser();
	
	if(favserieRep.findByUserAndSerie(u, l).isPresent()) {
	
	Long id = favserieRep.findByUserAndSerie(u, l).get().getIdFav();
	
	return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
	favfilmService.remove(id) );
	}
	else {
	
	FavSerie fl = new FavSerie();
	fl.setSerie(l);
	fl.setUser(u);
	
	return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
	favserieService.addFav(fl));
	}
	}
	
	@Tag(name = "Series")
	@GetMapping("series/favories/all/")
	public ResponseEntity<Object> serieFavorieAll(){
	
	return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
	favserieService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Series")
	@DeleteMapping("series/favories/delete/{idFavorie}")
	public ResponseEntity<Object> serieDelFavorie(@PathVariable Long idFav){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, favserieService.remove(idFav));
	
	}
	
	
	/*
	* FIN DU MODULE		
	*/
	////////////////////////////////////////////////////////////////////////////////////
		

	
	
	/*
	 * GESTION DES EPISODES
	 */
	//Episodes
	@Tag(name = "Episodes")
	@GetMapping("episodes")
    public ResponseEntity<Object> showE(){

		return fnc.showE();
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/all/")
    public ResponseEntity<Object> showE(
    		Pageable p,
    		@RequestParam (required = false) Long serie,
    		@RequestParam (required = false) Long saison,
			@RequestParam (required = false) Long langue){
		
		return fnc.showE(p, serie, saison, langue);
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/{id}")
	public ResponseEntity<Object> showbyIdE(@PathVariable Long id){

		return fnc.showbyIdE(id);
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/search/")
	public ResponseEntity<Object> searchEp(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long serie,
			@RequestParam (required = false) Long saison,
			@RequestParam (required = false) Long langue){
		
		return fnc.searchEp(s, p, serie, saison, langue);
	}

	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	
	//LIKE
	
	//AFFICHE LIKE PAR EPISODE
	@Tag(name = "Episodes")
	@GetMapping("episodes/likes/show/byEpisode/{idEpisode}")
	public ResponseEntity<Object> episodeLikebyLive(@PathVariable Long idEpisode){
	
	Episode l = episodeService.showById(idEpisode).get();
	
	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, likeepisodeService.findByEpisode(l));
	
	}
	
	//AFFICHE NOMBRE LIKE PAR EPISODE
	@Tag(name = "Episodes")
	@GetMapping("episodes/likes/show/nblikebyEpisode/{idEpisode}")
	public ResponseEntity<Object> episodeNbLike(@PathVariable Long idEpisode){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likeepisodeService.nbretotalLike(episodeService.showById(idEpisode).get()));
	}
	
	//ADD LIKE
	@Tag(name = "Episodes")
	@PostMapping("episodes/likes/add/{idEpisode}")
	public ResponseEntity<Object> episodeAddLike(@PathVariable Long idEpisode){
	
	Episode ep = episodeService.showById(idEpisode).get();
	User u = userService.findCurrentUser();
	
	if(likeepisodeRep.findByUserAndEpisode(u, ep).isPresent()) {
	Long id = likeepisodeRep.findByUserAndEpisode(u, ep).get().getIdLike();
	
	return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
	likeepisodeService.removeLike(id) );
	}
	else {
	
	LikeEpisode le = new LikeEpisode();
	le.setEpisode(ep);
	le.setUser(u);
	
	return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
	likeepisodeService.addLike(le));
	}
	}
	
	//DELETE LIKE
	@Tag(name = "Episodes")
	@DeleteMapping("episodes/likes/delete/{idEpisode}")
	public ResponseEntity<Object> episodeDelLike(@PathVariable Long idEpisode){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likeepisodeService.removeLike(idEpisode));
	
	}
	
	/*
	* GESTION DES FAVORIES
	*/
	//FAVORIES
	
	@Tag(name = "Episodes")
	@PostMapping("episodes/favories/add/{idEpisode}")
	public ResponseEntity<Object> episodeShowFavorie(@PathVariable Long idEpisode){
	
	Episode ep = episodeService.showById(idEpisode).get();
	User u = userService.findCurrentUser();
	
	if(favepisodeRep.findByUserAndEpisode(u, ep).isPresent()) {
	
	Long id = favepisodeRep.findByUserAndEpisode(u, ep).get().getIdFav();
	
	return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
	favepisodeService.remove(id) );
	}
	else {
	
	FavEpisode fe = new FavEpisode();
	fe.setEpisode(ep);
	fe.setUser(u);
	
	return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
	favepisodeService.addFav(fe));
	}
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/favories/all/")
	public ResponseEntity<Object> episodeAddFavorie(){
	
	return EntityResponse.generateResponse("Liste des episodes favorie", HttpStatus.OK, 
	favepisodeService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Episodes")
	@DeleteMapping("episodes/favories/delete/{idFavorie}")
	public ResponseEntity<Object> episodeDelFavorie(@PathVariable Long idFav){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				favepisodeService.remove(idFav));
	
	}
	
	
	/*
	 * GESTION DES SAISONS
	 */
	
	//SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/all/")
	public ResponseEntity<Object> showSaisonPage(
			Pageable p,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long serie ){
		
		return fnc.showSaisonPage(p, langue, serie);
	}

	@Tag(name = "Saison")
	@GetMapping("saisons/show/{id}")
	public ResponseEntity<Object> showbyId(@PathVariable Long id){

		return fnc.showbyIdCL(id);
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/search/")
	public ResponseEntity<Object> showbyNameC(
			@RequestParam String s, 
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long serie,
			Pageable p){
		
		return fnc.showbyNameC(s, langue, serie, p);
		
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	
	//LIKE
	
	//AFFICHE LIKE PAR SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/likes/show/bySaison/{idSaison}")
	public ResponseEntity<Object> saisonLikebySaison(@PathVariable Long idSaison){
	
	Saison s = saisonService.showById(idSaison);
	
	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
			likesaisonService.findBySaison(s));
	
	}
	
	//AFFICHE NOMBRE LIKE PAR SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/likes/nblikebySaison/{idSaison}")
	public ResponseEntity<Object> saisonNbLike(@PathVariable Long idSaison){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likesaisonService.nbretotalLike(saisonService.showById(idSaison)));
	
	}
	
	//ADD LIKE
	@Tag(name = "Saison")
	@PostMapping("saisons/likes/add/{idSaison}")
	public ResponseEntity<Object> saisonAddLike(@PathVariable Long idSaison){
	
	Saison s = saisonService.showById(idSaison);
	User u = userService.findCurrentUser();
	
	if(likesaisonRep.findByUserAndSaison(u, s).isPresent()) {
	Long id = likesaisonRep.findByUserAndSaison(u, s).get().getIdLike();
	
	return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
	likesaisonService.removeLike(id) );
	}
	else {
	
	LikeSaison ls = new LikeSaison();
	ls.setSaison(s);
	ls.setUser(u);
	
	return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
	likesaisonService.addLike(ls));
	}
	}
	
	//DELETE LIKE
	@Tag(name = "Saison")
	@DeleteMapping("saisons/likes/delete/{idSaison}")
	public ResponseEntity<Object> saisonDelLike(@PathVariable Long idSaison){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				likeepisodeService.removeLike(idSaison));
	
	}
	
	/*
	* GESTION DES FAVORIES
	*/
	//FAVORIES
	
	@Tag(name = "Saison")
	@PostMapping("saisons/favories/add/{idSaison}")
	public ResponseEntity<Object> saisonAddFavorie(@PathVariable Long idSaison){
	
	Saison s = saisonService.showById(idSaison);
	User u = userService.findCurrentUser();
	
	if(favsaisonRep.findByUserAndSaison(u, s).isPresent()) {
	
	Long id = favsaisonRep.findByUserAndSaison(u, s).get().getIdFavSaison();
	
	return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
	favsaisonService.remove(id) );
	}
	else {
	
	FavSaison fe = new FavSaison();
	fe.setSaison(s);
	fe.setUser(u);
	
	return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
	favsaisonService.addFav(fe));
	}
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/favories/all/")
	public ResponseEntity<Object> saisonAllFavorie(){
	
	return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
	favepisodeService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Saison")
	@DeleteMapping("saisons/favories/delete/{idFavorie}")
	public ResponseEntity<Object> saisonDelFavorie(@PathVariable Long idFav){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				favsaisonService.remove(idFav));
	
	}
	
	
	//Multi Media
	@Tag(name = "Profil Abonne")
	@GetMapping("user/favories/all/")
	
	public ResponseEntity<Object> allFavorite(){
		
		List<FavFilm> film= favfilmService.findByUser(userService.findCurrentUser()); 
		
		List<FavSerie> serie= favserieService.findByUser(userService.findCurrentUser());
		
		List<FavRadio> radio = favradioService.findByUser(userService.findCurrentUser());
		
		List<FavPodcast> podcast = favpodService.findByUser(userService.findCurrentUser());
		
		List<FavLiveTv> livetv = favliveService.findByUser(userService.findCurrentUser());
		
		FavoriteAllResponse fav = new FavoriteAllResponse(film, serie, radio, podcast, livetv );
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fav);
	}
	
	
	/*
	 * 
	 * LES LIVES  DES EVENEMENTS
	 * 
	 * 
	 */

	@Tag(name = "Lives")
	@GetMapping("lives")
	public ResponseEntity<Object> showLives(){

		return fnc.showLives();
	}
	
	@Tag(name = "Lives")
	@GetMapping("lives/all/")
	public ResponseEntity<Object> showLivesByPage(
		Pageable p,
		@RequestParam (required = false) Long categ ){
	
		return fnc.showLivesByPage(p, categ);
	}

	@Tag(name = "Lives")
	@GetMapping("lives/{id}")
	public ResponseEntity<Object> showbyIdLives(@PathVariable Long id){

		return fnc.showbyIdLives(id);
	}

	@Tag(name = "Lives")
	@GetMapping("lives/search/")
	public ResponseEntity<Object> showbyIdLives(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) Long categ ){
	
		return fnc.showbyIdLives(s, p, categ);
	}
	
	
	/*
	 * 
	 * ROUTES LiveTV
	 * 
	 * 
	 */

	@Tag(name = "TV SHOW")
	@GetMapping("tv")
	public ResponseEntity<Object> showTV(){

		return fnc.showL();
	}
	
	@Tag(name = "TV SHOW")
	@GetMapping("tv/find/byname/{name}")
	public ResponseEntity<Object> showTV(@PathVariable String name){

		return fnc.livetv_find_by_name(name);
			
	}
	
	@Tag(name = "TV SHOW")
	@GetMapping("tv/all/")
	public ResponseEntity<Object> showTVPages(Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
		
		return fnc.tv_show_page(p, genre, langue, pays);
	}
	
	@Tag(name = "TV SHOW")
	@GetMapping("tv/search/")
	public ResponseEntity<Object> showTVbyNameContainL(
			@RequestParam String s, Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
			
		return fnc.tv_search(s, p, genre, langue, pays);
	}

	@Tag(name = "TV SHOW")
	@GetMapping("tv/{id}")
	public ResponseEntity<Object> showbyIdLTV(@PathVariable Long id){

		return fnc.showbyIdL(id);
	}
	
	
	
			
			//LIKE
			
			//AFFICHE LIKE PAR 
			@Tag(name = "TV SHOW")
			@GetMapping("tv/likes/show/byLive/{idLive}")
			public ResponseEntity<Object> liveLikebyLive(@PathVariable Long idLive){
				
				LiveTv l = liveService.showById(idLive).get();
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, likeliveService.findByLivetv(l));
			}
			
			//AFFICHE NOMBRE LIKE PAR LIVE TV
			@Tag(name = "TV SHOW")
			@GetMapping("tv/all/nblikebyLiveTv/{idLivetv}")
			public ResponseEntity<Object> liveNbLike(@PathVariable Long idLivetv){
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
						likeliveService.nbretotalLike(liveService.showById(idLivetv).get()));
			}
			
			//ADD LIKE
			@Tag(name = "TV SHOW")
			@PostMapping("tv/add/{idLive}")
			public ResponseEntity<Object> liveAddLike(@PathVariable Long idLive){
				LiveTv l = liveService.showById(idLive).get();
				User u = userService.findCurrentUser();
				
				if(likeliveRep.findByUserAndLivetv(u, l).isPresent()) {
					Long id = likeliveRep.findByUserAndLivetv(u, l).get().getIdLike();
					
					return EntityResponse.generateResponse("VOUS VENEZ DE DISLIKEZ ", HttpStatus.OK, 
							likeliveService.removeLike(id) );
				}
				else {
					
					LikeLivetv lt = new LikeLivetv();
					lt.setLivetv(l);
					lt.setUser(u);
					
					return EntityResponse.generateResponse("VOUS VENEZ DE LIKEZ", HttpStatus.OK, 
							likeliveService.addLike(lt));
				}
			}
			
			//DELETE LIKE
			@Tag(name = "TV SHOW")
			@DeleteMapping("tv/likes/delete/{idLike}")
			public ResponseEntity<Object> liveDelLike(@PathVariable Long id){
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,  likeliveService.removeLike(id));
				 
			}
			
			/*
			 * GESTION DES FAVORIES
			 */
			//FAVORIES
			
			@Tag(name = "TV SHOW")
			@PostMapping("tv/favories/add/{idLive}")
			public ResponseEntity<Object> liveShowFavorie(@PathVariable Long idLive){
	        
				LiveTv l = liveService.showById(idLive).get();
				User u = userService.findCurrentUser();
				
				if(favliveRep.findByUserAndLivetv(u, l).isPresent()) {
					
					Long id = favliveRep.findByUserAndLivetv(u, l).get().getIdFav();
					
					return EntityResponse.generateResponse("RETIRER DES FAVORIES ", HttpStatus.OK, 
							favliveService.remove(id) );
				}
				else {
					
					FavLiveTv fl = new FavLiveTv();
					fl.setLivetv(l);
					fl.setUser(u);
					
					return EntityResponse.generateResponse("AJOUTEZ AUX FAVORIES", HttpStatus.OK, 
							favliveService.addFav(fl));
				}
			}
			
			@Tag(name = "TV SHOW")
			@GetMapping("tv/favories/all/")
			public ResponseEntity<Object> liveAddFavorie(){
	    
				return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
						favliveService.findByUser(userService.findCurrentUser()));
			}
			
			@Tag(name = "TV SHOW")
			@DeleteMapping("tv/favories/show/{idFavorie}")
			public boolean liveDelFavorie(@PathVariable Long idFav){

				return favradioService.remove(idFav);
				
			}
		
			
	/*
	 * 
	 * Controlleur pour les publicitées
	 * 
	 * 
	 */
	
	@Tag(name = "Publicité")
	@GetMapping("pub")
	public ResponseEntity<Object> showPub(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.show());
	}
    
	@Tag(name = "Publicité")
	@GetMapping("pub/all/")
	public ResponseEntity<Object> showPubPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.showPage(p));
		
	}
	
	@Tag(name = "Publicité")
	@GetMapping("pub/search/")
	public ResponseEntity<Object> searchPub(
		@RequestParam String s, 
		Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.findByName(s, p));
	}
    
	@Tag(name = "Publicité")
	@GetMapping("pub/{id}")
	public ResponseEntity<Object> showPubById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.showById(id));
	}
	
	 
    /*
     * 	Slider
     * 
     * 
     */
    
  	@Tag(name = "Slider")
  	@GetMapping("slider")
      public ResponseEntity<Object> showSlider(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.show());
  	}
  	
  	@Tag(name = "Slider")
  	@GetMapping("slider/all/")
      public ResponseEntity<Object> showSliderPaging(Pageable p, 
    		  @RequestParam (required = false) String page ){

  		if(page != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.showPage(p));
		}
		else {

  		  return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.showPageByTarget(page,p));
		}
  	}
  	
  	
    @Tag(name = "Slider")
  	@GetMapping("slider/{id}")
  	public ResponseEntity<Object> slider(@PathVariable Long id){

      	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.showById(id));
  	}

    @Tag(name = "Slider")
  	@GetMapping("slider/search/")
  	public ResponseEntity<Object> sliderByName(
  			@RequestParam String s, 
  			Pageable p){

      	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.showByName(s, p));
  	}
    
    
  
    
    

	/*
	 * 
	 * CRUD CATEG ARTICLE
	 * 
	 * 
	 * 
	 */
	@Tag(name = "Categorie Article")
	@GetMapping("articles/categ")
	public ResponseEntity<Object> cat_article_show(){

		return fnc.cat_article_show();
	}
    
    @Tag(name = "Categorie Article")
	@GetMapping("articles/categ/all/")
	public ResponseEntity<Object> cat_article_show_Paging(Pageable p){

    	return fnc.cat_article_show_Paging(p);
	}
    
    @Tag(name = "Categorie Article")
	@GetMapping("articles/categ/{id}")
	public ResponseEntity<Object> cat_article_show_byid(@PathVariable long id){

    	return fnc.cat_article_show_byid(id);
	}
    
    
    /*
	 * 
	 * CRUD ARTICLE
	 * 
	 * 
	 * 
	 */
    
	@Tag(name = "Article")
	@GetMapping("articles")
	public ResponseEntity<Object> article_show(){

		return fnc.article_show();
	}
    
    
    @Tag(name = "Article")
	@GetMapping("articles/{id}")
	public ResponseEntity<Object> article_show_byid(@PathVariable long id){

    	return fnc.article_show_byid(id);
    	
	}
    
    @Tag(name = "Article")
	@GetMapping("articles/search/")
	public ResponseEntity<Object> showbyNameContain(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) List<Long> categ
			){
		
		return fnc.article_search_filtre(s, categ, p);
	}
    
    @Tag(name = "Article")
	@GetMapping("articles/all/")
	public ResponseEntity<Object> article_show_Paging(
			@RequestParam (required = false) List<Long> categ,
			Pageable p){

    	return fnc.article_show_Paging(categ, p);
	}
  	/*
  	 * 
  	 * OPERATION SUR LES SETTING
  	 * 
  	 */
	
  	@Tag(name = "Setting")
  	@GetMapping("setting/general/{id}")
  	public ResponseEntity<Object> show_general(@PathVariable Long id){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_setting(id));
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/firebase/{id}")
  	public ResponseEntity<Object> show_firebase(@PathVariable Long id){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_firebase(id));
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/admob/{id}")
  	public ResponseEntity<Object> show_admob(@PathVariable Long id){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_admod(id));
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/tmdb/{id}")
  	public ResponseEntity<Object> show_tmdb(@PathVariable Long id){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_TMDB(id));
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/r2cloudflare/{id}")
  	public ResponseEntity<Object> show_r2cloudflare(@PathVariable Long id){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_R2Setting(id));
  	}

  	@Tag(name = "Setting")
  	@GetMapping("setting/social/{id}")
  	public ResponseEntity<Object> show_social(@PathVariable Long id, @Valid @RequestBody SocialSetting s){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_Social(id));
  	}
  	
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/general/show")
  	public ResponseEntity<Object> show_general(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_setting());
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/firebase/show")
  	public ResponseEntity<Object> show_firebase(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_firebase());
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/admob/show")
  	public ResponseEntity<Object> show_admob(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_admod());
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/tmdb/show")
  	public ResponseEntity<Object> show_tmdb(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_TMDB());
  	}
  	
  	@Tag(name = "Setting")
  	@GetMapping("setting/r2cloudflare/show")
  	public ResponseEntity<Object> show_r2cloudflare(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_R2Setting());
  	}

  	@Tag(name = "Setting")
  	@GetMapping("setting/social/show")
  	public ResponseEntity<Object> show_social(){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_Social());
  	}
}


