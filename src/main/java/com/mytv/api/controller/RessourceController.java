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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.dto.StatusDTO;
import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.service.EpisodeService;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.service.ServiceFilm;
import com.mytv.api.intervenant.Actor;
import com.mytv.api.intervenant.ActorRepository;
import com.mytv.api.intervenant.Director;
import com.mytv.api.intervenant.DirectorRepository;
import com.mytv.api.live.model.Live;
import com.mytv.api.live.service.LiveService;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.service.LiveTvSetvice;
import com.mytv.api.payment.service.TransactionService;
import com.mytv.api.podcast.CatPodcast;
import com.mytv.api.podcast.CatPodcastService;
import com.mytv.api.podcast.ColPodcast;
import com.mytv.api.podcast.CollectionPodcastRepository;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.service.PodcastService;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.service.RadioService;
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
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.service.SaisonService;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.service.SerieService;
import com.mytv.api.subscription.service.SubscriptionService;
import com.mytv.api.user.service.WUserService;
import com.mytv.api.util.Publicite;
import com.mytv.api.util.PubliciteService;
import com.mytv.api.util.Slider;
import com.mytv.api.util.SliderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class RessourceController {

	@Autowired
	PubliciteService pubService;
	
	@Autowired
	TransactionService transService;
	
	@Autowired
    SubscriptionService subsService;
	
	@Autowired
	private RadioService radioService;
	
	@Autowired
	WUserService userService;
	
	//Pour le lives tv
	@Autowired
	private CategorieLiveService categliveService;
	
	//Pour le lives tv
	@Autowired
	private LiveTvSetvice liveService;
	
	//Pour les lives evenement 
	@Autowired
	private LiveService lService;
	
	@Autowired
    private SliderService sliderService;
	
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
    
    @Autowired
    private SaisonService saisonService;
    
    @Autowired
    private ActorRepository actorRep;
    
    @Autowired
    private DirectorRepository directorsRep;
    
    @Autowired
    private CollectionPodcastRepository colPodRep;
    
	
	
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
    
	
	@Tag(name = "Publicité")
	@PostMapping("pub/create")
	public ResponseEntity<Object> createPub(
			@Valid @RequestBody Publicite p){

    	
		if(pubService.findByName(p.getName()) != null ) {
		
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette publicité existe déja");
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, pubService.create(p));
		}
	}
    
	
	@Tag(name = "Publicité")
	@PutMapping("pub/update/{id}")
	public ResponseEntity<Object> updatePub(@PathVariable Long id, @Valid @RequestBody Publicite p){

		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.update(id, p ));
		
	}
	
	@Tag(name = "Publicité")
	@PutMapping("pub/update/status/{id}")
	public ResponseEntity<Object> updatePub(@PathVariable Long id,
			@Valid @RequestBody StatusDTO status){

		Publicite p = pubService.showById(id).get();
		p.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.update(id, p ));
		
	}
    
	@Tag(name = "Publicité")
	@DeleteMapping("pub/delete/{id}")
	public ResponseEntity<Object> deletePub(@PathVariable Long id){
    	pubService.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");
		
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
      public ResponseEntity<Object> showSliderPaging(Pageable p){

  		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.showPage(p));
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

      @Tag(name = "Slider")
  	@PostMapping(path="slider/create")
  	public ResponseEntity<Object> createSlider(
  			@Valid @RequestBody Slider slider) {

  		if(sliderService.findByName(slider.getName()) != null) {
  			
  			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Ce slider existe déja");
  		}
  		else {
  			
  			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, sliderService.create(slider));
  		}


  	}

    @Tag(name = "Slider")
  	@PutMapping(path="slider/update/{id}")
  	public ResponseEntity<Object> sliderUpdate(
  			@PathVariable Long id,
  			@Valid @RequestBody Slider slider){

  		//Save du tout
  		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, sliderService.upadte(id, slider));

  	}

    @Tag(name = "Slider")
  	@PutMapping(path="slider/update/status/{id}")
  	public ResponseEntity<Object> sliderUpdate(
  			@PathVariable Long id,
  			@Valid @RequestBody StatusDTO status){

    	Slider slider = sliderService.showById(id).get();
    	slider.setStatus(status.getStatus());
    	return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, sliderService.upadte(id, slider));

  	}
    @Tag(name = "Slider")
  	@DeleteMapping(path="slider/delete/{id}")
  	public ResponseEntity<Object> sliderDelete (@PathVariable Long id) {

    	sliderService.delete(id);

  		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
  	}
	
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
    @GetMapping("/medias/all")
    public <R> Object getAllMedia(@RequestParam (required = false) List<String> media) {
    	
    	
    	List<LiveTv> livetv = liveService.show() ;
    	
    	List<Live> lives = lService.show();
    	
    	List<Film> films = filmService.show();
    	
    	List<Radio> radios = radioService.show();
    	
    	List<Podcast> podcasts = podcastservice.show();
    	
    	List<ColPodcast> colpodcasts = colPodRep.findAll();

    	List<Serie> series = serieService.show();
    	
    	List <Saison> saisons = saisonService.show();
    	
    	List<Episode> episodes = episodeService.show();
    	

    	
        if (media == null || media.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "films",films,"radios", radios, "podcasts", podcasts, "colpodcasts", colpodcasts,"series", 
        					series, "saisons", saisons, "episodes", episodes, "livetv", livetv, "live", lives ));
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
                case "livetv":
                	return Map.of("livetv",livetv);
                case "lives":
                	return Map.of("lives",lives);
                default:
                	return Map.of("erreurs", "media inconnue: " + res);
            }
        }).toArray());
    }
    
    
    /*
     * 
     * Ressources stats
     */
    
    //Stats pour les ressoures sans media
    @Tag(name = "Ressource")
    @GetMapping("ressources/stats/all")
    public <R> Object getStatsRessources(@RequestParam (required = false) List<String> resources) {
    	
    	
    	
    	int users = userService.retrieveAllUserList().stream().filter(f -> f.getRole() == "ABONNE").toList().size();
    	
    	int sliders = sliderService.show().size();
    	
    	int pays = paysService.show().size();
    	
    	int Langues = langService.show().size();
    	
    	int CatRL = catLrService.show().size();
    	
    	int genre = genreService.show().size();

    	int CatPodcast = catpodService.show().size();
    	
    	int acteurs = actorRep.findAll().size();
    	
    	int directeurs = directorsRep.findAll().size();
    	
    	int categorieLive = categliveService.show().size();

    	
        if (resources == null || resources.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "abonne", users, "sliders", sliders, "pays",pays,"langues", Langues, "cat_radio_livetv", CatRL, "categlives",categorieLive,"genres", 
        					genre, "catpodcast", CatPodcast, "acteurs", acteurs, "directeurs", directeurs));
        }
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, resources.stream().map(res -> {
            switch (res.toLowerCase()) {
                case "pays":
                    return Map.of("pays",pays);
                case "langues":
                    return Map.of("langues", Langues);
                case "abonne":
                    return Map.of("abonne",users);
                case "sliders":
                    return Map.of("sliders", sliders);
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
    
    
    @Tag(name = "Ressource")
    @GetMapping("/medias/stats/all")
    public <R> Object getStatsAllMedia(@RequestParam (required = false) List<String> media) {
    	
    	int livetv = liveService.show().size() ;
    	
    	int lives = lService.show().size();
    	
    	int films = filmService.show().size() ;
    	
    	int radios = radioService.show().size();
    	
    	int podcasts = podcastservice.show().size();
    	
    	int colpodcasts = colPodRep.findAll().size();

    	int series = serieService.show().size();
    	
    	int saisons = saisonService.show().size();
    	
    	int episodes = episodeService.show().size();
    	

    	
        if (media == null || media.isEmpty()) {
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			
        			Map.of( "films",films,"radios", radios, "podcasts", podcasts, "colpodcasts",colpodcasts,"series", 
        					series, "saisons", saisons, "episodes", episodes, "livetv", livetv, "live", lives));
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
                case "livetv":
                	return Map.of("livetv",livetv);
                case "lives":
                	return Map.of("lives",lives);
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
        			
        			Map.of( "films",films,"radios", radios, "podcasts", podcasts, "colpodcasts",colpodcasts,"series", 
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
     * 
     * 
     * 
     * 
     * 
     * Transaction
     * 
     * 
     * 
     * 
     * 
     */
    //
	
	@Tag(name = "Transaction (Down)")
	@GetMapping("transaction")
	public ResponseEntity<Object> abList(){
		
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, subsService.show());
		
	}
	
	@Tag(name = "Transaction (Down)")
	@PostMapping("transaction/{id}")
	public ResponseEntity<Object> abcreate(){
		
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, subsService.show());
		
	}
	
	@Tag(name = "Transaction (Down)")
	@PostMapping("transaction/cancel")
	public ResponseEntity<Object> abupdate(){
		
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, subsService.show());
		
	}
	
	@Tag(name = "Transaction (Down)")
	@PostMapping("transaction/delete/{id}")
	public ResponseEntity<Object> abDelete(@PathVariable Long id){
		
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, subsService.delete(id));
		
	}
	

	
}
