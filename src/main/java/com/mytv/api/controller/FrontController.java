package com.mytv.api.controller;

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

import com.mytv.api.model.ComEpisode;
import com.mytv.api.model.ComFilm;
import com.mytv.api.model.ComPodcast;
import com.mytv.api.model.ComSaison;
import com.mytv.api.model.ComSerie;
import com.mytv.api.model.FavEpisode;
import com.mytv.api.model.FavFilm;
import com.mytv.api.model.FavLiveTv;
import com.mytv.api.model.FavPodcast;
import com.mytv.api.model.FavRadio;
import com.mytv.api.model.FavSaison;
import com.mytv.api.model.FavSerie;
import com.mytv.api.model.LikeEpisode;
import com.mytv.api.model.LikeFilm;
import com.mytv.api.model.LikeLivetv;
import com.mytv.api.model.LikePodcast;
import com.mytv.api.model.LikeRadio;
import com.mytv.api.model.LikeSaison;
import com.mytv.api.model.LikeSerie;
import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategorieLive;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.ColPodcast;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.model.ressource.Actor;
import com.mytv.api.model.ressource.Director;
import com.mytv.api.model.ressource.Language;
import com.mytv.api.model.ressource.Pays;
import com.mytv.api.repository.ActorRepository;
import com.mytv.api.repository.CatPodcastRepository;
import com.mytv.api.repository.CategorieLiveRepository;
import com.mytv.api.repository.CategoryLrRepository;
import com.mytv.api.repository.CollectionPodcastRepository;
import com.mytv.api.repository.DirectorRepository;
import com.mytv.api.repository.FavEpisodeRepository;
import com.mytv.api.repository.FavFilmRepository;
import com.mytv.api.repository.FavLiveRepository;
import com.mytv.api.repository.FavPodcastRepository;
import com.mytv.api.repository.FavRadioRepository;
import com.mytv.api.repository.FavSaisonRepository;
import com.mytv.api.repository.FavSerieRepository;
import com.mytv.api.repository.LikeEpisodeRepository;
import com.mytv.api.repository.LikeFilmRepository;
import com.mytv.api.repository.LikeLivetvRepository;
import com.mytv.api.repository.LikePodcastRepository;
import com.mytv.api.repository.LikeRadioRepository;
import com.mytv.api.repository.LikeSaisonRepository;
import com.mytv.api.repository.LikeSerieRepository;
import com.mytv.api.response.FavoriteAllResponse;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.ComEpisodeService;
import com.mytv.api.service.ComFilmService;
import com.mytv.api.service.ComPodcastService;
import com.mytv.api.service.ComSaisonService;
import com.mytv.api.service.ComSerieService;
import com.mytv.api.service.FavEpisodeService;
import com.mytv.api.service.FavFilmService;
import com.mytv.api.service.FavLiveService;
import com.mytv.api.service.FavPodcastService;
import com.mytv.api.service.FavRadioService;
import com.mytv.api.service.FavSaisonService;
import com.mytv.api.service.FavSerieService;
import com.mytv.api.service.LikeEpisodeService;
import com.mytv.api.service.LikeFilmService;
import com.mytv.api.service.LikeLiveService;
import com.mytv.api.service.LikePodcastService;
import com.mytv.api.service.LikeRadioService;
import com.mytv.api.service.LikeSaisonService;
import com.mytv.api.service.LikeSerieService;
import com.mytv.api.service.gestMedia.CatPodcastService;
import com.mytv.api.service.gestMedia.CategorieLiveService;
import com.mytv.api.service.gestMedia.CategoryLrService;
import com.mytv.api.service.gestMedia.EpisodeService;
import com.mytv.api.service.gestMedia.GenreService;
import com.mytv.api.service.gestMedia.LangueService;
import com.mytv.api.service.gestMedia.LiveTvSetvice;
import com.mytv.api.service.gestMedia.PaysService;
import com.mytv.api.service.gestMedia.PodcastService;
import com.mytv.api.service.gestMedia.RadioService;
import com.mytv.api.service.gestMedia.SaisonService;
import com.mytv.api.service.gestMedia.SerieService;
import com.mytv.api.service.gestMedia.ServiceFilm;
import com.mytv.api.service.gestUser.WUserService;
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
	private ComPodcastService compodService;
	@Autowired
	private LikePodcastRepository likepodRep;
	@Autowired
	private FavPodcastRepository favpodRep;
	
	//FILM FAV LIKE COM
	@Autowired
	private FavFilmService favfilmService;
	@Autowired
	private LikeFilmService likefilmService;
	@Autowired
	private ComFilmService comfilmService;
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
	private ComSerieService comserieService;
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
	private ComEpisodeService comepisodeService;
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
	private ComSaisonService comsaisonService;
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
    
    @Tag(name = "Ressource: genres, Categories, pays, langues")
    @GetMapping("ressources/all")
    public <R> Object getRessources(@RequestParam (required = false) List<String> resources) {
    	
    	List<Pays> pays = paysService.show();
    	
    	List<Language> Langues = langService.show();
    	
    	List<CategoryRL> CatRL = catLrService.show();
    	
    	List<Genre> genre = genreService.show();

    	List<CatPodcast> CatPodcast = catpodService.show();
    	
    	List <Actor> acteurs = actorRep.findAll();
    	
    	List<Director> directeurs = directorsRep.findAll();
    	
    	List<CategorieLive> categorieLive = categliveService.show();

    	
        if (resources == null || resources.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "pays ",pays,"langues", Langues, "cat_radio_livetv", CatRL, "categlives",categorieLive,"genres", 
        					genre, "catpodcast", CatPodcast, "acteurs", acteurs, "directeurs", directeurs));
        }
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, resources.stream().map(res -> {
            switch (res.toLowerCase()) {
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
    @GetMapping("/medias/all")
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
	 * GESTION DES RADIOS
	 */
	//Radio
	@Tag(name = "Radios")
	@GetMapping("radios")
	public ResponseEntity<Object> showR(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.show());
	}
	
	@Tag(name = "Radios")
	@GetMapping("radios/all/withPaging")
	public ResponseEntity<Object> showRadioPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showPage(p));
	}

	@Tag(name = "Radios")
	@GetMapping("radios/{id}")
	public ResponseEntity<Object> showbyIdR(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showById(id));
	}
	@Tag(name = "Radios")
	@GetMapping("radios/search/{val}")
	public ResponseEntity<Object> showbyNameContain(@PathVariable String val){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.search(val));
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
	@GetMapping("radios/favories/all")
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
	 * GESTION DES LIVES TV 
	 */
	//ROUTES LiveTV
	@Tag(name = "Lives")
	@GetMapping("lives")
	public ResponseEntity<Object> showL(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.show());
	}
	
	@Tag(name = "Lives")
	@GetMapping("lives/all/withPaging")
	public ResponseEntity<Object> showLivePage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showPage(p));
	}

	@Tag(name = "Lives")
	@GetMapping("lives/{id}")
	public ResponseEntity<Object> showbyIdL(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showById(id));
	}
    
	@Tag(name = "Lives")
	@GetMapping("lives/search/{val}")
	public ResponseEntity<Object> showLbyNameContainL(@PathVariable String val){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.search(val));
	}
			
			//LIKE
			
			//AFFICHE LIKE PAR RADIO
			@Tag(name = "Lives")
			@GetMapping("lives/likes/show/byLive/{idLive}")
			public ResponseEntity<Object> liveLikebyLive(@PathVariable Long idLive){
				
				LiveTv l = liveService.showById(idLive).get();
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, likeliveService.findByLivetv(l));
			}
			
			//AFFICHE NOMBRE LIKE PAR LIVE TV
			@Tag(name = "Lives")
			@GetMapping("lives/all/nblikebyLiveTv/{idLivetv}")
			public ResponseEntity<Object> liveNbLike(@PathVariable Long idLivetv){
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
						likeliveService.nbretotalLike(liveService.showById(idLivetv).get()));
			}
			
			//ADD LIKE
			@Tag(name = "Lives")
			@PostMapping("lives/add/{idLive}")
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
			@Tag(name = "Lives")
			@DeleteMapping("lives/likes/delete/{idLike}")
			public ResponseEntity<Object> liveDelLike(@PathVariable Long id){
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,  likeliveService.removeLike(id));
				 
			}
			
			/*
			 * GESTION DES FAVORIES
			 */
			//FAVORIES
			
			@Tag(name = "Lives")
			@PostMapping("lives/favories/add/{idLive}")
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
			
			@Tag(name = "Lives")
			@GetMapping("lives/favories/all")
			public ResponseEntity<Object> liveAddFavorie(){
	    
				return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
						favliveService.findByUser(userService.findCurrentUser()));
			}
			
			@Tag(name = "Lives")
			@DeleteMapping("lives/favories/show/{idFavorie}")
			public boolean liveDelFavorie(@PathVariable Long idFav){

				return favradioService.remove(idFav);
				
			}
		

	
	
	/*
	 * GESTION DES PODCASTS
	 */
	//Podcast
	@Tag(name = "Podcasts")
	@GetMapping("podcasts")
	public ResponseEntity<Object> showP(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.show());
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/all/withPaging")
	public ResponseEntity<Object> showPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showPage(p));
	}

	@Tag(name = "Podcasts")
	@GetMapping("podcasts/{id}")
	public ResponseEntity<Object> showbyIdP(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showById(id));
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/search/{val}")
	public ResponseEntity<Object> showbyIdP(@PathVariable String val){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.search(val));
	}
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/comments/all")
	public ResponseEntity<Object> podcastShowAllComment(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, compodService.show() );
		
	}
	
	//AFFICHE COMMENTAIRE D UN PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/comments/byId/{idPod}")
	public ResponseEntity<Object> podcastShowCommentById(@PathVariable Long idPod){
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				compodService.findByPodcast(podcastservice.showById(idPod).get())) ;
		
	}
	
	//ADD COMMENT
	@Tag(name = "Podcasts")
	@PostMapping("podcasts/comments/add/{idPod}")
	public ResponseEntity<Object> LiveAddComment(@PathVariable Long idPod, @RequestBody String com){
		
		Podcast l = podcastservice.showById(idPod).get();
		User u = userService.findCurrentUser();
		ComPodcast comp = new ComPodcast();
		comp.setUser(u);
		comp.setPodcast(l);
		comp.setContenu(com);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				compodService.addCom(comp));
		
	}
	
	@Tag(name = "Podcasts")
	@DeleteMapping("podcasts/comments/add/{idCom}")
	public ResponseEntity<Object> LiveDelComment(@PathVariable Long idCom){

		return EntityResponse.generateResponse("Comentaire Supprimé avec succès", HttpStatus.OK, 
				compodService.remove(idCom));
		
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
	@GetMapping("podcasts/favories/all")
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
	//Films
	@Tag(name = "Films")
	@GetMapping("films")
	public ResponseEntity<Object> showM(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					filmService.show());
	}
	
	@Tag(name = "Films")
	@GetMapping("films/all/withPaging")
	public ResponseEntity<Object> showMovieWithPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				filmService.showPages(p));
	}

	@Tag(name = "Films")
	@GetMapping("films/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				filmService.showById(id));
	}
    
	@Tag(name = "Films")
	@GetMapping("films/search/contain/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				filmService.showByNameContaining(name));
	}
	
