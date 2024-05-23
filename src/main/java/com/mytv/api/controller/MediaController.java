package com.mytv.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.accessanalyzer.model.ResourceNotFoundException;
import com.mytv.api.aws.AmazonS3ServiceImpl;
import com.mytv.api.aws.FileMeta;
import com.mytv.api.aws.FileMetaRepository;
import com.mytv.api.aws.MetadataService;
import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategorieLive;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.ColPodcast;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.Live;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.ressource.Actor;
import com.mytv.api.model.ressource.Director;
import com.mytv.api.model.ressource.Language;
import com.mytv.api.model.ressource.Pays;
import com.mytv.api.model.util.Slider;
import com.mytv.api.repository.ActorRepository;
import com.mytv.api.repository.CatPodcastRepository;
import com.mytv.api.repository.CategorieLiveRepository;
import com.mytv.api.repository.CategoryLrRepository;
import com.mytv.api.repository.CollectionPodcastRepository;
import com.mytv.api.repository.DirectorRepository;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.gestMedia.CatPodcastService;
import com.mytv.api.service.gestMedia.CategorieLiveService;
import com.mytv.api.service.gestMedia.CategoryLrService;
import com.mytv.api.service.gestMedia.EpisodeService;
import com.mytv.api.service.gestMedia.GenreService;
import com.mytv.api.service.gestMedia.LangueService;
import com.mytv.api.service.gestMedia.LiveService;
import com.mytv.api.service.gestMedia.LiveTvSetvice;
import com.mytv.api.service.gestMedia.PaysService;
import com.mytv.api.service.gestMedia.PodcastService;
import com.mytv.api.service.gestMedia.RadioService;
import com.mytv.api.service.gestMedia.SaisonService;
import com.mytv.api.service.gestMedia.SerieService;
import com.mytv.api.service.gestMedia.ServiceFilm;
import com.mytv.api.service.ressource.SliderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")

@SecurityRequirement(name = "bearerAuth")
public class MediaController {

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private AmazonS3ServiceImpl awsService;

	@Autowired
    private FileMetaRepository fileMetaRep;

	@Autowired
	private RadioService radioService;
	
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
    private SliderService sliderService;
	
	
	@Autowired
	private CategoryLrRepository catlrRep;
    @Autowired
    private CatPodcastRepository catPodRep;
    
    @Autowired
    private CategorieLiveRepository catLiveRep;
    
    
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
     * Collection Podcast
     * 
     * */
    
