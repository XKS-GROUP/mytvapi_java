package com.mytv.api.controller.admin;


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
import com.mytv.api.episode.repository.EpisodeRepository;
import com.mytv.api.episode.service.EpisodeService;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.service.ServiceFilm;
import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.model.Director;
import com.mytv.api.intervenant.model.Podcasteur;
import com.mytv.api.live.model.Live;
import com.mytv.api.live.service.LiveService;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.service.LiveTvSetvice;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.service.PodcastService;
import com.mytv.api.podcastCollecton.model.ColPodcast;
import com.mytv.api.podcastCollecton.service.ColPodcastService;
import com.mytv.api.podcastcateg.model.CatPodcast;
import com.mytv.api.podcastcateg.service.CatPodcastService;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.service.RadioService;
import com.mytv.api.ressource.model.CategorieLive;
import com.mytv.api.ressource.model.CategoryRL;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.model.Pays;
import com.mytv.api.ressource.repository.CategorieLiveRepository;
import com.mytv.api.ressource.service.CategorieLiveService;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.service.SaisonService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.service.SerieService;
import com.mytv.api.service.CommonFunction;

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
    //Necessaire pour le controle du nom de l'episode par saison
	private EpisodeRepository repEpisode; 
    
	@Autowired
	private CommonFunction fnc;

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
	private CatPodcastService catpodService;

    @Autowired
    private CategorieLiveRepository catLiveRep;
    
    @Autowired
    private ColPodcastService colpodservice;
    
    @Autowired
    private SaisonService saisonService;
    
    /*
     * Collection Podcast
     * 
     * */
    
    //CRUD Podcast Collections
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
    
    @Tag(name = "Podcast Collection")
	@PostMapping("podcast/collections/create")
	public ResponseEntity<Object> createCollection(@Valid @RequestBody ColPodcast r){
    	
    	if(colpodservice.checktop10limit() != null && r.isTop10() ) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 collections de podcast en tete sont atteint", "podcast_top10", colpodservice.checktop10limit()));
		}
		else if(colpodservice.checktoplimit() != null && r.isTop()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une collection de podcast en vedette", "podcast_vedette", colpodservice.checktoplimit()));
		}
		else {
    	
			return fnc.createCollection(r);
		}
	}
    
    @Tag(name = "Podcast Collection")
	@PutMapping("podcast/collections/update/{id}")
	public ResponseEntity<Object> updateCollection(@PathVariable Long id, @Valid @RequestBody ColPodcast a){
    	
    	return fnc.updateCollection(id, a);
		
	}
    
    @Tag(name = "Podcast Collection")
   	@PutMapping("podcast/collections/update/status/{id}")
   	public ResponseEntity<Object> updateSatusCollection(@PathVariable Long id, @Valid @RequestBody StatusDTO status){
       	
    	return fnc.updateSatusCollection(id, status);
    	
   	}
    
    
    @Tag(name = "Podcast Collection")
	@DeleteMapping("podcast/collections/delete/{id}")
	public ResponseEntity<Object> deleteCollection(@PathVariable Long id){

    	return fnc.deleteCollection(id);
	}
    
    
    /*
     * 
     * CRUD acteurs
     * 
     */
    //
    
    @Tag(name = "Acteur")
	@GetMapping("acteurs")
	public ResponseEntity<Object> actor_show(){

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
	public ResponseEntity<Object> actor_show_by_id(@PathVariable long id){

		return fnc.actor_show_by_id(id);
	}
    
    @Tag(name = "Acteur")
	@PostMapping("acteurs/create")
	public ResponseEntity<Object> actor_create(@Valid @RequestBody Actor a){

		return fnc.actor_create(a);
	}
    
    @Tag(name = "Acteur")
	@PutMapping("acteurs/update/{id}")
	public ResponseEntity<Object> actor_update(@PathVariable Long id, @Valid @RequestBody Actor a){

    	return fnc.actor_update(id, a);
		
	}
    
    @Tag(name = "Acteur")
	@DeleteMapping("acteurs/delete/{id}")
	public ResponseEntity<Object> actor_delete(@PathVariable Long id){
    	
    	return fnc.actor_delete(id);
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

		return fnc.showDirById(id);
	}
    
    @Tag(name = "Directeur")
	@PostMapping("directeurs/create")
	public ResponseEntity<Object> createDir(@Valid @RequestBody Director a){

		return fnc.createDir(a);
	}
    
    @Tag(name = "Directeur")
	@PutMapping("directeurs/update/{id}")
	public ResponseEntity<Object> updateDirecteur(@PathVariable Long id, @Valid @RequestBody Director a){
    	
    	return fnc.updateDirecteur(id, a);
		
	}
    
    @Tag(name = "Directeur")
	@DeleteMapping("directeurs/delete/{id}")
	public ResponseEntity<Object> deleteDir(@PathVariable Long id){
    	
    	return fnc.deleteDir(id);
		
	}
    
    
    /*
     * 
     * CRUD Podcasteur
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
    
    @Tag(name = "Podcasteur")
	@PostMapping("podcasteurs/create")
	public ResponseEntity<Object> podCreate(@Valid @RequestBody Podcasteur p){

		return fnc.podCreate(p);
	}
    
    @Tag(name = "Podcasteur")
	@PutMapping("podcasteurs/update/{id}")
	public ResponseEntity<Object> podUpdate(@PathVariable Long id, @Valid @RequestBody Podcasteur p){
    	
    	return fnc.podUpdate(id, p);
		
	}
    
    @Tag(name = "Podcasteur")
	@DeleteMapping("podcasteurs/delete/{id}")
	public ResponseEntity<Object> podDelete(@PathVariable Long id){
    	
    	return fnc.podDelete(id);
		
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
	@PutMapping("lang/update/{id}")
	public ResponseEntity<Object> updateLang(@PathVariable Long id,@Valid @RequestBody Language r){
		
		return fnc.updateLang(id, r);
	}
	
	@Tag(name = "Langue")
	@PutMapping("lang/update/status/{id}")
	public ResponseEntity<Object> updateStatusLang(@PathVariable Long id, @Valid @RequestBody StatusDTO status){
		
		return fnc.updateStatusLang(id, status);

	}

	@Tag(name = "Langue")
	@PostMapping(path="lang/create")
	public ResponseEntity<Object> createLang(@Valid @RequestBody Language u) {
		
		return fnc.createLang(u);
	}

	
	@Tag(name = "Langue")
	@DeleteMapping(path="langs/delete/{id}")
	public ResponseEntity<Object> delete (@PathVariable Long id) {

		return fnc.delete(id);
	}

	@Tag(name = "Langue")
	@GetMapping("langs/search/")
	public ResponseEntity<Object> showLangByName( 
			@RequestParam String s, Pageable p){

		return fnc.showLangByName(s, p);
	}

	/*
	 * 
	 * 
	 * CRUD Pays
	 * 
	 * 
	 */
	
	@Tag(name = "Pays")
	@PostMapping("pays/create")

	public ResponseEntity<Object> createPays(@Valid @RequestBody Pays u) {
		
		return fnc.createPays(u);
	}

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

	@Tag(name = "Pays")
	@PutMapping("pays/update/{id}")
	public ResponseEntity<Object> updatePays(@PathVariable Long id,@Valid @RequestBody Pays u){

		return fnc.updatePays(id, u);

	}
	
	@Tag(name = "Pays")
	@PutMapping("pays/update/status/{id}")
	public ResponseEntity<Object> updateStatusPays(@PathVariable Long id, @Valid @RequestBody StatusDTO status){

		return fnc.updateStatusPays(id, status);
	}

	@Tag(name = "Pays")
	@DeleteMapping(path="pays/delete/{id}")
	public ResponseEntity<Object> deletePays (@PathVariable Long id) {

		return fnc.deletePays(id);
	}

	@Tag(name = "Pays")
	@GetMapping("pays/search/")
	public ResponseEntity<Object> paysSearch(
			@RequestParam String s,
			Pageable p){
		
			return  fnc.findPays(s, p);
		
	}
	
	
	
	/*
	 * 
	 * GENRE DE FILMs ET SERIES
	 * 
	 * 
	 */
	
	@Tag(name = "Genre FILM SERIE")
	@PostMapping(path="genres/create")

	public ResponseEntity<Object> create(@Valid @RequestBody Genre g) {
		
		return fnc.create(g);
	}

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

	@Tag(name = "Genre FILM SERIE")
	@PutMapping("genres/update/{id}")
	public ResponseEntity<Object> updateG(@PathVariable Long id, @Valid @RequestBody Genre g){

		return fnc.updateG(id, g);
	}
	
	@Tag(name = "Genre FILM SERIE")
	@PutMapping("genres/update/status/{id}")
	public ResponseEntity<Object> updateG(@PathVariable Long id, @Valid @RequestBody StatusDTO status ){

		return fnc.updateG(id, status);
	}

	@Tag(name = "Genre FILM SERIE")
	@DeleteMapping(path="genres/delete/{id}")
	public ResponseEntity<Object> deleteG (@PathVariable Long id) {

		return fnc.deleteG(id);
	}



	/*
	 * 
	 * 
	 * Categorie LiveTv ou Radio
	 * 
	 * 
	 */

	@Tag(name = "Categorie RADIO LIVE ")
	@PostMapping(path="catrl/create")
	public ResponseEntity<Object> createCRL(@Valid @RequestBody CategoryRL u) {
		
		return fnc.createCRL(u);
	}

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

	@Tag(name = "Categorie RADIO LIVE ")
	@PutMapping(path="catrl/update/{id}")
	public ResponseEntity<Object> updateCRL(
			@PathVariable Long id,
			@Valid @RequestBody CategoryRL u) {

		return fnc.updateCRL(id, u);
	}
	
	@Tag(name = "Categorie RADIO LIVE ")
	@PutMapping(path="catrl/update/status/{id}")
	public ResponseEntity<Object> updateStatusCRL(
			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status) {
		
		return fnc.updateStatusCRL(id, status);
	}

	@Tag(name = "Categorie RADIO LIVE ")
	@DeleteMapping(path="catrl/delete/{id}")
	public ResponseEntity<Object> deleteCRL (@PathVariable Long id) {

		return fnc.deleteCRL(id);
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

		return fnc.createCP(u);
	}

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

	@Tag(name = "Categorie PODCAST")
	@PutMapping(path="catpod/update/{id}")
	public ResponseEntity<Object> updateCP(

			@PathVariable Long id,
			@Valid @RequestBody CatPodcast u){
		
		return fnc.updateCP(id, u);
	}
	
	@Tag(name = "Categorie PODCAST")
	@PutMapping(path="catpod/update/status/{id}")
	public ResponseEntity<Object> updateSatusCP(

			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status){
		
		return fnc.updateSatusCP(id, status);
	}

	@Tag(name = "Categorie PODCAST")
	@DeleteMapping(path="catpod/delete/{id}")
	public ResponseEntity<Object> deleteCP (@PathVariable Long id) {

		return fnc.deleteCP(id);
	}


	/*
	 * 
	 * 
	 * CRUD RADIO 
	 * 
	 * 
	 */
	
	@Tag(name = "Radio")
	@GetMapping("radios")
	public ResponseEntity<Object> showR(){
		
		
		return fnc.radio_show();
	}
	
	@Tag(name = "Radio")
	@GetMapping("radios/all/")
	public ResponseEntity<Object> radio_show_page(Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
		
		    return fnc.radio_show_page(p, categ, langue, pays);
		
	}

	@Tag(name = "Radio")
	@PostMapping(path="radios/create")
	public  ResponseEntity<Object> radio_create(
			@Valid
			@RequestBody Radio r ){

		if(radioService.findByName(r.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name",  "Cette radio existe déja"));
		}
		else if(radioService.checktop10limit() != null && r.isTop10() ) {
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 radios en tete sont atteint", "podcast_top10", radioService.checktop10limit()));
		}
		else if(radioService.checktoplimit() != null && r.isTop()) {
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une radio en vedette", "podcast_vedette", radioService.checktoplimit()));
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.create(r));
		}
	}

	@Tag(name = "Radio")
	@GetMapping("radios/{id}")
	public ResponseEntity<Object> radio_show_by_id(@PathVariable Long id){

		return fnc.radio_show_by_id(id);
	}

	@Tag(name = "Radio")
	@GetMapping("radios/search/")
	public ResponseEntity<Object> showbyNameContain(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) Long categ ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
		
			return fnc.radio_search(s, categ, langue, pays, p);
		
	}

	@Tag(name = "Radio")
	@PutMapping(path="radios/update/{id}")
	public ResponseEntity<Object> updateR(@PathVariable Long id,
			@Valid @RequestBody Radio r) {

		if(radioService.checktop10limit() != null && r.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 radios en tete sont atteint", "podcast_top10", radioService.checktop10limit()));
		}
		else if(radioService.checktoplimit() != null  && r.isTop()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une radio en vedette", "podcast_vedette", radioService.checktoplimit()));
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, radioService.upadte(id, r));
		
		}

	}

	@Tag(name = "Radio")
	@PutMapping(path="radios/update/status/{id}")
	public ResponseEntity<Object> updateStatusR(@PathVariable Long id,
			@Valid @RequestBody StatusDTO status) {

		Radio r = radioService.showById(id).get();
		r.setStatus(status.getStatus());
		
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, radioService.upadte(id, r));

	}
	
	@Tag(name = "Radio")
	@DeleteMapping(path="radios/delete/{id}")
	public ResponseEntity<Object> deleteR (@PathVariable Long id) {

		radioService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	/*
	 * 
	 * ROUTES LiveTV
	 * 
	 * 
	 */

	@Tag(name = "TV SHOW")
	@PostMapping(path="tv/create")
	public ResponseEntity<Object> createL(
			@Valid @RequestBody LiveTv lt) {

		if(liveService.findByName(lt.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name", "Cette chaine tv existe déja"));
		}
		if(liveService.checktop10limit() != null  && lt.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 chaines tv en tete sont atteint", "podcast_top10", liveService.checktop10limit()));
		}
		else if(liveService.checktoplimit() != null && lt.isTop()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une chaine tv en vedette", "podcast_vedette", liveService.checktoplimit()));
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
		
		return fnc.tv_show_page(p, genre, langue, pays);
	}
	
	@Tag(name = "TV SHOW")
	@GetMapping("tv/search/")
	public ResponseEntity<Object> showLbyNameContainL(
			@RequestParam String s, Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){
			
		return fnc.tv_search(s, p, genre, langue, pays);
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

		if(liveService.checktop10limit() != null && lt.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 chaines tv en tete sont atteint", "podcast_top10", liveService.checktop10limit()));
		}
		else if(liveService.checktoplimit() != null && lt.isTop()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une chaine tv en vedette", "podcast_vedette", liveService.checktoplimit()));
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.update(id, lt));
		}
	}
	
	
	@Tag(name = "TV SHOW")
	@PutMapping(path="tv/update/status/{id}")
	public  ResponseEntity<Object> updateStatusL(
			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status) {

		LiveTv lt = liveService.showById(id).get();
		lt.setStatus(status.getStatus());
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
		
		if(catLiveRep.findByName(u.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name", "Cette categorie de lıve existe déja"));
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
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.upadte(id, u));

	}
	@Tag(name = "Categorie Lives")
	@PutMapping(path="lives/categ/update/status/{id}")
	public ResponseEntity<Object> updatStatuseCL(

			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status){
		
		CategorieLive cl = categliveService.showById(id).get();
		cl.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.upadte(id, cl));

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

		if(lService.findByName(p.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name", "Ce live existe déja"));
		}
		else if(lService.checktop10limit() != null && p.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 lives en tete sont atteint", "podcast_top10", lService.checktop10limit()));
		}
		else if(lService.checktoplimit() != null && p.isTop() ) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja un live en vedette", "podcast_vedette", lService.checktoplimit()));
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
		
		if(lService.checktop10limit() != null && l.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 lives en tete sont atteint", "podcast_top10", lService.checktop10limit()));
		}
		else if(lService.checktoplimit() != null && l.isTop() ) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja un live en vedette", "podcast_vedette", lService.checktoplimit()));
		}
		else{
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, lService.update(id, l) );
		}
	}
	
	@Tag(name = "Lives")
	@PutMapping(path="lives/update/status/{id}")
	public ResponseEntity<Object> updateLives(
			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status) {
		

		Live l = lService.showById(id).get();
		l.setStatus(status.getStatus());
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
			
			if(categ != null && langue == null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByCateg(categ, p));
			}
			else if(langue != null && categ == null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByLang(langue, p));
			}
			else if(langue != null && categ != null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByGenreAndLang(categ, langue, p));
			}
			else {

				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showPage(p));
			}
		}

		@Tag(name = "Podcast")
		@PostMapping(path="podcasts/create")
		public ResponseEntity<Object> createP(
				@Valid @RequestBody Podcast p){

			if(podcastservice.findByName(p.getName()) != null) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
						Map.of("name", "Ce Podcast existe déja"));
			}
			else if(podcastservice.checktop10limit() != null && p.isTop10()) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
						Map.of("top10", "les 10 podcasts en tete sont atteint", "podcast_top10", podcastservice.checktop10limit()));
			}
			else if(podcastservice.checktoplimit() != null && p.isTop()) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
						Map.of("top", "il y a deja un podcast en vedette", "podcast_vedette", podcastservice.checktoplimit()));
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
			
			if(categ != null && langue == null ) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByCateg(s, categ, p));
			}
			else if(langue != null && categ == null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByLang(s, langue, p));
			}
			else if(langue != null && categ != null) {
				
				return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByGenreAndLang(s, categ, langue, p));
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

			if(podcastservice.findByName(p.getName()) != null) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
						Map.of("name", "Ce Podcast existe déja"));
			}
			else if(podcastservice.checktop10limit() != null && p.isTop10() ) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
						Map.of("top10", "les 10 podcasts en tete sont atteint", "podcast_top10", podcastservice.checktop10limit()));
			}
			else if(podcastservice.checktoplimit() != null && p.isTop()) {
				
				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
						Map.of("top", "il y a deja un podcast en vedette", "podcast_vedette", podcastservice.checktoplimit()));
			}
			else {
				return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, podcastservice.upadte(id, p) );
				
			}

		}
		
		@Tag(name = "Podcast")
		@PutMapping(path="podcasts/update/status/{id}")
		public ResponseEntity<Object> updateStatusPodcast(
				@PathVariable Long id,
				@Valid @RequestBody StatusDTO status) {


			Podcast p = podcastservice.showById(id).get();
			p.setStatus(status.getStatus());
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
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){

			return fnc.film_show_page(p, genre, langue, pays);

	}

	@Tag(name = "Movie")
	@PostMapping(path="movies/create")
	public ResponseEntity<Object> createM(
			@RequestBody Film film) {

		if(filmService.findByName(film.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name", "Ce film existe déja"));
		}
		else {
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, filmService.create(film));
		}


	}

	@Tag(name = "Movie")
	@GetMapping("movies/{id}")
	public ResponseEntity<Object> showbyIdM(@PathVariable Long id){

		return fnc.film_show_by_id(id);
	}

	@Tag(name = "Movie")
	@GetMapping("movies/search/")
	public ResponseEntity<Object> search(
			@RequestParam String s, 
			Pageable p,
			@RequestParam (required = false) Long genre ,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long pays){

			return fnc.film_search(s, p, genre, langue, pays);
		
	}

	@Tag(name = "Movie")
	@PutMapping(path="movies/update/{id}")
	public ResponseEntity<Object> updateM(@PathVariable Long id,
			@Valid @RequestBody Film film)  {
		
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, filmService.upadte(id, film));

	}
	
	@Tag(name = "Movie")
	@PutMapping(path="movies/update/status/{id}")
	public ResponseEntity<Object> updateM(@PathVariable Long id,
			@Valid @RequestBody StatusDTO status)  {
		Film film = filmService.showById(id).get();
		film.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, filmService.upadte(id, film));

	}

	@Tag(name = "Movie")
	@DeleteMapping(path="movies/delete/{id}")
	public ResponseEntity<Object> deleteM (@PathVariable Long id) {

		filmService.delete(id);
        
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	
	/*
	 * 
	 * ROUTES SUR LES SERIES
	 * 
	 * 
	 */

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
	

	@Tag(name = "Serie")
	@PostMapping(path="series/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Serie serie){
			
		if(serieService.findByName(serie.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name", "Cette serie existe déja"));
		}
		else if(serieService.checktop10limit() != null && serie.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 series en tete sont atteint", "podcast_top10", serieService.checktop10limit()));
		}
		else if(serieService.checktoplimit() != null && serie.isTop()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une serie en vedette", "podcast_vedette", serieService.checktoplimit()));
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

	@Tag(name = "Serie")
	@PutMapping(path="series/update/{id}")
	public ResponseEntity<Object> updateS(
			@PathVariable Long id,
			@Valid @RequestBody Serie serie){

		if(serieService.checktop10limit() != null && serie.isTop10()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top10", "les 10 series en tete sont atteint", "podcast_top10", serieService.checktop10limit()));
		}
		else if(serieService.checktoplimit() != null && serie.isTop()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("top", "il y a deja une serie en vedette", "podcast_vedette", serieService.checktoplimit()));
		}
		else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , serieService.upadte(id, serie));
		}
	}
	
	@Tag(name = "Serie")
	@PutMapping(path="series/update/status/{id}")
	public ResponseEntity<Object> updateStatusSerie(
			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status){

			Serie serie = serieService.showById(id).get();
			serie.setStatus(status.getStatus());
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , serieService.upadte(id, serie));

	}

	@Tag(name = "Serie")
	@DeleteMapping(path="series/delete/{id}")
	public ResponseEntity<Object> deleteS (@PathVariable Long id) {
		
		if(serieService.showById(id).isEmpty()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					"Vous tentez de supprimer une serie qui n'existe pas");
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
	public ResponseEntity<Object> showSaisonPage(
			Pageable p,
			@RequestParam (required = false) Long langue,
			@RequestParam (required = false) Long serie ){
		
		if(langue != null && serie == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByLangue(langue, p));
		}
		else if (serie != null && langue != null) {
					
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByidSerie(serie, p));

		}
		else if (langue != null && serie != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByLangueAndSerie(langue, serie, p));

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

		
		
		//Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED , saisonService.create(saison));

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
			@RequestParam (required = false) Long serie,
			Pageable p){
		
		if(langue != null && serie == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.searchByLangue(s, langue, p));
		}
		else if (serie != null && langue != null) {
					
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.searchBySerie(s, serie, p));

		}
		else if (langue != null && serie != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.searchByLangueAndSerie(s, langue, serie, p));

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

			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , saisonService.update(id, saison));

	}
	
	@Tag(name = "Saison")
	@PutMapping(path="saisons/update/status/{id}")
	public ResponseEntity<Object> updateStatusSaison(
			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status){

			Saison saison = saisonService.showById(id);
			saison.setStatus(status.getStatus());
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK , saisonService.update(id, saison));

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
    		@RequestParam (required = false) Long saison,
			@RequestParam (required = false) Long langue){
		
		if(langue != null && serie == null && saison == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showByLangue(langue, p));
		}
		else if(saison != null && langue == null && serie==null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySaison(saison, p));
		}
		else if(serie != null && langue == null && saison == null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySerie(serie, p));
		}
		else if(serie != null && saison != null && langue == null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySaisonAndSerie(serie, saison, p));
		}
		else if(serie != null && langue != null && saison != null) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySaisonAndLangueAndSerie(serie, langue, saison, p));
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
			@RequestParam (required = false) Long saison,
			@RequestParam (required = false) Long langue){
		
		if(langue != null && saison == null && serie == null) {
			
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchByLangue(s, langue, p));
			
		}
		else if(saison != null && langue == null && serie == null) {
			
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchBySaison(s, saison, p));
			
		}
		
		else if(serie != null && langue == null && saison == null) {
			
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchBySerie(s, serie, p));
			
		}
		
		else if(serie != null && saison != null && langue == null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchBySaisonAndSerie(s, serie, saison, p));
		}
		
		else if(serie != null && langue != null && saison != null) {
			
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchBySaisonAndLangueAndSerie(s, serie, langue, saison, p)); 
		}
		
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.search(s, p));
    	
		}
	}
    
    

    
    @Tag(name = "Episode")
	@PostMapping(path="episodes/create")
	public ResponseEntity<Object> createE(
			@Valid @RequestBody Episode episode) {
    	
    	//controle du nom de l episode 
		int nb = repEpisode.findByIdSaison(episode.getIdSaison())
				.stream()
				.filter( e -> e.getName().contains(episode.getName()) )
				.toList().size();
		
		//Controle du numero de l episode 
		int nbEp = repEpisode.findByNumeroAndIdSerie(episode.getNumero(), episode.getIdSerie())
				.stream()
				.toList().size();
		
		if(nb>0 ) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("name","Pour cette saison ce nom existe déja"));
			
		}
		else if (nbEp>0) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
					Map.of("numero","Pour cette saison ce numero existe déja") );
		}
		else{
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, episodeService.create(episode));
		}
	}

    @Tag(name = "Episode")
	@PutMapping(path="episodes/update/{id}")
	public ResponseEntity<Object> updateE(
			@PathVariable Long id,
			@Valid @RequestBody Episode episode){

    	
    	
		Episode old_Ep = repEpisode.findById(id).get();
		//Save du tout
    	
    	//controle du nom de l episode 
    			int nb = repEpisode.findByIdSaison(episode.getIdSaison())
    					.stream()
    					.filter( e -> e.getName().contains(episode.getName()) )
    					.toList().size();
    			
    			//Controle du numero de l episode 
    			int nbEp = repEpisode.findByNumeroAndIdSerie(episode.getNumero(), episode.getIdSerie())
    					.stream()
    					.toList().size();
    			
    			if(nb>0 && !old_Ep.getName().contains(episode.getName())) {
    				
    				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
    						Map.of("name","Pour cette saison ce nom existe déja" ));
    			}
    			
    			else if (nbEp>0 && old_Ep.getNumero() != episode.getNumero()) {
    				
    				return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, 
    						Map.of("numero","Pour cette saison ce numero existe déja") );
    			}
    			else{
    				
    				return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, episodeService.upadte(id, episode));
		}

	}
    
    @Tag(name = "Episode")
	@PutMapping(path="episodes/update/status/{id}")
	public ResponseEntity<Object> updateStatusEpisode(
			@PathVariable Long id,
			@Valid @RequestBody StatusDTO status){

		Episode episode = episodeService.showById(id).get();
		episode.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, episodeService.upadte(id, episode));

	}

    @Tag(name = "Episode")
	@DeleteMapping(path="episodes/delete/{id}")
	public ResponseEntity<Object> deleteE (@PathVariable Long id) {

		episodeService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}
    //FIN EPISODE
    
    
   
    
}