////////////////////////////////////////////////////////////////////////
/*
 * DEBUT DU MODUL
 */
	//COM
		//AFFICHE TOUS LES COMMENTAIRES
		@Tag(name = "Films")
		@GetMapping("films/comments/all")
		public ResponseEntity<Object> filmShowAllComment(){

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
						comfilmService.show() );
			
		}
		
		//AFFICHE COMMENTAIRE D UN FILM
		@Tag(name = "Films")
		@GetMapping("films/comments/show/byIdFilm/{idFilm}")
		public ResponseEntity<Object> filmShowCommentById(@PathVariable Long idFilm){
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					comfilmService.findByFilm(filmService.showById(idFilm).get()));
			
		}
		
		//ADD COMMENT
		@Tag(name = "Films")
		@PostMapping("films/comments/add/{idFilm}")
		public ResponseEntity<Object> filmAddComment(@PathVariable Long idFilm, @RequestBody String com){
			
			Film f = filmService.showById(idFilm).get();
			User u = userService.findCurrentUser();
			ComFilm comp = new ComFilm();
			comp.setUser(u);
			comp.setFilm(f);
			comp.setContenu(com);
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					comfilmService.addCom(comp));
			
		}
		
		@Tag(name = "Films")
		@DeleteMapping("films/comments/delete/{idCom}")
		public ResponseEntity<Object> filmDelComment(@PathVariable Long idCom){

			return EntityResponse.generateResponse("Comentaire Supprimé avec succès", HttpStatus.OK, 
					comfilmService.remove(idCom));
			
		}
		
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
		@GetMapping("films/favories/all")
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
	@GetMapping("series/all/withPaging")
	public ResponseEntity<Object> showSeriePages(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				serieService.showPage(p));
	}


	@Tag(name = "Series")
	@GetMapping("series/{id}")
	public ResponseEntity<Object> showbyIdS(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				serieService.showById(id));
	}
	@Tag(name = "Series")
	@GetMapping("series/search/{val}")
	public ResponseEntity<Object> showbyIdS(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					serieService.search(name));
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Series")
	@GetMapping("series/comments/all")
	public ResponseEntity<Object> serieShowAllComment(){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				comserieService.show());
	
	}
	
	//AFFICHE COMMENTAIRE D UNE SERIE
	@Tag(name = "Series")
	@GetMapping("series/comments/show/byId/{idSerie}")
	public ResponseEntity<Object> serieShowCommentById(@PathVariable Long idSerie){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				comserieService.findBySerie(serieService.showById(idSerie).get())) ;
	
	}
	
	//ADD COMMENT
	@Tag(name = "Series")
	@PostMapping("series/comment/all/{idSerie}")
	public ResponseEntity<Object> serieAddComment(@PathVariable Long idSerie, @RequestBody String com){
	
	Serie f = serieService.showById(idSerie).get();
	User u = userService.findCurrentUser();
	ComSerie comp = new ComSerie();
	comp.setUser(u);
	comp.setSerie(f);
	comp.setContenu(com);
	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				comserieService.addCom(comp));
	
	}
	
	@Tag(name = "Series")
	@DeleteMapping("series/comments/delete/{idCom}")
	public ResponseEntity<Object> serieDelComment(@PathVariable Long idCom){
	
	return EntityResponse.generateResponse("Comentaire Supprimé avec succès", HttpStatus.OK, 
	comserieService.remove(idCom));
	
	}
	
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
	@GetMapping("series/favories/all")
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

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.show());
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/all/withPagi")
    public ResponseEntity<Object> showEpisodeWithPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showPage(p));
	}

	@Tag(name = "Episodes")
	@GetMapping("episodes/{id}")
	public ResponseEntity<Object> showbyIdE(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showById(id));
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/search/{val}")
	public ResponseEntity<Object> showbyIdE(@PathVariable String val){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.search(val));
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Episodes")
	@GetMapping("episodes/comments/All")
	public ResponseEntity<Object> episodeShowAllComment(){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, comepisodeService.show());
	
	}
	
	//AFFICHE COMMENTAIRE D UN EPISODE
	@Tag(name = "Episodes")
	@GetMapping("episodes/comments/show/byId/{idEpisode}")
	public ResponseEntity<Object> episodeShowCommentById(@PathVariable Long idEpisode){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				comepisodeService.findByEpisode(episodeService.showById(idEpisode).get()));
	
	}
	
	//ADD COMMENT
	@Tag(name = "Episodes")
	@PostMapping("episodes/comments/add/{idEpisode}")
	public ResponseEntity<Object> episodeAddComment(@PathVariable Long idEpisode, @RequestBody String com){
	
	Episode f = episodeService.showById(idEpisode).get();
	User u = userService.findCurrentUser();
	ComEpisode comp = new ComEpisode();
	
	comp.setUser(u);
	comp.setEpisode(f);
	comp.setContenu(com);
	
	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
			 comepisodeService.addCom(comp));
	
	}
	
	@Tag(name = "Episodes")
	@DeleteMapping("episodes/comments/delete/{idCom}")
	public ResponseEntity<Object> episodeDelComment(@PathVariable Long idCom){
	
	return EntityResponse.generateResponse("Comentaire Supprimé avec succès", HttpStatus.OK, 
	comepisodeService.remove(idCom));
	
	}
	
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
	@GetMapping("episodes/Favories/all")
	public ResponseEntity<Object> episodeAddFavorie(){
	
	return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
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
	@GetMapping("saisons/all")
    public ResponseEntity<Object> showSaison(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				saisonService.show());
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/all/whithPaging")
    public ResponseEntity<Object> showSaisonWithPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				saisonService.showPage(p));
	}

	@Tag(name = "Saison")
	@GetMapping("saisons/show/{id}")
	public ResponseEntity<Object> showbyId(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					saisonService.showById(id));
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/search/{val}")
	public ResponseEntity<Object> showbyIdSaison(@PathVariable String val){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				saisonService.search(val));
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Saison")
	@GetMapping("saisons/comments/all")
	public ResponseEntity<Object> saisonShowAllComment(){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				comsaisonService.show());
	
	}
	
	//AFFICHE COMMENTAIRE D UNE SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/comments/show/byId/{idSaison}")
	public ResponseEntity<Object> saisonShowCommentById(@PathVariable Long idSaison){
	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				comsaisonService.findBySaison(saisonService.showById(idSaison)));
	
	}
	
	//ADD COMMENT
	@Tag(name = "Saison")
	@PostMapping("saisons/comments/add/{idSaison}")
	public ResponseEntity<Object> saisonAddComment(@PathVariable Long idSaison, @RequestBody String com){
	
	Saison s = saisonService.showById(idSaison);
	User u = userService.findCurrentUser();
	ComSaison comp = new ComSaison();
	
	comp.setUser(u);
	comp.setSaison(s);
	comp.setContenu(com);
	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
			comsaisonService.addCom(comp));
	
	}
	
	@Tag(name = "Saison")
	@DeleteMapping("saisons/comments/delete/{idCom}")
	public ResponseEntity<Object> saisonDelComment(@PathVariable Long idCom){
	
	return EntityResponse.generateResponse("Comentaire Supprimé avec succès", HttpStatus.OK, 
	comsaisonService.remove(idCom));
	
	}
	
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
	@GetMapping("saisons/favories/all")
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
	@GetMapping("user/favories/all")
	
	public ResponseEntity<Object> allFavorite(){
		
		List<FavFilm> film= favfilmService.findByUser(userService.findCurrentUser()); 
		
		List<FavSerie> serie= favserieService.findByUser(userService.findCurrentUser());
		
		List<FavRadio> radio = favradioService.findByUser(userService.findCurrentUser());
		
		List<FavPodcast> podcast = favpodService.findByUser(userService.findCurrentUser());
		
		List<FavLiveTv> livetv = favliveService.findByUser(userService.findCurrentUser());
		
		FavoriteAllResponse fav = new FavoriteAllResponse(film, serie, radio, podcast, livetv );
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fav);
	}
}
