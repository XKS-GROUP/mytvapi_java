package com.mytv.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.mytv.api.model.gestMedia.CategoryRL;
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
import com.mytv.api.repository.CatPodcastRepository;
import com.mytv.api.repository.CategoryLrRepository;
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
   
    
	//private final String asset ="/RESSOURCES/IMG/";

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
	@PutMapping("lang/update/{id}")
	public Language updateLang(@PathVariable Long id, @RequestBody Language u){

		return langService.upadte(id, u);

	}

	@Tag(name = "Langue")
	@PostMapping(path="lang/create")
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
	public ResponseEntity<Object> deletePays (@PathVariable Long id) {

		paysService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT , true);
	}


	//Genre

	/*@Tag(name = "genre")
	@PostMapping(path="genres/create")

	public Genre createG(
			@Valid @RequestBody Genre g,
			@RequestParam("img_file") MultipartFile file) throws IOException{

		//Enregistrement du fichier img
		String pathImg = metadataService.uploadR3(file, this.asset);

		if (file.isEmpty())
            throw new IllegalStateException("Vous n'avez charger aucune image");

		g.setImg(pathImg);

		return genreService.create(g);
	}*/
	@Tag(name = "Genre FILM SERIE")
	@PostMapping(path="genres/create")

	public ResponseEntity<Object> create(@Valid @RequestBody Genre g) {
		
			
		if(!genreService.findByNameContain(g.getName()).isEmpty()) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.CONFLICT, "Ce genre existe déja");
		}
		else {
			
		    g.setName(g.getName().toLowerCase());
		    return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, genreService.create(g));
		
		}
	}

	@Tag(name = "Genre FILM SERIE", description = " Liste des genres")
	@GetMapping("genres")
	public List<Genre> showG(){

		return genreService.show();
	}

	@Tag(name = "Genre FILM SERIE", description = " Liste des genres avec laravel ")
	@GetMapping("genres/all/whith-pages")
	public Page<Genre> showPage(Pageable p){

		return genreService.showByPages(p);
	}

	@Tag(name = "Genre FILM SERIE", description = " Recherche par nom")
	@GetMapping("genres/search/byName/{name}")
	public Genre showByName(@Valid @PathVariable String name){

		return genreService.showByName(name);
	}

	@Tag(name = "Genre FILM SERIE", description = " Recherche par valeur")
	@GetMapping("genres/search/byNameC/{name}")
	public List<Genre> showByNameContain(@Valid @PathVariable String name){

		return genreService.findByNameContain(name);
	}


	@Tag(name = "Genre FILM SERIE", description = " Liste des genres")
	@GetMapping("genres/{id}")
	public Genre showbyIdG(@PathVariable Long id){

		return genreService.showById(id).orElseThrow(() -> new ResourceNotFoundException("aucune donne avec id= " + id));
	}

	@Tag(name = "Genre FILM SERIE", description = " Liste des genres")
	@PutMapping("genres/update/{id}")
	public Genre updateG(@PathVariable Long id, @RequestBody Genre g){

		return genreService.upadte(id, g);

	}

	@Tag(name = "Genre FILM SERIE", description = " Liste des genres")
	@DeleteMapping(path="genres/delete/{id}")
	public Boolean deleteG (@PathVariable Long id) {

		genreService.delete(id);

		return true;
	}



	//Categorie LiveTv ou Radio

	@Tag(name = "Categorie Radio et live ")
	@PostMapping(path="catrl/create")
	public ResponseEntity<Object> createCRL(@Valid @RequestBody CategoryRL u) {
		
		if(catlrRep.findByName(u.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.CONFLICT, "Cette categorie existe déja");
		}
		else {
			
		
		    return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, catLrService.create(u));
		
		}
	}


	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl")
	public List<CategoryRL> showCRL(){

		return catLrService.show();
	}


	@Tag(name = "Categorie RADIO LIVE ")
	@GetMapping("catrl/{id}")
	public Optional<CategoryRL> showbyIdCRL(@PathVariable Long id){

		return catLrService.showById(id);
	}


	@Tag(name = "Categorie RADIO LIVE ")
	@PutMapping(path="catrl/update/{id}")
	public CategoryRL updateCRL(
			@PathVariable Long id,
			@RequestBody CategoryRL u) {

		return catLrService.upadte(id, u);

	}


	@Tag(name = "Categorie RADIO LIVE ")
	@DeleteMapping(path="catrl/delete/{id}")
	public Boolean deleteCRL (@PathVariable Long id) {

		catLrService.delete(id);


		return true;
	}


	//Categorie Podcast


	@Tag(name = "Categorie PODCAST")

	@PostMapping(path="catpod/create")

	public ResponseEntity<Object> createCP(
			@Valid @RequestBody CatPodcast u) {

		
		if(catPodRep.findByName(u.getName()) != null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.CONFLICT, "Cette categorie de podcast existe déja");
		}
		else {
			
		
		    return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, catpodService.create(u));
		
		}
	}


	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod")
	public List<CatPodcast> showCP(){

		return catpodService.show();
	}


	@Tag(name = "Categorie PODCAST")
	@GetMapping("catpod/{id}")
	public Optional<CatPodcast> showbyIdCP(@PathVariable Long id){

		return catpodService.showById(id);
	}

	@Tag(name = "Categorie PODCAST")
	@PutMapping(path="catpod/update/{id}")
	public CatPodcast updateCP(

			@PathVariable Long id,
			@RequestBody CatPodcast u){

		return catpodService.upadte(id, u);

	}

	@Tag(name = "Categorie PODCAST")
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
	@PostMapping(path="radios/create")
	public  ResponseEntity<Object> createR(@Valid
			@RequestBody Radio r ){


			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.create(r));
	}

	@Tag(name = "Radio")
	@GetMapping("radios/{id}")
	public Optional<Radio> showbyIdR(@PathVariable Long id){

		return radioService.showById(id);
	}

	@Tag(name = "Radio")
	@GetMapping("radios/search/byName/{name}")
	public List<Radio> showbyNameContain(@PathVariable String nom){

		return radioService.showByNameContaining(nom);
	}

	@Tag(name = "Radio")
	@PutMapping(path="radios/update/{id}")
	public ResponseEntity<Object> updateR(@PathVariable Long id,
			@RequestBody Radio r) {

		//Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.upadte(id, r));

	}

	@Tag(name = "Radio")
	@DeleteMapping(path="radios/delete/{id}")
	public ResponseEntity<Object> deleteR (@PathVariable Long id) {

		radioService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true);
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
	public List<LiveTv> showL(){

		return liveService.show();
	}

	@Tag(name = "LiveTv")
	@GetMapping("lives/search/byName/{nom}")
	public List<LiveTv> showLbyNameContainL(@PathVariable String nom){

		return liveService.showByNameContaining(nom);
	}

	@Tag(name = "LiveTv")
	@GetMapping("lives/{id}")
	public Optional<LiveTv> showbyIdL(@PathVariable Long id){

		return liveService.showById(id);
	}

	@Tag(name = "LiveTv")
	@PutMapping(path="lives/update/{id}")
	public  ResponseEntity<Object> updateL(
			@PathVariable Long id,
			@Valid @RequestBody LiveTv lt) {


		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.create(lt));


	}

	@Tag(name = "LiveTv")
	@DeleteMapping(path="lives/delete/{id}")
	public ResponseEntity<Object> deleteL (@PathVariable Long id) {

		liveService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true);
	}

	//Podcast

	@Tag(name = "Podcast")
	@GetMapping("podcasts")
	public List<Podcast> showP(){

		return podcastservice.show();
	}


	@Tag(name = "Podcast")
	@PostMapping(path="podcasts/create")
	public ResponseEntity<Object> createP(
			@RequestBody Podcast p){

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.create(p) );

	}


	@Tag(name = "Podcast")
	@GetMapping("podcasts/{id}")
	public Optional<Podcast> showbyIdP(@PathVariable Long id){

		return podcastservice.showById(id);
	}

	@Tag(name = "Podcast")
	@GetMapping("podcasts/search/byName/{name}")
	public List<Podcast> showbyIdP(@PathVariable String name){

		return podcastservice.showByNameContaining(name);
	}

	@Tag(name = "Podcast")
	@PutMapping(path="podcasts/update/{id}")
	public ResponseEntity<Object> updateP(
			@PathVariable Long id,
			@RequestBody Podcast p) {

			//Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.upadte(id, p) );

	}

	@Tag(name = "Podcast")
	@DeleteMapping(path="podcasts/delete/{id}")
	public ResponseEntity<Object> deleteP (@PathVariable Long id) {

		liveService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true );
	}


	//Films

	@Tag(name = "Movie")
	@GetMapping("movies")
	public List<Film> showM(){

		return filmService.show();
	}

	@Tag(name = "Movie")
	@PostMapping(path="movies/create")
	public ResponseEntity<Object> createM(
			@RequestBody Film film) {

			//Save du tout
			return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, filmService.create(film));


	}

	@Tag(name = "Movie")
	@GetMapping("movies/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){

		return filmService.showById(id);
	}

	@Tag(name = "Movie")
	@GetMapping("movies/search/byName/{id}")
	public List<Film> showbyIdM(@PathVariable String name){

		return filmService.showByNameContaining(name);
	}

	@Tag(name = "Movie")
	@PutMapping(path="movies/update/{id}")
	public ResponseEntity<Object> updateM(@PathVariable Long id,
			@RequestBody Film film)  {
		return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, filmService.upadte(id, film));

	}

	@Tag(name = "Movie")
	@DeleteMapping(path="movies/delete/{id}")
	public ResponseEntity<Object> deleteM (@PathVariable Long id) {

		filmService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true);
	}

	//Series

	@Tag(name = "Serie")
	@GetMapping("series")
	public List<Serie> showS(){

		return serieService.show();
	}

	@Tag(name = "Serie")
	@PostMapping(path="series/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Serie serie){

			//Save du tout
			return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.CREATED , serieService.create(serie));


	}

	@Tag(name = "Serie")
	@GetMapping("series/{id}")
	public Optional<Serie> showbyIdS(@PathVariable Long id){

		return serieService.showById(id);
	}

	@Tag(name = "Serie")
	@GetMapping("series/search/byName/{name}")
	public List<Serie> showbyIdS(@PathVariable String name){

		return serieService.showbyNameContaining(name);
	}

	@Tag(name = "Serie")
	@PutMapping(path="series/update/{id}")
	public ResponseEntity<Object> updateS(
			@PathVariable Long id,
			@RequestBody Serie serie){

			return EntityResponse.generateResponse("Type de media non supporter", HttpStatus.CREATED , serieService.upadte(id, serie));

	}

	@Tag(name = "Serie")
	@DeleteMapping(path="series/delete/{id}")
	public ResponseEntity<Object> deleteS (@PathVariable Long id) {

		serieService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true);
	}

	//SAISON
	/*
	 *  CRUD DES SAISONS DE SERIE
	 */
	
	@Tag(name = "Saison")
	@GetMapping("Saisons")
	public List<Saison> showSaison(){

		return saisonService.show();
	}

	@Tag(name = "Saison")
	@PostMapping(path="saison/create" )
	public ResponseEntity<Object> createS(
			@Valid @RequestBody Saison saison){

			//Save du tout
			return EntityResponse.generateResponse("Enregistré avec succès", HttpStatus.CREATED , saisonService.create(saison));


	}

	@Tag(name = "Saison")
	@GetMapping("saison/{id}")
	public Saison showbyIdSaison(@PathVariable Long id){

		return saisonService.showById(id);
	}

	@Tag(name = "Saison")
	@GetMapping("saison/search/byName/{name}")
	public List<Saison> showbyNameC(@PathVariable String name){

		return saisonService.showByNameContaining(name);
		
	}

	@Tag(name = "Saison")
	@PutMapping(path="saison/update/{id}")
	public ResponseEntity<Object> updateSaison(
			@PathVariable Long id,
			@RequestBody Saison saison){

			return EntityResponse.generateResponse("Saison creer avec succès", HttpStatus.OK , saisonService.upadte(id, saison));

	}

	@Tag(name = "Saison")
	@DeleteMapping(path="saison/delete/{id}")
	public ResponseEntity<Object> deleteSaison (@PathVariable Long id) {

		saisonService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true);
	}
	
	
	
	
	
	
	//FIN SAISON

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
	@GetMapping("episodes/search/byName/{name}")
	public List<Episode> showbyIdE(@PathVariable String name){

		return episodeService.showByNameContain(name);
	}

    @Tag(name = "Episode")
	@PostMapping(path="episodes/create")
	public ResponseEntity<Object> createE(
			@Valid @RequestBody Episode episode) {

		return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, episodeService.create(episode));


	}

    @Tag(name = "Episode")
	@PutMapping(path="episodes/update/{id}")
	public ResponseEntity<Object> updateE(
			Long id,
			@Valid @RequestBody Episode episode){

		//Save du tout
		return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, episodeService.upadte(id, episode));

	}

    @Tag(name = "Episode")
	@DeleteMapping(path="episodes/delete/{id}")
	public ResponseEntity<Object> deleteE (@PathVariable Long id) {

		episodeService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.NO_CONTENT, true);
	}

    /* AWS R2 FILE CRUD  */

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/findall")
    public List<FileMeta> all() {

    	return metadataService.list();
    }

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/byId/{idFile}")
    public Optional<FileMeta> findbyid(@PathVariable int idFile) {

		return fileMetaRep.findById(idFile);
    }

	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/byName/{name}")
    public List<FileMeta> findbyname(@PathVariable String name) {

		return fileMetaRep.findByFileNameContaining(name);
    }

	@Tag(name = "R2-CLOUDFLARE")
    @PostMapping(path="r2/uploadfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public FileMeta upload(@RequestParam("file") MultipartFile file) throws IOException {

		return metadataService.uploadR3(file, "");

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

			return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, metadataService.uploadR3(file, newFolder));
		 }
    }

	@Tag(name = "R2-CLOUDFLARE")
    @DeleteMapping("deleteFile/{id}")
    public ResponseEntity<Object>  deleteByName(@PathVariable  int id){


		if (fileMetaRep.findById(id)==null) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.CREATED, "Vous tentez de supprimez un objet qui n'existe pas ");

		}
		else {


			Optional<FileMeta> fm = fileMetaRep.findById(id);

			System.out.println(fm.get().getFileName());

			System.out.println(fm.get().getFilePath());

			awsService.deleteR2( fm.get().getFilePath() , fm.get().getFileName());

			fileMetaRep.deleteById(id);

			return EntityResponse.generateResponse("Succes", HttpStatus.NO_CONTENT, "Supprimé");
		}

	}


}


