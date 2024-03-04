package com.mytv.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.accessanalyzer.model.ResourceNotFoundException;
import com.mytv.api.aws.MetadataService;
import com.mytv.api.model.gestMedia.CatPodcast;
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.Language;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestMedia.Serie;
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
    private MetadataService metadataService;
	@Autowired
	private LangueService langService;
	
	private final String asset ="/RESSOURCES/IMG/";
	
	//Langue
	
	@Tag(name = "Langue")
	@GetMapping("langs")
	public List<Language> showLang(){
		
		return langService.show();
	}
	
	@Tag(name = "Langue")
	@GetMapping("langs/{id}")
	public Optional<Language> showLangById(@PathVariable Long id){
		
		return langService.showById(id);
	}
	
	@Tag(name = "Langue")
	@PutMapping("langs/update/{id}")
	public Language updateLang(@PathVariable Long id, @RequestBody Language u){
		
		return langService.upadte(id, u);
		
	}
	
	@Tag(name = "Langue")
	@PostMapping(path="langs/create")
	public Language createLang(@RequestBody Language u) {
		
		return langService.create(u);
	}
	
	@Tag(name = "Langue")
	@DeleteMapping(path="langs/delete/{id}")
	public Boolean delete (@PathVariable Long id) {
		
		langService.delete(id);
		
		return true;
	}
	
	@Tag(name = "Langue", description = " Recherche par nom")
	@GetMapping("langsbyname/{name}")
	public Language showLangByName(@Valid @PathVariable String name){
		
		return langService.showByName(name);
	}
	
	//Pays
	@Tag(name = "Pays")
	@PostMapping("pays/create")

	public Pays createPays(@Valid @RequestBody Pays u) {
		
		return paysService.create(u);
	}
	
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
	
	@Tag(name = "Pays")
	@PutMapping("pays/update/{id}")
	public Pays updatePays(@PathVariable Long id, @RequestBody Pays u){
		
		return paysService.upadte(id, u);
		
	}
	
	@Tag(name = "Pays")
	@DeleteMapping(path="pays/delete/{id}")
	public Boolean deletePays (@PathVariable Long id) {
		
		paysService.delete(id);
		
		return true;
	}
	
	
	//Genre 
	
	@Tag(name = "genre")
	@PostMapping(path="genres/create")

	public Genre createG(
			@Valid @ModelAttribute Genre g, 
			@RequestParam("img_file") MultipartFile file) throws IOException{
			
		//Enregistrement du fichier img
		String pathImg = metadataService.uploadR3(file, this.asset);
		
		if (file.isEmpty())
            throw new IllegalStateException("Vous n'avez charger aucune image");	
		
		g.setImg(pathImg);
		
		return genreService.create(g);
	}
	
	@Tag(name = "genre", description = " Liste des genres")
	@GetMapping("genres")
	public List<Genre> showG(){
		
		return genreService.show();
	}
	
	@Tag(name = "genre", description = " Recherche par nom")
	@GetMapping("genresbyname/{name}")
	public Genre showByName(@Valid @PathVariable String name){
		
		return genreService.showByName(name);
	}
	
	@Tag(name = "genre", description = " Recherche par valeur")
	@GetMapping("genresbynameContain/{name}")
	public List<Genre> showByNameContain(@Valid @PathVariable String name){
		
		return genreService.findByNameContain(name);
	}
	
	
	@Tag(name = "genre", description = " Liste des genres")
	@GetMapping("genres/{id}")
	public Genre showbyIdG(@PathVariable Long id){
		
		return genreService.showById(id).orElseThrow(() -> new ResourceNotFoundException("aucune donne avec id= " + id));
	}
	
	@Tag(name = "genre", description = " Liste des genres")
	@PutMapping("genres/update/{id}")
	public Genre updateG(@PathVariable Long id, 
			@RequestBody Genre g, 
			@RequestParam("img_file") MultipartFile file) throws IOException{
		
		//Enregistrement du fichier img
		String pathImg = metadataService.uploadR3(file, this.asset);
		
		if (file.isEmpty())
            throw new IllegalStateException("Vous n'avez charger aucune image");	
		
		g.setImg(pathImg);
		
		return genreService.upadte(id, g);
		
	}
	
	@Tag(name = "genre", description = " Liste des genres")
	@DeleteMapping(path="genres/delete/{id}")
	public Boolean deleteG (@PathVariable Long id) {
		
		genreService.delete(id);
		
		return true;
	}
	
	
	
	//Categorie LiveTv ou Radio 
	
	@Tag(name = "Categorie Radio et live ")
	@PostMapping(path="catrl/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })

	public CategoryRL createCRL(
			@Valid @ModelAttribute CategoryRL u, 
			@RequestParam("img_file") MultipartFile file) 
					throws IOException {
		
		String pathImg = metadataService.uploadR3(file, this.asset);
		
		if (file.isEmpty())
            throw new IllegalStateException("Vous n'avez charger aucune image");	
		
		u.setImg(pathImg);
		
		return catLrService.create(u);
	}
	
	
	@Tag(name = "Categorie Radio et live ")
	@GetMapping("catrl")
	public List<CategoryRL> showCRL(){
		
		return catLrService.show();
	}
	
	
	@Tag(name = "Categorie Radio et live ")
	@GetMapping("catrl/{id}")
	public Optional<CategoryRL> showbyIdCRL(@PathVariable Long id){
		
		return catLrService.showById(id);
	}
	
	
	@Tag(name = "Categorie Radio et live ")
	@PutMapping(path="catrl/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public CategoryRL updateCRL(
			@PathVariable Long id, 
			@ModelAttribute CategoryRL u, 
			@RequestParam("img_file") MultipartFile file) 
			throws IOException{
		
		String pathImg = metadataService.uploadR3(file, this.asset);
		
		if (file.isEmpty())
            throw new IllegalStateException("Vous n'avez charger aucune image");	
		
		u.setImg(pathImg);
		return catLrService.upadte(id, u);
		
	}
	
	
	@Tag(name = "Categorie Radio et live ")	
	@DeleteMapping(path="catrl/delete/{id}")
	public Boolean deleteCRL (@PathVariable Long id) {
		
		catLrService.delete(id);
		
		
		return true;
	}
	
	
	//Categorie Podcast
	

	@Tag(name = "Categorie Podcast")
	
	@PostMapping(path="catpod/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })

	public CatPodcast createCP(
			@Valid @ModelAttribute CatPodcast u,
			@RequestParam("img_file") MultipartFile file) 
					throws IOException {
		
		String pathImg = metadataService.uploadR3(file, this.asset);
		
		if (file.isEmpty())
			
            throw new IllegalStateException("Vous n'avez charger aucune image");	
		
		u.setImg(pathImg);
		
		return catpodService.create(u);
	}
	
	
	@Tag(name = "Categorie Podcast")
	@GetMapping("catpod")
	public List<CatPodcast> showCP(){
		
		return catpodService.show();
	}
	
	
	@Tag(name = "Categorie Podcast")
	@GetMapping("catpod/{id}")
	public Optional<CatPodcast> showbyIdCP(@PathVariable Long id){
		
		return catpodService.showById(id);
	}
	
	@Tag(name = "Categorie Podcast")
	@PutMapping(path="catpod/update/{id}")
	public CatPodcast updateCP(
			
			@PathVariable Long id, 
			@ModelAttribute CatPodcast u, 
			@RequestParam("img_file") MultipartFile file) 
					throws IOException{
		
		String pathImg = metadataService.uploadR3(file, this.asset);
		
		if (file.isEmpty())
			
            throw new IllegalStateException("Vous n'avez charger aucune image");	
		
		u.setImg(pathImg);
		return catpodService.upadte(id, u);
		
	}
	
	@Tag(name = "Categorie Podcast")
	@DeleteMapping(path="catpod/delete/{id}")
	public Boolean deleteCP (@PathVariable Long id) {
		
		catpodService.delete(id);
		
		return true;
	}
	
	
	//Radio
	@Tag(name = "Radio")
	@GetMapping("radios")
	public List<Radio> showR(){
		
		return radioService.show();
	}
	
	@Tag(name = "Radio")
	@PostMapping(path="radios/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )//MULTIPART_FORM_DATA_VALUE
	public  ResponseEntity<Object> createR(@Valid 
			@ModelAttribute Radio r, 
			@RequestParam("poster_file") MultipartFile file, 
			@RequestParam("backdrop_file") MultipartFile backdrop) throws IOException {	
	
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, this.asset);
			
			String pathdrop = metadataService.uploadR3(file, this.asset);
			
			if (!file.isEmpty() && !backdrop.isEmpty()) {
				if(!file.getContentType().startsWith("image/") && !backdrop.getContentType().startsWith("image/")){
									
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
				}

			}
	            
			r.setPoster(pathImg);
			r.setBackdrop_path(pathdrop);
		
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.create(r));
	}
	
	@Tag(name = "Radio")
	@GetMapping("radios/{id}")
	public Optional<Radio> showbyIdR(@PathVariable Long id){
		
		return radioService.showById(id);
	}
	
	@Tag(name = "Radio")
	@GetMapping("radiosbynamecontain/{name}")
	public List<Radio> showbyNameContain(@PathVariable String nom){
		
		return radioService.showByNameContaining(nom);
	}
	
	@Tag(name = "Radio")
	@PutMapping(path="radios/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> updateR(@PathVariable Long id, 
			@ModelAttribute Radio r, 
			@RequestParam("poster_file") MultipartFile file, 
			@RequestParam("backdrop_file") MultipartFile backdrop) 
			throws IOException {
		
		
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, this.asset);
			
			String pathdrop = metadataService.uploadR3(file, this.asset);
			
			if (!file.isEmpty() && !backdrop.isEmpty()) {
				if(!file.getContentType().startsWith("image/") && !backdrop.getContentType().startsWith("image/")){
									
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
				}

		}
            
		r.setPoster(pathImg);
		r.setBackdrop_path(pathdrop);
	
		//Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.upadte(id, r));
		
		
		
	}
	
	@Tag(name = "Radio")
	@DeleteMapping(path="radios/delete/{id}")
	public Boolean deleteR (@PathVariable Long id) {
		
		radioService.delete(id);
		
		return true;
	}
	
	//ROUTES LiveTV
	
	@Tag(name = "LiveTv")
	@PostMapping(path="lives/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> createL(
			@Valid @ModelAttribute LiveTv lt, 
			@RequestParam("tvlogo") MultipartFile file) 
					throws IOException {
		
		//Enregistrement du fichier img
		String pathImg = metadataService.uploadR3(file, "/LIVE/"+lt.getName().replaceAll("\\s+", "")+"/LOGO/");
				
		if (!file.isEmpty()) {
			if(!file.getContentType().startsWith("image/") ){
								
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
			}

		}
	        
		lt.setTvLogo(pathImg);
		
		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, liveService.create(lt));
	}
	
	@Tag(name = "LiveTv")
	@GetMapping("lives")
	public List<LiveTv> showL(){
		
		return liveService.show();
	}
	
	@Tag(name = "LiveTv")
	@GetMapping("livesbynamecontain/{nom}")
	public List<LiveTv> showLbyNameContainL(@PathVariable String nom){
		
		return liveService.showByNameContaining(nom);
	}
	
	@Tag(name = "LiveTv")
	@GetMapping("lives/{id}")
	public Optional<LiveTv> showbyIdL(@PathVariable Long id){
		
		return liveService.showById(id);
	}
	
	@Tag(name = "LiveTv")
	@PutMapping(path="lives/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public  ResponseEntity<Object> updateL(
			@PathVariable Long id, 
			@Valid @ModelAttribute LiveTv lt, 
			@RequestParam("tvlogo") MultipartFile file) 
					throws IOException {
		
		//Enregistrement du fichier img
		String pathImg = metadataService.uploadR3(file, "/LIVE/"+lt.getName().replaceAll("\\s+", "")+"/LOGO/");
				
		if (!file.isEmpty()) {
			if(!file.getContentType().startsWith("image/") ){
								
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
			}

		}
	        
		lt.setTvLogo(pathImg);
		
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.create(lt));
		
		
	}
	
	@Tag(name = "LiveTv")
	@DeleteMapping(path="lives/delete/{id}")
	public Boolean deleteL (@PathVariable Long id) {
		
		liveService.delete(id);
		
		return true;
	}
	
	//Podcast
	
	@Tag(name = "Podcast")
	@GetMapping("podcasts")
	public List<Podcast> showP(){
		
		return podcastservice.show();
	}
	
	
	@Tag(name = "Podcast")
	@PostMapping(path="podcasts/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> createP(
			@ModelAttribute Podcast p, 
			@RequestParam("poster_f") MultipartFile poster,
			@RequestParam("backdrop_f") MultipartFile backdrop_path,
			@RequestParam("file_f") MultipartFile file)
		
			throws IOException {
		
			if(!backdrop_path.getContentType().startsWith("image/") || !poster.getContentType().startsWith("image/")){
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une video");
			}
			
			if(!file.getContentType().startsWith("audio/") ){
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader un fichier audio");
			}
			
	            
			
			String pathImg           = metadataService.uploadR3(poster, "/PODCAST/"+p.getName().replaceAll("\\s+", "")+"/POSTER");
			String pathBackdrop_path = metadataService.uploadR3(backdrop_path, "/PODCAST/"+p.getName().replaceAll("\\s+", "")+"/BD");
			String pathFile          = metadataService.uploadR3(file, "/PODCAST/"+p.getName().replaceAll("\\s+", "")+"/FILE");
			
			p.setPoster(pathImg);
			p.setBackdrop_path(pathBackdrop_path);
			p.setFile(pathFile);
		
			//Save du tout
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.create(p) );
	
	}
	
	
	@Tag(name = "Podcast")
	@GetMapping("podcasts/{id}")
	public Optional<Podcast> showbyIdP(@PathVariable Long id){
		
		return podcastservice.showById(id);
	}
	
	@Tag(name = "Podcast")
	@GetMapping("podcastsbynamecontain/{name}")
	public List<Podcast> showbyIdP(@PathVariable String name){
		
		return podcastservice.showByNameContaining(name);
	}
	
	@Tag(name = "Podcast")
	@PutMapping(path="podcasts/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> updateP(
			@PathVariable Long id,
			@ModelAttribute Podcast p,
			@RequestParam("poster") MultipartFile poster,
			@RequestParam("backdrop_path") MultipartFile backdrop_path,
			@RequestParam("file") MultipartFile file)
		
			throws IOException {
		
			
			if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
				
				if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une video");
				}
			}
			
			if (!file.isEmpty()) {
				
				if(!file.getContentType().startsWith("audio/") ){
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une video");
				}
			}
	            
			
			String pathImg = metadataService.uploadR3(file, "/PODCAST/"+p.getName()+"POSTER");
			String pathBackdrop_path = metadataService.uploadR3(file, "/PODCAST/"+p.getName()+"BD");
			String pathFile = metadataService.uploadR3(file, "/PODCAST/"+p.getName()+"FILE");
			p.setPoster(pathImg);
			p.setBackdrop_path(pathBackdrop_path);
			p.setFile(pathFile);
			
			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.upadte(id, p) );
		
	}
	
	@Tag(name = "Podcast")
	@DeleteMapping(path="podcasts/delete/{id}")
	public Boolean deleteP (@PathVariable Long id) {
		
		liveService.delete(id);
		
		return true;
	}
	
	
	//Films
	
	@Tag(name = "Movie")
	@GetMapping("movies")
	public List<Film> showM(){
		
		return filmService.show();
	}
	
	@Tag(name = "Movie")
	@PostMapping(path="movies/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> createM(
			@ModelAttribute Film film, 
			@RequestParam("backdrop_file") MultipartFile backdrop_path, 
			@RequestParam("poster_file") MultipartFile poster,
			@RequestParam("videoFile_file") MultipartFile videoFile,
			@RequestParam("videoFile480pLocal_file") MultipartFile videoFile480pLocal,
			@RequestParam("videoFile720pLocal_file") MultipartFile videoFile720pLocal,
			@RequestParam("videoFile1080pLocal_file") MultipartFile videoFile1080pLocal,
			@RequestParam("trailer_file") MultipartFile trailer) 
			throws IOException {
			
			
			if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
				
				if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une video");
				}
			}
			
			//
			if(!videoFile.isEmpty() 
					|| videoFile480pLocal.isEmpty() 
					|| videoFile720pLocal.isEmpty() 
					|| videoFile1080pLocal.isEmpty()) {
				
				if(!videoFile.getContentType().startsWith("video/") 
						|| videoFile480pLocal.getContentType().startsWith("video/") 
						|| videoFile720pLocal.getContentType().startsWith("video/")  
						|| videoFile1080pLocal.getContentType().startsWith("video/"))  {
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Uploader une image");
				}
				
			}
				
				
			
			String pathFilm = "/FILM/"+film.getName()+"MOVIE/";
			String pathTrailer ="FILM/"+film.getName()+"TRAILER/";
			String pathBack ="FILM/"+film.getName()+"BACKDROP/";
			String pathPoster ="FILM/"+film.getName()+"POSTER/";
			
			
			
			//Enregistrement du fichier img
			String backdropPath = metadataService.uploadR3(backdrop_path, pathBack);
			String postr = metadataService.uploadR3(poster, pathPoster);
			
			//Trailer
			String traile = metadataService.uploadR3(trailer, pathTrailer);
			
			//Video enregistrement
			String videoFil = metadataService.uploadR3(videoFile, pathFilm);
			
			//Differents format disponible
			String videoFile480pL = metadataService.uploadR3(videoFile480pLocal, pathFilm+"/480P/");
			String videoFile720pL = metadataService.uploadR3(videoFile720pLocal, pathFilm+"/720P/");
			String videoFile1080pL = metadataService.uploadR3(videoFile1080pLocal, pathFilm+"/1080P/");
			
			
			//Enregistrement du path du fichier
			
			film.setBackdrop_path(backdropPath);
			film.setPoster(postr);
			film.setTrailer(traile);
			film.setVideoFile(videoFil);
			
			//Ajot des formats videos
			
			film.setVideoFile1080pLocal(videoFile1080pL);
			film.setVideoFile720pLocal(videoFile720pL);
			film.setVideoFile480pLocal(videoFile480pL);
		
			//Save du tout
			return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, filmService.create(film));
		
		
	}
	
	@Tag(name = "Movie")
	@GetMapping("movies/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){
		
		return filmService.showById(id);
	}
	
	@Tag(name = "Movie")
	@GetMapping("moviesbynamecontain/{id}")
	public List<Film> showbyIdM(@PathVariable String name){
		
		return filmService.showByNameContaining(name);
	}
	
	@Tag(name = "Movie")
	@PutMapping(path="movies/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> updateM(@PathVariable Long id,
			@ModelAttribute Film film, 
			@RequestParam("backdrop_file") MultipartFile backdrop_path, 
			@RequestParam("poster_file") MultipartFile poster,
			@RequestParam("videoFile_file") MultipartFile videoFile,
			@RequestParam("videoFile480pLocal_file") MultipartFile videoFile480pLocal,
			@RequestParam("videoFile720pLocal_file") MultipartFile videoFile720pLocal,
			@RequestParam("videoFile1080pLocal_file") MultipartFile videoFile1080pLocal,
			@RequestParam("trailer_file") MultipartFile trailer) throws IOException {
		
		
		if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
			
			if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
			}
		}
		
		//
		if(!videoFile.isEmpty() 
				|| videoFile480pLocal.isEmpty() 
				|| videoFile720pLocal.isEmpty() 
				|| videoFile1080pLocal.isEmpty()) {
			
			if(!videoFile.getContentType().startsWith("video/") 
					|| videoFile480pLocal.getContentType().startsWith("video/") 
					|| videoFile720pLocal.getContentType().startsWith("video/")  
					|| videoFile1080pLocal.getContentType().startsWith("video/"))  {
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Uploader une video");
			}
			
		}
			
			
		
		String pathFilm = "/FILM/"+film.getName()+"MOVIE/";
		String pathTrailer ="FILM/"+film.getName()+"TRAILER/";
		String pathBack ="FILM/"+film.getName()+"BACKDROP/";
		String pathPoster ="FILM/"+film.getName()+"POSTER/";
		
		
		
		//Enregistrement du fichier img
		String backdropPath = metadataService.uploadR3(backdrop_path, pathBack);
		String postr = metadataService.uploadR3(poster, pathPoster);
		
		//Trailer
		String traile = metadataService.uploadR3(trailer, pathTrailer);
		
		//Video enregistrement
		String videoFil = metadataService.uploadR3(videoFile, pathFilm);
		
		//Differents format disponible
		String videoFile480pL = metadataService.uploadR3(videoFile480pLocal, pathFilm+"/480P/");
		String videoFile720pL = metadataService.uploadR3(videoFile720pLocal, pathFilm+"/720P/");
		String videoFile1080pL = metadataService.uploadR3(videoFile1080pLocal, pathFilm+"/1080P/");
		
		
		//Enregistrement du path du fichier
		
		film.setBackdrop_path(backdropPath);
		film.setPoster(postr);
		film.setTrailer(traile);
		film.setVideoFile(videoFil);
		
		//Ajout des formats videos
		
		film.setVideoFile1080pLocal(videoFile1080pL);
		film.setVideoFile720pLocal(videoFile720pL);
		film.setVideoFile480pLocal(videoFile480pL);
	
		//Save du tout
		return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, filmService.upadte(id, film));
	
		
	}
	
	@Tag(name = "Movie")
	@DeleteMapping(path="movies/delete/{id}")
	public Boolean deleteM (@PathVariable Long id) {
		
		filmService.delete(id);
		
		return true;
	}
	
	//Series
	
	@Tag(name = "Serie")
	@GetMapping("series")
	public List<Serie> showS(){
		
		return serieService.show();
	}
	
	@Tag(name = "Serie")
	@PostMapping(path="series/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> createS(
			@Valid @ModelAttribute Serie serie, 
			@RequestParam("backdrop_file") MultipartFile backdrop_path,
			@RequestParam("poster_file") MultipartFile poster,
			@RequestParam("trailer_file") MultipartFile trailer ) 
					throws IOException {
		
			
			//Enregistrement du fichier img
			String pathbackdrop = metadataService.uploadR3(backdrop_path, "/SERIE/"+serie.getName().replaceAll("\\s+", "")+"/BD");
			String pathposter = metadataService.uploadR3(poster, "/SERIE/"+serie.getName().replaceAll("\\s+", "")+"/POSTER");
			
			//Enregistrement du Trailer
			String pathtrailer = metadataService.uploadR3(trailer, "/SERIE/"+serie.getName().replaceAll("\\s+", "")+"/TRAILER");
			
			if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
				
				if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
				}
			}
			
			if (!trailer.isEmpty() ) {
							
				if(!trailer.getContentType().startsWith("video/") ){
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
				}
			}
	            
			
			//Ajout des path dans la db
			serie.setPoster(pathposter);
			serie.setBackdrop_path(pathbackdrop);
			serie.setTrailer(pathtrailer);
		
			//Save du tout
			return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.CREATED , serieService.create(serie));
			
		
	}
	
	@Tag(name = "Serie")
	@GetMapping("series/{id}")
	public Optional<Serie> showbyIdS(@PathVariable Long id){
		
		return serieService.showById(id);
	}
	
	@Tag(name = "Serie")
	@GetMapping("seriesbynamecontain/{name}")
	public List<Serie> showbyIdS(@PathVariable String name){
		
		return serieService.showbyNameContaining(name);
	}
	
	@Tag(name = "Serie")
	@PutMapping(path="series/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> updateS(
			@PathVariable Long id, 
			@ModelAttribute Serie serie,
			@RequestParam("backdrop_file") MultipartFile backdrop_path,
			@RequestParam("poster_file") MultipartFile poster,
			@RequestParam("trailer_file") MultipartFile trailer ) 
					throws IOException {
		
			
			//Enregistrement du fichier img
			String pathbackdrop = metadataService.uploadR3(backdrop_path, "/SERIE/"+serie.getName().replaceAll("\\s+", "")+"/BD");
			String pathposter = metadataService.uploadR3(poster, "/SERIE/"+serie.getName().replaceAll("\\s+", "")+"/POSTER");
			
			//Enregistrement du Trailer
			String pathtrailer = metadataService.uploadR3(trailer, "/SERIE/"+serie.getName().replaceAll("\\s+", "")+"/TRAILER");
			
			if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
				
				if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
					
					
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
				}
			}
			
			if (!trailer.isEmpty() ) {
							
				if(!trailer.getContentType().startsWith("video/") ){
					
					return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
				}
			}
	            
			
			//Ajout des path dans la db
			serie.setPoster(pathposter);
			serie.setBackdrop_path(pathbackdrop);
			serie.setTrailer(pathtrailer);
		
			//Save du tout
			return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.CREATED , serieService.upadte(id, serie));
		
	}
	
	@Tag(name = "Serie")
	@DeleteMapping(path="series/delete/{id}")
	public Boolean deleteS (@PathVariable Long id) {
		
		serieService.delete(id);
		
		return true;
	}

	
	//Episodes
	@Tag(name = "Episode")
	@GetMapping("episodes")
    public List<Episode> showE(){
		
		return episodeService.show();
	}
	
    @Tag(name = "Episode")
	@GetMapping("episodes/{id}")
	public Optional<Episode> showbyIdE(@PathVariable Long id){
		
		return episodeService.showById(id);
	}
    
    @Tag(name = "Episode")
	@GetMapping("episodesbynamecontain/{name}")
	public List<Episode> showbyIdE(@PathVariable String name){
		
		return episodeService.showByNameContain(name);
	}
    
    @Tag(name = "Episode")
	@PostMapping(path="episodes/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> createE(
			@Valid @ModelAttribute Episode episode, 
			@RequestParam("backdrop_file") MultipartFile backdrop_path, 
			@RequestParam("poster_file") MultipartFile poster,
			@RequestParam("videoFile_file") MultipartFile videoFile,
			@RequestParam("videoFile480pLocal_file") MultipartFile videoFile480pLocal,
			@RequestParam("videoFile720pLocal_file") MultipartFile videoFile720pLocal,
			@RequestParam("videoFile1080pLocal_file") MultipartFile videoFile1080pLocal,
			@RequestParam("trailer_file") MultipartFile trailer) throws IOException {
		
		
		if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
			
			if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
			}
		}
		
		//
		if(!videoFile.isEmpty() 
				|| videoFile480pLocal.isEmpty() 
				|| videoFile720pLocal.isEmpty() 
				|| videoFile1080pLocal.isEmpty()) {
			
			if(!videoFile.getContentType().startsWith("video/") 
					|| videoFile480pLocal.getContentType().startsWith("video/") 
					|| videoFile720pLocal.getContentType().startsWith("video/")  
					|| videoFile1080pLocal.getContentType().startsWith("video/"))  {
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Uploader une video");
			}
			
		}
			
			
		
		String pathFilm = "/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/MOVIE/";
		String pathTrailer ="/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/TRAILER/";
		String pathBack ="/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/BACKDROP/";
		String pathPoster ="/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/POSTER/";
		
		
		
		//Enregistrement du fichier img
		String backdropPath = metadataService.uploadR3(backdrop_path, pathBack);
		String postr = metadataService.uploadR3(poster, pathPoster);
		
		//Trailer
		String traile = metadataService.uploadR3(trailer, pathTrailer);
		
		//Video enregistrement
		String videoFil = metadataService.uploadR3(videoFile, pathFilm);
		
		//Differents format disponible
		String videoFile480pL = metadataService.uploadR3(videoFile480pLocal, pathFilm+"/480P/");
		String videoFile720pL = metadataService.uploadR3(videoFile720pLocal, pathFilm+"/720P/");
		String videoFile1080pL = metadataService.uploadR3(videoFile1080pLocal, pathFilm+"/1080P/");
		
		
		//Enregistrement du path du fichier
		
		episode.setThumbnail(backdropPath);
		episode.setPosterUrl(postr);
		episode.setTrailer(traile);
		episode.setVideoFile(videoFil);
		
		//Ajout des formats videos
		
		episode.setVideoFile1080pLocal(videoFile1080pL);
		episode.setVideoFile720pLocal(videoFile720pL);
		episode.setVideoFile480pLocal(videoFile480pL);
	
		//Save du tout
		return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, episodeService.create(episode));

		
	}
	
    @Tag(name = "Episode")
	@PutMapping(path="episodes/update/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> updateE(
			Long id,
			@Valid @ModelAttribute Episode episode, 
			@RequestParam("backdrop_file") MultipartFile backdrop_path, 
			@RequestParam("poster_file") MultipartFile poster,
			@RequestParam("videoFile_file") MultipartFile videoFile,
			@RequestParam("videoFile480pLocal_file") MultipartFile videoFile480pLocal,
			@RequestParam("videoFile720pLocal_file") MultipartFile videoFile720pLocal,
			@RequestParam("videoFile1080pLocal_file") MultipartFile videoFile1080pLocal,
			@RequestParam("trailer_file") MultipartFile trailer) throws IOException {
		
		
		if (!backdrop_path.isEmpty() && !poster.isEmpty()) {
			
			if(!backdrop_path.getContentType().startsWith("image/") && !poster.getContentType().startsWith("image/")){
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, " uploader une image");
			}
		}
		
		//
		if(!videoFile.isEmpty() 
				|| videoFile480pLocal.isEmpty() 
				|| videoFile720pLocal.isEmpty() 
				|| videoFile1080pLocal.isEmpty()) {
			
			if(!videoFile.getContentType().startsWith("video/") 
					|| videoFile480pLocal.getContentType().startsWith("video/") 
					|| videoFile720pLocal.getContentType().startsWith("video/")  
					|| videoFile1080pLocal.getContentType().startsWith("video/"))  {
				
				return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Uploader une video");
			}
			
		}
			
			
		
		String pathFilm = "/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/MOVIE/";
		String pathTrailer ="/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/TRAILER/";
		String pathBack ="/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/BACKDROP/";
		String pathPoster ="/EPISODE/"+episode.getName().replaceAll("\\s+", "")+"/POSTER/";
		
		
		
		//Enregistrement du fichier img
		String backdropPath = metadataService.uploadR3(backdrop_path, pathBack);
		String postr = metadataService.uploadR3(poster, pathPoster);
		
		//Trailer
		String traile = metadataService.uploadR3(trailer, pathTrailer);
		
		//Video enregistrement
		String videoFil = metadataService.uploadR3(videoFile, pathFilm);
		
		//Differents format disponible
		String videoFile480pL = metadataService.uploadR3(videoFile480pLocal, pathFilm+"/480P/");
		String videoFile720pL = metadataService.uploadR3(videoFile720pLocal, pathFilm+"/720P/");
		String videoFile1080pL = metadataService.uploadR3(videoFile1080pLocal, pathFilm+"/1080P/");
		
		
		//Enregistrement du path du fichier
		
		episode.setThumbnail(backdropPath);
		episode.setPosterUrl(postr);
		episode.setTrailer(traile);
		episode.setVideoFile(videoFil);
		
		//Ajout des formats videos
		
		episode.setVideoFile1080pLocal(videoFile1080pL);
		episode.setVideoFile720pLocal(videoFile720pL);
		episode.setVideoFile480pLocal(videoFile480pL);
	
		//Save du tout
		return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, episodeService.upadte(id, episode));
		
	}
	
    @Tag(name = "Episode")
	@DeleteMapping(path="episodes/delete/{id}")
	public Boolean deleteE (@PathVariable Long id) {
		
		episodeService.delete(id);
		
		return true;
	}

}
