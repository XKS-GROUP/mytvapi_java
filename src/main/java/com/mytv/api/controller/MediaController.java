package com.mytv.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	//Pays
	@PostMapping(path="pays/create")

	public Pays createPays(@RequestBody Pays u) {
		
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

	public Genre createG(@RequestBody Genre u) {
		
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
	@PostMapping(path="radios/create")

	public Radio create(@RequestBody Radio u) {
		
		return radioService.create(u);
	}
	
	
	@GetMapping("radios")
	public List<Radio> showR(){
		
		return radioService.show();
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
	
	@PostMapping(path="podcasts/create")
	public Podcast createP(@RequestBody Podcast u) {
		
		return podcastservice.create(u);
	}
	
	
	@GetMapping("podcasts")
	public List<Podcast> showP(){
		
		return podcastservice.show();
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
	
	
	@GetMapping("movies/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){
		
		return filmService.showById(id);
	}
	
	@PutMapping("movies/update/{id}")
	public Film updateM(@PathVariable Long id, @RequestBody Film u){
		
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
