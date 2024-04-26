package com.mytv.api.controller;

import java.io.IOException;
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
import com.mytv.api.model.gestMedia.Actor;
import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.ColPodcast;
import com.mytv.api.model.gestMedia.Director;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.Language;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.repository.ActorRepository;
import com.mytv.api.repository.CatPodcastRepository;
import com.mytv.api.repository.CategoryLrRepository;
import com.mytv.api.repository.CollectionPodcastRepository;
import com.mytv.api.repository.DirectorRepository;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.gestMedia.CatPodcastService;
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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

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
	@Autowired
	private CategoryLrRepository catlrRep;
    @Autowired
    private CatPodcastRepository catPodRep;
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
     * SOUS TITRE
     * 
     * 
     */
    
    
    
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
	@GetMapping("podcast/collections/all/withPaging")
	public ResponseEntity<Object> showCollPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findAll(p));
	}
    
    @Tag(name = "Podcast Collection")
	@GetMapping("podcast/collections/{id}")
	public ResponseEntity<Object> showCollectionById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findById(id));
	}
    
    @Tag(name = "Podcast Collection")
	@PostMapping("podcast/collections/create")
	public ResponseEntity<Object> createCollection(@Valid @RequestBody ColPodcast a){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, colPodRep.save(a));
	}
    
    @Tag(name = "Podcast Collection")
	@PutMapping("podcast/collections/update/{id}")
	public ResponseEntity<Object> updateCollection(@PathVariable Long id, @Valid @RequestBody ColPodcast a){
    	a.setIdColPd(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.save(a));
		
	}
    
    
    @Tag(name = "Podcast Collection")
	@DeleteMapping("podcast/collections/delete/{id}")
	public ResponseEntity<Object> deleteCollection(@PathVariable Long id){
    	actorRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, " ");
		
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
	@GetMapping("acteurs/all/withPaging")
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
	@GetMapping("directeurs/all/withPaging")
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
	public ResponseEntity<Object> updateActor(@PathVariable Long id, @Valid @RequestBody Director a){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.save(a));
		
	}
    
    
    @Tag(name = "Directeur")
	@PutMapping("directeurs/delete/{id}")
	public ResponseEntity<Object> deleteDir(@PathVariable Long id){
    	actorRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, " ");
		
	}
    
    
    
    
	//private final String asset ="/RESSOURCES/IMG/";

	//Langue

	@Tag(name = "Langue")
	@GetMapping("langs")
	public ResponseEntity<Object> showLang(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.show());
	}
	
	@Tag(name = "Langue")
	@GetMapping("langs/all/withPaging")
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
	public ResponseEntity<Object> updateLang(@PathVariable Long id,@Valid @RequestBody Language u){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.upadte(id, u));

	}

	@Tag(name = "Langue")
	@PostMapping(path="lang/create")
	public ResponseEntity<Object> createLang(@Valid @RequestBody Language u) {
		
		if( langService.showByName(u.getName()) != null ) {
			
			System.out.println(" Ce nom existe déja");
			
			return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST , "Ce genre existe déja");
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
	@GetMapping("langs/search/byname/{name}")
	public ResponseEntity<Object> showLangByName( @PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.showByName(name));
	}

	//Pays
	@Tag(name = "Pays")
	@PostMapping("pays/create")

	public ResponseEntity<Object> createPays(@Valid @RequestBody Pays u) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, paysService.create(u));
	}

	@Tag(name = "Pays")
	@GetMapping("pays")
	public ResponseEntity<Object> showPays(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.show());
	}
	
	@Tag(name = "Pays")
	@GetMapping("pays/all/withPaging")
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
	@GetMapping("genres/all/withPaging")
	public ResponseEntity<Object> showPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showByPages(p));
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/search/byName/{name}")
	public ResponseEntity<Object> showByName(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showByName(name));
	}

	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/search/contain/{name}")
	public ResponseEntity<Object> showByNameContain(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.findByNameContain(name));
		
	}


	@Tag(name = "Genre FILM SERIE")
	@GetMapping("genres/{id}")
	public ResponseEntity<Object> showbyIdG(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showById(id).orElseThrow(() -> new ResourceNotFoundException("aucune donne avec id= " + id)));
	}

	@Tag(name = "Genre FILM SERIE")
	@PutMapping("genres/update/{id}")
	public ResponseEntity<Object> updateG(@PathVariable Long id,@Valid @RequestBody Genre g){
		g.getName().toUpperCase();
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
	@GetMapping("catrl/all/withPaging")
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

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.upadte(id, u));

	}


	@Tag(name = "Categorie RADIO LIVE ")
	@DeleteMapping(path="catrl/delete/{id}")
	public ResponseEntity<Object> deleteCRL (@PathVariable Long id) {

		catLrService.delete(id);


		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}


	//Categorie Podcast


	@Tag(name = "Categorie PODCAST")

	@PostMapping(path="catpod/create")

	public ResponseEntity<Object> createCP(
			@Valid @RequestBody CatPodcast u) {

		
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
	@GetMapping("catpod/all/withPaging")
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
	@GetMapping("radios/all/withPaging")
	public ResponseEntity<Object> showRadioPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showPage(p));
	}

	@Tag(name = "Radio")
	@PostMapping(path="radios/create")
	public  ResponseEntity<Object> createR(
			@Valid
			@RequestBody Radio r ){

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.create(r));
	}

	@Tag(name = "Radio")
	@GetMapping("radios/{id}")
	public ResponseEntity<Object> showbyIdR(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showById(id));
	}

	@Tag(name = "Radio")
	@GetMapping("radios/search/byName/{name}")
	public ResponseEntity<Object> showbyNameContain(@PathVariable String nom){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showByNameContaining(nom));
	}

	@Tag(name = "Radio")
	@PutMapping(path="radios/update/{id}")
	public ResponseEntity<Object> updateR(@PathVariable Long id,
			@Valid @RequestBody Radio r) {

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

	@Tag(name = "LiveTv")
	@PostMapping(path="lives/create")
	public ResponseEntity<Object> createL(
			@Valid @RequestBody LiveTv lt) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, liveService.create(lt));
	}

	@Tag(name = "LiveTv")
	@GetMapping("lives")
	public ResponseEntity<Object> showL(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.show());
	}
	
	@Tag(name = "LiveTv")
	@GetMapping("lives/all/withPaging")
	public ResponseEntity<Object> showLivePages(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showPage(p));
	}
	
	@Tag(name = "LiveTv")
	@GetMapping("lives/search/byName/{nom}")
	public ResponseEntity<Object> showLbyNameContainL(@PathVariable String nom){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByNameContaining(nom));
	}

	@Tag(name = "LiveTv")
	@GetMapping("lives/{id}")
	public ResponseEntity<Object> showbyIdL(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showById(id));
	}

	@Tag(name = "LiveTv")
	@PutMapping(path="lives/update/{id}")
	public  ResponseEntity<Object> updateL(
			@PathVariable Long id,
			@Valid @RequestBody LiveTv lt) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.update(id, lt));

	}

	@Tag(name = "LiveTv")
	@DeleteMapping(path="lives/delete/{id}")
	public ResponseEntity<Object> deleteL (@PathVariable Long id) {

		liveService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	//Podcast

	@Tag(name = "Podcast")
	@GetMapping("podcasts")
	public ResponseEntity<Object> showP(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.show());
	}
	
	@Tag(name = "Podcast")
	@GetMapping("podcasts/all/withPaging")
	public ResponseEntity<Object> showPodcastByPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showPage(p));
	}

	@Tag(name = "Podcast")
	@PostMapping(path="podcasts/create")
	public ResponseEntity<Object> createP(
			@Valid @RequestBody Podcast p){

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.create(p) );

	}


	@Tag(name = "Podcast")
	@GetMapping("podcasts/{id}")
	public ResponseEntity<Object> showbyIdP(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showById(id));
	}

	@Tag(name = "Podcast")
	@GetMapping("podcasts/search/byName/{name}")
	public ResponseEntity<Object> showbyIdP(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByNameContaining(name));
	}

	@Tag(name = "Podcast")
	@PutMapping(path="podcasts/update/{id}")
	public ResponseEntity<Object> updateP(
			@PathVariable Long id,
			@Valid @RequestBody Podcast p) {

			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, podcastservice.upadte(id, p) );

	}

	@Tag(name = "Podcast")
	@DeleteMapping(path="podcasts/delete/{id}")
	public ResponseEntity<Object> deleteP (@PathVariable Long id) {

		podcastservice.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true );
	}


	//Films

	@Tag(name = "Movie")
	@GetMapping("movies")
	public ResponseEntity<Object> showM(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.show());
	}
	
	@Tag(name = "Movie")
	@GetMapping("movies/all/withPaging")
	public ResponseEntity<Object> showMovieByPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showPages(p));
	}

	@Tag(name = "Movie")
	@PostMapping(path="movies/create")
	public ResponseEntity<Object> createM(
			@RequestBody Film film) {

			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, filmService.create(film));


	}

	@Tag(name = "Movie")
	@GetMapping("movies/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showById(id));
	}

	@Tag(name = "Movie")
	@GetMapping("movies/search/byName/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showByNameContaining(name));
	}

	@Tag(name = "Movie")
	@PutMapping(path="movies/update/{id}")
	public ResponseEntity<Object> updateM(@PathVariable Long id,
			@Valid @RequestBody Film film)  {
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
	@GetMapping("series/all/withPaging")
	public ResponseEntity<Object> showSerieByPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showPage(p));
	}
	

	@Tag(name = "Serie")
	@PostMapping(path="series/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Serie serie){

			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED , serieService.create(serie));


	}

	@Tag(name = "Serie")
	@GetMapping("series/{id}")
	public ResponseEntity<Object> showbyIdS(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showById(id));
	}

	@Tag(name = "Serie")
	@GetMapping("series/search/byName/{name}")
	public ResponseEntity<Object> showbyIdS(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showbyNameContaining(name));
	}

	@Tag(name = "Serie")
	@PutMapping(path="series/update/{id}")
	public ResponseEntity<Object> updateS(
			@PathVariable Long id,
			@Valid @RequestBody Serie serie){

			return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.OK , serieService.upadte(id, serie));

	}

	@Tag(name = "Serie")
	@DeleteMapping(path="series/delete/{id}")
	public ResponseEntity<Object> deleteS (@PathVariable Long id) {

		serieService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
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
	@GetMapping("saisons/all/withPaging")
	public ResponseEntity<Object> showSaisonPage(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showPage(p));
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/bySerie/{idSerie}")
	public ResponseEntity<Object> showSaisonBySerie(@PathVariable Long idSerie){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showBySerie(idSerie));
	}

	@Tag(name = "Saison")
	@PostMapping(path="saisons/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Saison saison){

			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED , saisonService.create(saison));


	}

	@Tag(name = "Saison")
	@GetMapping("saisons/{id}")
	public ResponseEntity<Object> showbyIdSaison(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showById(id));
	}

	@Tag(name = "Saison")
	@GetMapping("saisons/search/byName/{name}")
	public ResponseEntity<Object> showbyNameC(@PathVariable String name){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByNameContaining(name));
		
	}

	@Tag(name = "Saison")
	@PutMapping(path="saisons/update/{id}")
	public ResponseEntity<Object> updateSaison(
			@PathVariable Long id,
			@Valid @RequestBody Saison saison){

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
	@GetMapping("episodes/all/withPaging")
    public ResponseEntity<Object> showE(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showPage(p));
	}
	
	@Tag(name = "Episode")
	@GetMapping("episodes/bySaison/{idSaison}")
    public ResponseEntity<Object> showE(@PathVariable Long idSaison){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySaison(idSaison));
	}
	
    @Tag(name = "Episode")
	@GetMapping("episodes/{id}")
	public ResponseEntity<Object> showbyIdE(@PathVariable Long id){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showById(id));
	}

    @Tag(name = "Episode")
	@GetMapping("episodes/search/byName/{name}")
	public ResponseEntity<Object> showbyIdE(@PathVariable String name){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showByNameContain(name));
	}

    @Tag(name = "Episode")
	@PostMapping(path="episodes/create")
	public ResponseEntity<Object> createE(
			@Valid @RequestBody Episode episode) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, episodeService.create(episode));


	}

    @Tag(name = "Episode")
	@PutMapping(path="episodes/update/{id}")
	public ResponseEntity<Object> updateE(
			Long id,
			@Valid @RequestBody Episode episode){

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
    public ResponseEntity<Object> allWithPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.listWithPage(p));
    }
	
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/download/{idFile}")
    public ResponseEntity<Object> download( @PathVariable int idFile) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.download(idFile));
    }
	

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/byId/{idFile}")
    public ResponseEntity<Object> findbyid(@PathVariable int idFile) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findById(idFile));
    }

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/byName/{name}")
    public ResponseEntity<Object> findbyname(@PathVariable String name) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findByFileNameContaining(name));
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


