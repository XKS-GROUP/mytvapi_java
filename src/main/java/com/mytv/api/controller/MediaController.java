package com.mytv.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mytv.api.aws.MetadataService;
import com.mytv.api.execptions.ErrorResponse;
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

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")

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
	
	
	//Pays
	@PostMapping( path="pays/create")

	public Pays createPays(@Valid @RequestBody Pays u) {
		
		return paysService.create(u);
	}
	
	
	@GetMapping("pays")
	public List<Pays> showPays(){
		
		return paysService.show();
	}
		
	@GetMapping("pays/{id}")
	public Optional<Pays> showbyIdPays(@PathVariable Long id){
		
		return paysService.showById(id);
	}
	
	@PutMapping("pays/update/{id}")
	public Pays updatePays(@PathVariable Long id, @RequestBody Pays u){
		
		return paysService.upadte(id, u);
		
	}
		
	@DeleteMapping(path="pays/delete/{id}")
	public Boolean deletePays (@PathVariable Long id) {
		
		paysService.delete(id);
		
		return true;
	}
	
	
	//Genre 
	@PostMapping(path="genres/create")

	public Genre createG(@ModelAttribute Genre u, @RequestParam("file") MultipartFile file) throws IOException {
		
		
	
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, "");
			if (file.isEmpty())
	            throw new IllegalStateException("Vous n'avez charger aucune image");
			
			//Enregistrement du path du fichier
			
			u.setImageUrl(pathImg);
			//Save du tout
			return genreService.create(u);
		
	}
	
	
	@GetMapping("genres")
	public List<Genre> showG(){
		
		return genreService.show();
	}
		
	@GetMapping("genres/{id}")
	public Optional<Genre> showbyIdG(@PathVariable Long id){
		
		return genreService.showById(id);
	}
	
	@PutMapping("genres/update/{id}")
	public Genre updateG(@PathVariable Long id, @RequestBody Genre u){
		
		return genreService.upadte(id, u);
		
	}
		
	@DeleteMapping(path="genres/delete/{id}")
	public Boolean deleteG (@PathVariable Long id) {
		
		genreService.delete(id);
		
		return true;
	}
	

	
	//Categorie LiveTv ou Radio 
	@PostMapping(path="catrl/create")

	public CategoryRL createCRL(@RequestBody CategoryRL u) {
		
		return catLrService.create(u);
	}
	
	
	@GetMapping("catrl")
	public List<CategoryRL> showCRL(){
		
		return catLrService.show();
	}
		
	@GetMapping("catrl/{id}")
	public Optional<CategoryRL> showbyIdCRL(@PathVariable Long id){
		
		return catLrService.showById(id);
	}
	
	@PutMapping("catrl/update/{id}")
	public CategoryRL updateCRL(@PathVariable Long id, @RequestBody CategoryRL u){
		
		return catLrService.upadte(id, u);
		
	}
		
	@DeleteMapping(path="catrl/delete/{id}")
	public Boolean deleteCRL (@PathVariable Long id) {
		
		catLrService.delete(id);
		
		return true;
	}
	
	
	//Categorie Podcast
	
	@PostMapping(path="catpod/create")

	public CatPodcast createCP(@RequestBody CatPodcast u) {
		
		return catpodService.create(u);
	}
	
	
	@GetMapping("catpod")
	public List<CatPodcast> showCP(){
		
		return catpodService.show();
	}
		
	@GetMapping("catpod/{id}")
	public Optional<CatPodcast> showbyIdCP(@PathVariable Long id){
		
		return catpodService.showById(id);
	}
	
	@PutMapping("catpod/update/{id}")
	public CatPodcast updateCP(@PathVariable Long id, @RequestBody CatPodcast u){
		
		return catpodService.upadte(id, u);
		
	}
		
	@DeleteMapping(path="catpod/delete/{id}")
	public Boolean deleteCP (@PathVariable Long id) {
		
		catpodService.delete(id);
		
		return true;
	}
	
	
	//Radio	
	@GetMapping("radios")
	public List<Radio> showR(){
		
		return radioService.show();
	}
	
	@PostMapping(path="radios/create")
	public Podcast createR(@ModelAttribute Podcast r, @RequestParam("file") MultipartFile file) throws IOException {
		
	
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, "");
			if (file.isEmpty())
	            throw new IllegalStateException("Vous n'avez charger aucune image");
			
			
			r.setPoster(pathImg);
		
			//Save du tout
			return podcastservice.create(r);
		
	}
		
	@GetMapping("radios/{id}")
	public Optional<Radio> showbyIdR(@PathVariable Long id){
		
		return radioService.showById(id);
	}
	
	@PutMapping("radios/update/{id}")
	public Radio updateR(@PathVariable Long id, @RequestBody Radio u){
		
		return radioService.upadte(id, u);
		
	}
		
	@DeleteMapping(path="radios/delete/{id}")
	public Boolean deleteR (@PathVariable Long id) {
		
		radioService.delete(id);
		
		return true;
	}
	
	//ROUTES LiveTV
	@PostMapping(path="lives/create")
	public LiveTv createL(@RequestBody LiveTv u) {
		
		return liveService.create(u);
	}
	
	
	@GetMapping("lives")
	public List<LiveTv> showL(){
		
		return liveService.show();
	}
	
	
	@GetMapping("lives/{id}")
	public Optional<LiveTv> showbyIdL(@PathVariable Long id){
		
		return liveService.showById(id);
	}
	
	@PutMapping("lives/update/{id}")
	public LiveTv updateL(@PathVariable Long id, @RequestBody LiveTv u){
		
		return liveService.upadte(id, u);
		
	}
	
	
	@DeleteMapping(path="lives/delete/{id}")
	public Boolean deleteL (@PathVariable Long id) {
		
		liveService.delete(id);
		
		return true;
	}
	
	//Podcast
	@GetMapping("podcasts")
	public List<Podcast> showP(){
		
		return podcastservice.show();
	}
	
	@PostMapping(path="podcasts/create")
	public Podcast createP(@ModelAttribute Podcast p, @RequestParam("file") MultipartFile file, @RequestParam("movie") MultipartFile movie) throws IOException {
		
	
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, "");
			if (file.isEmpty() || movie.isEmpty())
	            throw new IllegalStateException("Vous n'avez charger aucune image");
			
			String pathmovie = metadataService.uploadR3(movie, "");
			//Enregistrement du path du fichier
			
			p.setPoster(pathImg);
		
			//Save du tout
			return podcastservice.create(p);
		
	}
	
	
	@GetMapping("podcasts/{id}")
	public Optional<Podcast> showbyIdP(@PathVariable Long id){
		
		return podcastservice.showById(id);
	}
	
	@PutMapping("podcasts/update/{id}")
	public Podcast updateP(@PathVariable Long id, @RequestBody Podcast u){
		
		return podcastservice.upadte(id, u);
		
	}
	
	
	@DeleteMapping(path="podcasts/delete/{id}")
	public Boolean deleteP (@PathVariable Long id) {
		
		liveService.delete(id);
		
		return true;
	}
	
	
	//Films
	
	@GetMapping("movies")
	public List<Film> showM(){
		
		return filmService.show();
	}
	
	@PostMapping(path="movies/create")
	public Film createM(@ModelAttribute Film u, @RequestParam("file") MultipartFile file, @RequestParam("movie") MultipartFile movie) throws IOException {
		
	
			
			
			if (file.isEmpty() || movie.isEmpty())
	            throw new IllegalStateException("Vous n'avez charger aucune image");
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, "");
			String pathmovie = metadataService.uploadR3(movie, "");
			
			//Enregistrement du path du fichier
			
			u.setPosterUrl(pathImg);
			u.setDownloadURL(pathmovie);
		
			//Save du tout
			return filmService.create(u);
		
	}
	
	@GetMapping("movies/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){
		
		return filmService.showById(id);
	}
	
	@PutMapping("movies/update/{id}")
	public Film updateM(@PathVariable Long id, @ModelAttribute Film u, @RequestParam("file") MultipartFile file, @RequestParam("movie") MultipartFile movie) throws IOException{
		
		
		//Enregistrement du fichier img
		
		if (file.isEmpty() || movie.isEmpty())
            throw new IllegalStateException("Vous n'avez charger aucune image");
		String pathImg = metadataService.uploadR3(file, "");
		String pathmovie = metadataService.uploadR3(movie, "");
		
		//Enregistrement du path du fichier
		
		u.setPosterUrl(pathImg);
		u.setDownloadURL(pathmovie);
		
		return filmService.upadte(id, u);
		
	}
	
	
	@DeleteMapping(path="movies/delete/{id}")
	public Boolean deleteM (@PathVariable Long id) {
		
		filmService.delete(id);
		
		return true;
	}
	
	//Series
	
	@GetMapping("series")
	public List<Serie> showS(){
		
		return serieService.show();
	}
	
	@PostMapping(path="series/create")
	public Podcast createS(@ModelAttribute Podcast serie, @RequestParam("file") MultipartFile file) throws IOException {
		
	
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, "");
			if (file.isEmpty())
	            throw new IllegalStateException("Vous n'avez charger aucune image");
			
			
			serie.setPoster(pathImg);
		
			//Save du tout
			return podcastservice.create(serie);
		
	}
	
	@GetMapping("series/{id}")
	public Optional<Serie> showbyIdS(@PathVariable Long id){
		
		return serieService.showById(id);
	}
	
	@PutMapping("series/update/{id}")
	public Serie updateS(@PathVariable Long id, @RequestBody Serie u){
		
		return serieService.upadte(id, u);
		
	}
	
	@DeleteMapping(path="series/delete/{id}")
	public Boolean deleteS (@PathVariable Long id) {
		
		serieService.delete(id);
		
		return true;
	}

	
	//Episodes
	
    public List<Episode> showE(){
		
		return episodeService.show();
	}
	
	@GetMapping("episodes/{id}")
	public Optional<Episode> showbyIdE(@PathVariable Long id){
		
		return episodeService.showById(id);
	}
	
	@PostMapping(path="episodes/create")
	public Episode createE(@ModelAttribute Episode e, @RequestParam("file") MultipartFile file, @RequestParam("ep") MultipartFile ep) throws IOException {
		
	
			//Enregistrement du fichier img
			String pathImg = metadataService.uploadR3(file, "");
			if (file.isEmpty())
	            throw new IllegalStateException("Vous n'avez charger aucune image");
			
			String pathmovie = metadataService.uploadR3(file, "");
			
			
			e.setPosterUrl(pathImg);
			
			e.setUrlvideo(pathmovie);
		
			//Save du tout
			return episodeService.create(e);
		
	}
	
	@PutMapping("episodes/update/{id}")
	public Episode updateE(@PathVariable Long id, @RequestBody Episode u){
		
		return episodeService.upadte(id, u);
		
	}
	
	@DeleteMapping(path="episodes/delete/{id}")
	public Boolean deleteE (@PathVariable Long id) {
		
		episodeService.delete(id);
		
		return true;
	}

	
	

}