    //CRUD Podcast Collections
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections")
	public ResponseEntity<Object> showCollection(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findAll());
	}
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/all/")
	public ResponseEntity<Object> showCollPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findAll(p));
	}
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/search/")
	public ResponseEntity<Object> searchCollection(
		@RequestParam String s, 
		Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findAll(p));
	}
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/{id}")
	public ResponseEntity<Object> showCollectionById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findById(id));
	}
    
    @Tag(name = "Podcast Collection")
	@PostMapping("podcast/collections/create")
	public ResponseEntity<Object> createCollection(@Valid @RequestBody ColPodcast r){

    	r.setName(r.getName().toLowerCase());
		if(colPodRep.findByName(r.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette collection existe déja");
		}
		else {
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, colPodRep.save(r));
		}
	}
    
    @Tag(name = "Podcast Collection")
	@PutMapping("podcast/collections/update/{id}")
	public ResponseEntity<Object> updateCollection(@PathVariable Long id, @Valid @RequestBody ColPodcast a){
    	a.setIdColPd(id);
    	a.setName(a.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.save(a));
		
	}
    
    
    @Tag(name = "Podcast Collection")
	@DeleteMapping("podcast/collections/delete/{id}")
	public ResponseEntity<Object> deleteCollection(@PathVariable Long id){
    	colPodRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");
		
	}
    
    
    /*
     * 
     * CRUD acteurs
     * 
     */
    //
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs")
	public ResponseEntity<Object> showActor(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.findAll());
	}
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs/all/")
	public ResponseEntity<Object> showActorPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.findAll(p));
	}
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs/{id}")
	public ResponseEntity<Object> showActorById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.findById(id));
	}
    
    @Tag(name = "Acteur")
	@PostMapping("acteurs/create")
	public ResponseEntity<Object> createActor(@Valid @RequestBody Actor a){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, actorRep.save(a));
	}
    
    @Tag(name = "Acteur")
	@PutMapping("acteurs/update/{id}")
	public ResponseEntity<Object> updateActor(@PathVariable Long id, @Valid @RequestBody Actor a){

    	a.setIdActor(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.save(a));
		
	}
    
    
    @Tag(name = "Acteur")
	@DeleteMapping("acteurs/delete/{id}")
	public ResponseEntity<Object> deleteActor(@PathVariable Long id){
    	actorRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, " ");
		
	}
    
    //
    
    //CRUD Directors
    
    @Tag(name = "Directeur")
	@GetMapping("directeurs")
	public ResponseEntity<Object> showDir(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.findAll());
	}
    
    @Tag(name = "Directeur")
	@GetMapping("directeurs/all/")
	public ResponseEntity<Object> showDirPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.findAll(p));
	}
    
    @Tag(name = "Directeur")
	@GetMapping("directeurs/{id}")
	public ResponseEntity<Object> showDirById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.findById(id));
	}
    
    @Tag(name = "Directeur")
	@PostMapping("directeurs/create")
	public ResponseEntity<Object> createDir(@Valid @RequestBody Director a){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, directorsRep.save(a));
	}
    
    @Tag(name = "Directeur")
	@PutMapping("directeurs/update/{id}")
	public ResponseEntity<Object> updateDirecteur(@PathVariable Long id, @Valid @RequestBody Director a){
    	
    	a.setIdDirector(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.save(a));
		
	}
    
    @Tag(name = "Directeur")
	@DeleteMapping("directeurs/delete/{id}")
	public ResponseEntity<Object> deleteDir(@PathVariable Long id){
    	directorsRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");
		
	}
    
    
    
    
	//private final String asset ="/RESSOURCES/IMG/";

	//Langue

	@Tag(name = "Langue")
	@GetMapping("langs")
	public ResponseEntity<Object> showLang(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.show());
	}
	
	@Tag(name = "Langue")
	@GetMapping("langs/all/")
	public ResponseEntity<Object> showLangPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
				
				langService.showPage(p)); 
	}

	@Tag(name = "Langue")
	@GetMapping("langs/{id}")
	public ResponseEntity<Object> showLangById(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.showById(id));
	}

	@Tag(name = "Langue")
	@PutMapping("lang/update/{id}")
	public ResponseEntity<Object> updateLang(@PathVariable Long id,@Valid @RequestBody Language r){
		r.setName(r.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.upadte(id, r));

	}

	@Tag(name = "Langue")
	@PostMapping(path="lang/create")
	public ResponseEntity<Object> createLang(@Valid @RequestBody Language u) {
		
		u.setName(u.getName().toLowerCase());
		if( langService.showByName(u.getName()) != null ) {
			
			
			return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST , "Cette langue existe déja");
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED , langService.create(u));
		}
	}

	
	@Tag(name = "Langue")
	@DeleteMapping(path="langs/delete/{id}")
	public ResponseEntity<Object> delete (@PathVariable Long id) {

		langService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	@Tag(name = "Langue")
	@GetMapping("langs/search/")
	public ResponseEntity<Object> showLangByName( 
			@RequestParam String s, Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.showByName(s, p));
	}

	//Pays
	@Tag(name = "Pays")
	@PostMapping("pays/create")

	public ResponseEntity<Object> createPays(@Valid @RequestBody Pays u) {
		u.setName(u.getName().toLowerCase());
		if(paysService.findByname(u.getName()) != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CONFLICT, "Ce nom de pays existe déja ");
		}
		else {
			
		 return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, paysService.create(u));
		 
		}
	}

	@Tag(name = "Pays")
	@GetMapping("pays")
	public ResponseEntity<Object> showPays(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.show());
	}
	
	@Tag(name = "Pays")
	@GetMapping("pays/all/")
	public ResponseEntity<Object> showPaysPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
				paysService.showPage(p));
	}


	@Tag(name = "Pays")
	@GetMapping("pays/{id}")
	public ResponseEntity<Object> showbyIdPays(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.showById(id));
	}

	@Tag(name = "Pays")
	@PutMapping("pays/update/{id}")
	public ResponseEntity<Object> updatePays(@PathVariable Long id,@Valid @RequestBody Pays u){

		u.setName(u.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.upadte(id, u));

	}

	@Tag(name = "Pays")
	@DeleteMapping(path="pays/delete/{id}")
	public ResponseEntity<Object> deletePays (@PathVariable Long id) {

		paysService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , true);
	}


	
	/*
	 * 
	 * GENRE DE FILMs ET SERIES
	 * 
	 */
	
	@Tag(name = "Genre FILM SERIE")
	@PostMapping(path="genres/create")

	public ResponseEntity<Object> create(@Valid @RequestBody Genre g) {
		
		g.setName(g.getName().toLowerCase());
		if(!genreService.findByNameContain(g.getName()).isEmpty()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.CONFLICT, "Ce genre existe déja");
		}
		
		else {
			
			g.getName().toUpperCase();
		    return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, genreService.create(g));
		
		}
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres")
	public ResponseEntity<Object> showG(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.show());
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/all/")
	public ResponseEntity<Object> showPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showByPages(p));
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/search/")
	public ResponseEntity<Object> showByName(
			@RequestParam String s, 
			Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showByName(s, p));
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/search/contain/")
	public ResponseEntity<Object> showByNameContain(
			@RequestParam String s, 
			Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.findByNameContain(s, p));
		
	}


	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/{id}")
	public ResponseEntity<Object> showbyIdG(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showById(id).orElseThrow(() -> new ResourceNotFoundException("aucune donne avec id= " + id)));
	}

	@Tag(name = "Genre FILM SERIE")
	@PutMapping("genres/update/{id}")
	public ResponseEntity<Object> updateG(@PathVariable Long id,@Valid @RequestBody Genre g){
		g.setName(g.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.upadte(id, g));

	}

	@Tag(name = "Genre FILM SERIE")
	@DeleteMapping(path="genres/delete/{id}")
	public ResponseEntity<Object> deleteG (@PathVariable Long id) {

		genreService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}



	//Categorie LiveTv ou Radio

	@Tag(name = "Categorie RADIO LIVE ")
	@PostMapping(path="catrl/create")
	public ResponseEntity<Object> createCRL(@Valid @RequestBody CategoryRL u) {
		
		u.setName(u.getName().toLowerCase());
		if(catlrRep.findByName(u.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette categorie existe déja");
		}
		else {
			
		
		    return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, catLrService.create(u));
		
		}
	}


	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl")
	public ResponseEntity<Object> showCRL(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.show());
	}

	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl/all/")
	public ResponseEntity<Object> showCRLPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
				catLrService.showPaging(p));
	}

	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl/{id}")
	public ResponseEntity<Object> showbyIdCRL(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.showById(id));
	}


	@Tag(name = "Categorie RADIO LIVE ")
	@PutMapping(path="catrl/update/{id}")
	public ResponseEntity<Object> updateCRL(
			@PathVariable Long id,
			@Valid @RequestBody CategoryRL u) {

		u.setName(u.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.upadte(id, u));

	}


	@Tag(name = "Categorie RADIO LIVE ")
	@DeleteMapping(path="catrl/delete/{id}")
	public ResponseEntity<Object> deleteCRL (@PathVariable Long id) {

		catLrService.delete(id);


		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}


	/*
	 * 
	 * Categorie Podcast
	 * 
	 * 
	 */


	@Tag(name = "Categorie PODCAST")

	@PostMapping(path="catpod/create")

	public ResponseEntity<Object> createCP(
			@Valid @RequestBody CatPodcast u) {

		u.setName(u.getName().toLowerCase());
		if(catPodRep.findByName(u.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette categorie de podcast existe déja");
		}
		else {
			
		
		    return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, catpodService.create(u));
		
		}
	}


	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod")
	public ResponseEntity<Object> showCP(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.show());
	}
	
	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod/all/")
	public ResponseEntity<Object> showCP(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
				catpodService.showPaging(p) );
	}


	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod/{id}")
	public ResponseEntity<Object> showbyIdCP(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.showById(id));
	}

	@Tag(name = "Categorie PODCAST")
	@PutMapping(path="catpod/update/{id}")
	public ResponseEntity<Object> updateCP(

			@PathVariable Long id,
			@Valid @RequestBody CatPodcast u){
		u.setName(u.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.upadte(id, u));

	}

	@Tag(name = "Categorie PODCAST")
	@DeleteMapping(path="catpod/delete/{id}")
	public ResponseEntity<Object> deleteCP (@PathVariable Long id) {

		catpodService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}


	//Radio
	@Tag(name = "Radio")
	@GetMapping("radios")
	public ResponseEntity<Object> showR(){
		
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.show());
	}
	
	@Tag(name = "Radio")
	@GetMapping("radios/all/")
	public ResponseEntity<Object> showRadioPage(Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue){
		
		if(categ != null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showByCateg(categ, p));
		}
		if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showByLangue(langue, p));
		}
		else {

		    return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showPage(p));
		}
	}

	@Tag(name = "Radio")
	@PostMapping(path="radios/create")
	public  ResponseEntity<Object> createR(
			@Valid
			@RequestBody Radio r ){

		r.setName(r.getName().toLowerCase());
		if(radioService.findByName(r.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette radio existe déja");
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.create(r));
		}
	}

	@Tag(name = "Radio")
	@GetMapping("radios/{id}")
	public ResponseEntity<Object> showbyIdR(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showById(id));
	}

	@Tag(name = "Radio")
	@GetMapping("radios/search/")
	public ResponseEntity<Object> showbyNameContain(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue){
		
		if(categ != null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.searchByCateg(s, categ, p));
		}
		else if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.searchByLangue(s, langue, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.search(s, p));
		
		}
	}

	@Tag(name = "Radio")
	@PutMapping(path="radios/update/{id}")
	public ResponseEntity<Object> updateR(@PathVariable Long id,
			@Valid @RequestBody Radio r) {

		r.setName(r.getName().toLowerCase());
		//Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, radioService.upadte(id, r));

	}

	@Tag(name = "Radio")
	@DeleteMapping(path="radios/delete/{id}")
	public ResponseEntity<Object> deleteR (@PathVariable Long id) {

		radioService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	//ROUTES LiveTV

	@Tag(name = "TV SHOW")
	@PostMapping(path="tv/create")
	public ResponseEntity<Object> createL(
			@Valid @RequestBody LiveTv lt) {

		lt.setName(lt.getName().toLowerCase());
		if(liveService.findByName(lt.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette chaine tv existe déja");
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, liveService.create(lt));
		}
	}

	@Tag(name = "TV SHOW")
	@GetMapping("tv")
	public ResponseEntity<Object> showL(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.show());
	}
	
	@Tag(name = "TV SHOW")
	@GetMapping("tv/all/")
	public ResponseEntity<Object> showLivePages(Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
		
		if(genre != null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByGenre(genre, p));
		}
		else if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByLangue(langue, p));
		}
		else if(pays != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByPays(pays, p));
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showPage(p));
		}
	}
	
	@Tag(name = "TV SHOW")
	@GetMapping("tv/search/")
	public ResponseEntity<Object> showLbyNameContainL(
			@RequestParam String s, Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
			
		if(genre != null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.searchByGenre(s, genre, p));
		}
		else if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.searchbyLangue(s, langue, p));
		}
		else if(pays != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.searchByPays(s, pays, p));
		}
		else {
			

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.search(s, p));
		}
	}

	@Tag(name = "TV SHOW")
	@GetMapping("tv/{id}")
	public ResponseEntity<Object> showbyIdL(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showById(id));
	}

	@Tag(name = "TV SHOW")
	@PutMapping(path="tv/update/{id}")
	public  ResponseEntity<Object> updateL(
			@PathVariable Long id,
			@Valid @RequestBody LiveTv lt) {

		lt.setName(lt.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.update(id, lt));

	}

	@Tag(name = "TV SHOW")
	@DeleteMapping(path="tv/delete/{id}")
	public ResponseEntity<Object> deleteL (@PathVariable Long id) {

		liveService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}
	
	/*
	 * 
	 * CATEGORIE DES LIVES 
	 * 
	 */
	
	
	@Tag(name = "Categorie Lives")

	@PostMapping(path="lives/categ/create")

	public ResponseEntity<Object> createCP(
			@Valid @RequestBody CategorieLive u) {
		
		u.setName(u.getName().toLowerCase());
		if(catLiveRep.findByName(u.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette categorie de lıve existe déja");
		}
		else {
			
		    return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, categliveService.create(u));
		
		}
	}


	@Tag(name = "Categorie Lives")
	@GetMapping("lives/categs")
	public ResponseEntity<Object> showCL(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.show());
	}
	
	@Tag(name = "Categorie Lives")
	@GetMapping("lives/categs/all/")
	public ResponseEntity<Object> showCL(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
				categliveService.showPaging(p) );
	}


	@Tag(name = "Categorie Lives")
	@GetMapping("lives/categs/{id}")
	public ResponseEntity<Object> showbyIdCL(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.showById(id));
	}

	@Tag(name = "Categorie Lives")
	@PutMapping(path="lives/categ/update/{id}")
	public ResponseEntity<Object> updateCL(

			@PathVariable Long id,
			@Valid @RequestBody CategorieLive u){
		u.setName(u.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.upadte(id, u));

	}

	@Tag(name = "Categorie Lives")
	@DeleteMapping(path="lives/categ/delete/{id}")
	public ResponseEntity<Object> deleteCL(@PathVariable Long id) {

		catpodService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}



	/*
	 * 
	 * LES LIVES  
	 * 
	 * 
	 */

	@Tag(name = "Lives")
	@GetMapping("lives")
	public ResponseEntity<Object> showLives(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.show());
	}
	
	@Tag(name = "Lives")
	@GetMapping("lives/all/")
	public ResponseEntity<Object> showLivesByPage(
		Pageable p,
		@RequestParam (required = false) Long categ ){
	
		if(categ != null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.showByCategorie(categ, p));
		}
		else {
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.showPage(p));
		}
	}

	@Tag(name = "Lives")
	@PostMapping(path="lives/create")
	public ResponseEntity<Object> createLives(
			@Valid @RequestBody Live p){

		p.setName(p.getName().toLowerCase());
		if(lService.findByName(p.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Ce live existe déja");
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, lService.create(p) );
		}

	}


	@Tag(name = "Lives")
	@GetMapping("lives/{id}")
	public ResponseEntity<Object> showbyIdLives(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.showById(id));
	}

	@Tag(name = "Lives")
	@GetMapping("lives/search/")
	public ResponseEntity<Object> showbyIdLives(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) Long categ ){
	
		if(categ != null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.searchByCateg(s, categ, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.search(s, p));
		}
	}

	@Tag(name = "Lives")
	@PutMapping(path="lives/update/{id}")
	public ResponseEntity<Object> updateLives(
			@PathVariable Long id,
			@Valid @RequestBody Live l) {
		
			l.setName(l.getName().toLowerCase());
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, lService.update(id, l) );

	}

	@Tag(name = "Lives")
	@DeleteMapping(path="lives/delete/{id}")
	public ResponseEntity<Object> deleteLives (@PathVariable Long id) {

		lService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true );
	}
	
	
	/*
	 * 
	 * Podcast
	 * 
	 */

		@Tag(name = "Podcast")
		@GetMapping("podcasts")
		public ResponseEntity<Object> showP(){

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.show());
		}
		
		@Tag(name = "Podcast")
		@GetMapping("podcasts/all/")
		public ResponseEntity<Object> showPodcastByPage(Pageable p,
				@RequestParam (required = false) Long categ ,
				@RequestParam (required = false) Long langue){
			
			if(categ != null ) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByCateg(categ, p));
			}
			if(langue != null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByLang(langue, p));
			}
			else {

				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showPage(p));
			}
		}

		@Tag(name = "Podcast")
		@PostMapping(path="podcasts/create")
		public ResponseEntity<Object> createP(
				@Valid @RequestBody Podcast p){

			p.setName(p.getName().toLowerCase());
			if(podcastservice.findByName(p.getName()) != null) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Ce Podcast existe déja");
			}
			else {
				return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.create(p) );
			}

		}


		@Tag(name = "Podcast")
		@GetMapping("podcasts/{id}")
		public ResponseEntity<Object> showbyIdP(@PathVariable Long id){

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showById(id));
		}

		@Tag(name = "Podcast")
		@GetMapping("podcasts/search/")
		public ResponseEntity<Object> showbyIdP(
				@RequestParam String s, 
				Pageable p,
				@RequestParam (required = false) Long categ ,
				@RequestParam (required = false) Long langue){
			
			if(categ != null ) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByCateg(s, categ, p));
			}
			if(langue != null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByLang(s, langue, p));
			}
			else {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.search(s, p));
			}
		}

		@Tag(name = "Podcast")
		@PutMapping(path="podcasts/update/{id}")
		public ResponseEntity<Object> updateP(
				@PathVariable Long id,
				@Valid @RequestBody Podcast p) {

				p.setName(p.getName().toLowerCase());
				//Save du tout
				return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, podcastservice.upadte(id, p) );

		}

		@Tag(name = "Podcast")
		@DeleteMapping(path="podcasts/delete/{id}")
		public ResponseEntity<Object> deleteP (@PathVariable Long id) {

			podcastservice.delete(id);

			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true );
		}
	
	
	
	
	
	
	
	
	/*
	 * 
	 * FILMS
	 * 
	 */

	@Tag(name = "Movie")
	@GetMapping("movies")
	public ResponseEntity<Object> showM(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.show());
	}
	
	@Tag(name = "Movie")
	@GetMapping("movies/all/")
	public ResponseEntity<Object> showMovieByPage(
			Pageable p, 
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue ){


		if(langue != null && genre == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showByLangue(langue,p));
			
		}
		else if(genre != null && langue == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showByGenre(genre,p));
			
		}
		else if(genre !=null && langue !=null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showByGenre(genre,p));
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showPages(p));
		}

	}

	@Tag(name = "Movie")
	@PostMapping(path="movies/create")
	public ResponseEntity<Object> createM(
			@RequestBody Film film) {

		film.setName(film.getName().toLowerCase());
		if(filmService.findByName(film.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Ce film existe déja");
		}
		else {
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, filmService.create(film));
		}


	}

	@Tag(name = "Movie")
	@GetMapping("movies/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showById(id));
	}

	@Tag(name = "Movie")
	@GetMapping("movies/search/")
	public ResponseEntity<Object> search(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue){

		if(langue != null && genre==null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.searchByLangue(s,langue,p));
			
		}
		else if(genre != null && langue == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.searchByGenre(s, genre, p));
			
		}
		else if(genre != null && langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.searchByGenre(s, genre, p));
			
		}
		else {
		
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.search(s, p));
		
		}
	}

	@Tag(name = "Movie")
	@PutMapping(path="movies/update/{id}")
	public ResponseEntity<Object> updateM(@PathVariable Long id,
			@Valid @RequestBody Film film)  {
		
		film.setName(film.getName().toLowerCase());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, filmService.upadte(id, film));

	}

	@Tag(name = "Movie")
	@DeleteMapping(path="movies/delete/{id}")
	public ResponseEntity<Object> deleteM (@PathVariable Long id) {

		filmService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	//Series

	@Tag(name = "Serie")
	@GetMapping("series")
	public ResponseEntity<Object> showS(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.show());
	}
	
	@Tag(name = "Serie")
	@GetMapping("series/all/")
	public ResponseEntity<Object> showSerieByPage(Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue){
		
		if(genre != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByGenre(genre, p));
			
		}
		else if (langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByLangue(langue, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showPage(p));
			
		}
	}
	

	@Tag(name = "Serie")
	@PostMapping(path="series/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Serie serie){
			
		serie.setName(serie.getName().toLowerCase());
		if(serieService.findByName(serie.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette serie existe déja");
		}
		else {
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED , serieService.create(serie));
		}


	}

	@Tag(name = "Serie")
	@GetMapping("series/{id}")
	public ResponseEntity<Object> showbyIdS(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showById(id));
	}

	@Tag(name = "Serie")
	@GetMapping("series/search/")
	public ResponseEntity<Object> showbyIdS(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue){
		
		if(genre != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByGenre(s, genre, p));
			
		}
		else if (langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByLangue(s, langue, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.search(s, p));
			
		}
	}

	@Tag(name = "Serie")
	@PutMapping(path="series/update/{id}")
	public ResponseEntity<Object> updateS(
			@PathVariable Long id,
			@Valid @RequestBody Serie serie){

			serie.setName(serie.getName().toLowerCase());
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , serieService.upadte(id, serie));

	}

	@Tag(name = "Serie")
	@DeleteMapping(path="series/delete/{id}")
	public ResponseEntity<Object> deleteS (@PathVariable Long id) {
		
		if(serieService.showById(id).isEmpty()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Vous tentez de supprimer une serie qui n'existe pas");
		}
		else {
			
		serieService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
		
		}
			
		
	}

	//SAISON
	/*
	 *  CRUD DES SAISONS DE SERIE
	 */
	
	@Tag(name = "Saison")
	@GetMapping("saisons")
	public ResponseEntity<Object> showSaison(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.show());
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/all/")
	public ResponseEntity<Object> showSaisonPage(Pageable p,
			@RequestParam (required = false) Long langue){
		
		if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByLangue(langue, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showPage(p));
		
		}
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/bySerie/{idSerie}")
	public ResponseEntity<Object> showSaisonBySerie(@PathVariable Long idSerie, Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showBySerie(serieService.showById(idSerie).get(), p));
	}

	@Tag(name = "Saison")
	@PostMapping(path="saisons/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Saison saison){

		saison.setName(saison.getName().toLowerCase());
		if(saisonService.findByName(saison.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette saison existe déja");
		}
		else {
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED , saisonService.create(saison));
		}

	}

	@Tag(name = "Saison")
	@GetMapping("saisons/{id}")
	public ResponseEntity<Object> showbyIdSaison(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showById(id));
	}

	@Tag(name = "Saison")
	@GetMapping("saisons/search/")
	public ResponseEntity<Object> showbyNameC(
			@RequestParam String s, 
			@RequestParam (required = false) Long langue,
			Pageable p){
		
		if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.searchByLangue(s, langue, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.search(s, p));
		}
		
	}

	@Tag(name = "Saison")
	@PutMapping(path="saisons/update/{id}")
	public ResponseEntity<Object> updateSaison(
			@PathVariable Long id,
			@Valid @RequestBody Saison saison){

			saison.setName(saison.getName().toLowerCase());
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , saisonService.upadte(id, saison));

	}

	@Tag(name = "Saison")
	@DeleteMapping(path="saisons/delete/{id}")
	public ResponseEntity<Object> deleteSaison (@PathVariable Long id) {

		saisonService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}
	
	//FIN SAISON

	
	/*
	 * 
	 * EPISODES
	 * 
	 * 
	 */
	
	//Episodes
	@Tag(name = "Episode")
	@GetMapping("episodes")
    public ResponseEntity<Object> showE(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.show());
	}
	
	@Tag(name = "Episode")
	@GetMapping("episodes/all/")
    public ResponseEntity<Object> showE(
    		Pageable p,
    		@RequestParam (required = false) Long serie,
			@RequestParam (required = false) Long langue){
		
		if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showByLangue(langue, p));
		}
		else if(serie != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySerie(serie, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showPage(p));
		}
	}
	
	@Tag(name = "Episode")
	@GetMapping("episodes/bySaison/{idSaison}")
    public ResponseEntity<Object> showE(@PathVariable Long idSaison){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySaison(saisonService.showById(idSaison)));
	}
	
    @Tag(name = "Episode")
	@GetMapping("episodes/{id}")
	public ResponseEntity<Object> showbyIdE(@PathVariable Long id){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showById(id));
	}

    @Tag(name = "Episode")
	@GetMapping("episodes/search/")
	public ResponseEntity<Object> searchEp(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long serie,
			@RequestParam (required = false) Long langue){
		
		if(langue != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchByLangue(s, langue, p));
		}
		else if(serie != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchBySerie(s, serie, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.search(s, p));
    	
		}
	}

    @Tag(name = "Episode")
	@PostMapping(path="episodes/create")
	public ResponseEntity<Object> createE(
			@Valid @RequestBody Episode episode) {

    	episode.setName(episode.getName().toLowerCase());
		if(episodeService.findByName(episode.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette episode existe déja");
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, episodeService.create(episode));
		}


	}

    @Tag(name = "Episode")
	@PutMapping(path="episodes/update/{id}")
	public ResponseEntity<Object> updateE(
			@PathVariable Long id,
			@Valid @RequestBody Episode episode){

    	episode.setName(episode.getName().toLowerCase());
		//Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, episodeService.upadte(id, episode));

	}

    @Tag(name = "Episode")
	@DeleteMapping(path="episodes/delete/{id}")
	public ResponseEntity<Object> deleteE (@PathVariable Long id) {

		episodeService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}
    //FIN EPISODE
    
    
    
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
  			@PathVariable String name, 
  			Pageable p){

      	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, sliderService.showByName(s, p));
  	}

      @Tag(name = "Slider")
  	@PostMapping(path="slider/create")
  	public ResponseEntity<Object> createSlider(
  			@Valid @RequestBody Slider slider) {

    	  slider.setName(slider.getName().toLowerCase());
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

    	 slider.setName(slider.getName().toLowerCase());
  		//Save du tout
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
     * 
     *CLOUDFLARE R2 CRUD 
     * 
     * 
     */
    
    

    /* AWS R2 FILE CRUD  */

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/findall")
    public ResponseEntity<Object> all() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.list());
    }
	
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/find/all/whithPaging")
    public ResponseEntity<Object> all(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.listWithPage(p));
    }
	
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/download/{idFile}")
    public ResponseEntity<Object> download( @PathVariable int idFile) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.download(idFile));
    }
	

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/byId/")
    public ResponseEntity<Object> findbyid(
    		@RequestParam int id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findById(id));
    }

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/{name}")
    public ResponseEntity<Object> findbyname(@PathVariable String name, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findByFileNameContaining(name, p));
    }

	@Tag(name = "R2-CLOUDFLARE")
    @PostMapping(path="r2/uploadfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.uploadR3(file, ""));

    }

	@Tag(name = "R2-CLOUDFLARE")
    @PostMapping(path="r2/uploadfile/in-newFolder/{newFolder}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>  uploadWithFolderName(@RequestParam("file") MultipartFile file, @PathVariable String newFolder) throws IOException {


		if(newFolder==null) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.BAD_REQUEST, " le dossier ne puis être vide");

		}
		else if (file.isEmpty()) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.BAD_REQUEST, " un fichier est requis");

		}
		else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, metadataService.uploadR3(file, newFolder));
		 }
    }

	@Tag(name = "R2-CLOUDFLARE")
    @DeleteMapping("deleteFile/{id}")
    public ResponseEntity<Object>  deleteByName(@PathVariable  int id){


		if (fileMetaRep.findById(id)==null) {

			return EntityResponse.generateResponse("Erreur suppresion", HttpStatus.BAD_REQUEST, "Vous tentez de supprimez un objet qui n'existe pas ");

		}
		else {


			Optional<FileMeta> fm = fileMetaRep.findById(id);

			System.out.println(fm.get().getFileName());

			System.out.println(fm.get().getFilePath());

			awsService.deleteR2( fm.get().getFilePath() , fm.get().getFileName());

			fileMetaRep.deleteById(id);

			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, "fichier Supprimé");
		}

	}
	
	


}


