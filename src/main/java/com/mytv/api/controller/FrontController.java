package com.mytv.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.ComPodcast;
import com.mytv.api.model.FavLiveTv;
import com.mytv.api.model.FavPodcast;
import com.mytv.api.model.FavRadio;
import com.mytv.api.model.LikeLivetv;
import com.mytv.api.model.LikePodcast;
import com.mytv.api.model.LikeRadio;
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
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.ComPodcastRepository;
import com.mytv.api.repository.FavLiveRepository;
import com.mytv.api.repository.FavPodcastRepository;
import com.mytv.api.repository.FavRadioRepository;
import com.mytv.api.repository.LikeLivetvRepository;
import com.mytv.api.repository.LikePodcastRepository;
import com.mytv.api.repository.LikeRadioRepository;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.ComPodcastService;
import com.mytv.api.service.FavLiveService;
import com.mytv.api.service.FavPodcastService;
import com.mytv.api.service.FavRadioService;
import com.mytv.api.service.LikeLiveService;
import com.mytv.api.service.LikePodcastService;
import com.mytv.api.service.LikeRadioService;
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
import com.mytv.api.service.gestUser.WUserService;

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
	@Autowired
	private ComPodcastRepository compodRep;

	
	@Autowired
	WUserService userService;
	
	//Pays
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


	//Genre

	@Tag(name = "Genres")
	@GetMapping("genres")
	public List<Genre> showG(){

		return genreService.show();
	}

	@Tag(name = "Genres")
	@GetMapping("genres/{id}")
	public Optional<Genre> showbyIdG(@PathVariable Long id){

		return genreService.showById(id);
	}


	//Categorie LiveTv ou Radio
	@Tag(name = "Categorie Radio et LiveTv")
	@GetMapping("catrl")
	public List<CategoryRL> showCRL(){

		return catLrService.show();
	}


	//Categorie Podcast

	@Tag(name = "Categorie Podcast")
	@GetMapping("catpod")
	public List<CatPodcast> showCP(){

		return catpodService.show();
	}

	
	/*
	 * GESTION DES RADIOS
	 */
	//Radio
	@Tag(name = "Radios")
	@GetMapping("radios")
	public List<Radio> showR(){

		return radioService.show();
	}

	@Tag(name = "Radios")
	@GetMapping("radios/{id}")
	public Optional<Radio> showbyIdR(@PathVariable Long id){

		return radioService.showById(id);
	}
	@Tag(name = "Radios")
	@GetMapping("radiosbynamecontain/{name}")
	public List<Radio> showbyNameContain(@PathVariable String nom){

		return radioService.showByNameContaining(nom);
	}
	
	//LIKE
	
	//AFFICHE LIKE PAR RADIO
	@Tag(name = "Radios")
	@GetMapping("radioShowlikebyRadio/{idRadio}")
	public List<LikeRadio> radioLikebyRadio(@PathVariable Long idRadio){
		
		Radio r = radioService.showById(idRadio).get();
		
		return likeradioService.findByUser(r);
	}
	
	//AFFICHE NOMBRE LIKE PAR RADIO
	@Tag(name = "Radios")
	@GetMapping("radioShowNblikebyRadio/{idRadio}")
	public Long radioNbLike(@PathVariable Long idRadio){
		
		return likeradioService.nbretotalLike(radioService.showById(idRadio).get());
	}
	
	//ADD LIKE
	@Tag(name = "Radios")
	@PostMapping("radioAddlike/{idRadio}")
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
	@DeleteMapping("radioDellike/{idLike}")
	public void radioDelLike(@PathVariable Long id){
		
		 likeradioService.removeLike(id);
		 
	}
	
	/*
	 * GESTION DES FAVORIES
	 */
	//FAVORIES
	@Tag(name = "Radios")
	@PostMapping("radioAddFavorie/{idRadio}")
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
	@GetMapping("radioShowFavorie")
	public ResponseEntity<Object> radioAddFavorie(){

		return EntityResponse.generateResponse("Liste des radios favorie", HttpStatus.OK, 
				favradioService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Radios")
	@DeleteMapping("radioDelFavorie/{idFavorie}")
	public boolean radioDelFavorie(@PathVariable Long idFav){

		return favradioService.remove(idFav);
		
	}
	
	
	/*
	 * GESTION DES LIVES TV 
	 */
	//ROUTES LiveTV
	@Tag(name = "Lives")
	@GetMapping("lives")
	public List<LiveTv> showL(){

		return liveService.show();
	}

	@Tag(name = "Lives")
	@GetMapping("lives/{id}")
	public Optional<LiveTv> showbyIdL(@PathVariable Long id){

		return liveService.showById(id);
	}
    
	@Tag(name = "Lives")
	@GetMapping("livesbynamecontain/{nom}")
	public List<LiveTv> showLbyNameContainL(@PathVariable String nom){

		return liveService.showByNameContaining(nom);
	}
			
			//LIKE
			
			//AFFICHE LIKE PAR RADIO
			@Tag(name = "Lives")
			@GetMapping("liveShowlikebyLive/{idLive}")
			public List<LikeLivetv> liveLikebyLive(@PathVariable Long idLive){
				
				LiveTv l = liveService.showById(idLive).get();
				
				return likeliveService.findByLivetv(l);
			}
			
			//AFFICHE NOMBRE LIKE PAR RADIO
			@Tag(name = "Lives")
			@GetMapping("liveShowNblikebyRadio/{idLive}")
			public Long liveNbLike(@PathVariable Long idLive){
				
				return likeliveService.nbretotalLike(liveService.showById(idLive).get());
			}
			
			//ADD LIKE
			@Tag(name = "Lives")
			@PostMapping("liveAddlike/{idLive}")
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
			@DeleteMapping("liveDellike/{idLike}")
			public void liveDelLike(@PathVariable Long id){
				
				 likeliveService.removeLike(id);
				 
			}
			
			/*
			 * GESTION DES FAVORIES
			 */
			//FAVORIES
			
			@Tag(name = "Lives")
			@PostMapping("liveAddFavorie/{idLive}")
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
			@GetMapping("liveShowFavorie")
			public ResponseEntity<Object> liveAddFavorie(){
	    
				return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
						favliveService.findByUser(userService.findCurrentUser()));
			}
			
			@Tag(name = "Lives")
			@DeleteMapping("liveDelFavorie/{idFavorie}")
			public boolean liveDelFavorie(@PathVariable Long idFav){

				return favradioService.remove(idFav);
				
			}
		

	
	
	/*
	 * GESTION DES PODCASTS
	 */
	//Podcast
	@Tag(name = "Podcasts")
	@GetMapping("podcasts")
	public List<Podcast> showP(){

		return podcastservice.show();
	}

	@Tag(name = "Podcasts")
	@GetMapping("podcasts/{id}")
	public Optional<Podcast> showbyIdP(@PathVariable Long id){

		return podcastservice.showById(id);
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcastsbynamecontain/{name}")
	public List<Podcast> showbyIdP(@PathVariable String name){

		return podcastservice.showByNameContaining(name);
	}
	//COM
	@Tag(name = "Podcasts")
	@GetMapping("podcasthowAllcomment/{idPod}")
	public List<ComPodcast> podcastShowAllComment(@PathVariable Long idPod){

		return compodService.show() ;
		
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasthowcommentById/{idPod}")
	public List<ComPodcast> podcastShowCommentById(@PathVariable Long idPod){
		
		return compodService.findByPodcast(podcastservice.showById(idPod).get()) ;
		
	}
	
	//ADD COMMENT
	@Tag(name = "Podcasts")
	@PostMapping("podcastAddcomment")
	public List<ComPodcast> LiveAddComment(@Valid @RequestBody ComPodcast comPodcast){
		
		
		
		//Podcast l = podcastservice.
		//User u = userService.findCurrentUser();
		
		return null;//compodService.addCom(com);
		
	}
	
	@Tag(name = "Podcasts")
	@DeleteMapping("podcastDelcomment/{idCom}")
	public List<Radio> LiveDelComment(@PathVariable Long idCom){

		return null; //Service.AFTER_DESTROY_EVENT;
		
	}
	
	//LIKE
	
	//AFFICHE LIKE PAR PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcastShowlikebyLive/{idPod}")
	public List<LikePodcast> podcastLikebyLive(@PathVariable Long idPod){
		
		Podcast l = podcastservice.showById(idPod).get();
		
		return likepodService.findByPodcast(l);
	}
	
	//AFFICHE NOMBRE LIKE PAR PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcastShowNblikebyRadio/{idLive}")
	public Long podcastNbLike(@PathVariable Long idPod){
		
		return likepodService.nbretotalLike(podcastservice.showById(idPod).get());
	}
	
	//ADD LIKE
	@Tag(name = "Podcasts")
	@PostMapping("podcastAddlike/{idPod}")
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
	@DeleteMapping("podcastDellike/{idLike}")
	public void podcastDelLike(@PathVariable Long id){
		
		likepodService.removeLike(id);
		 
	}
	
	/*
	 * GESTION DES FAVORIES
	 */
	//FAVORIES
	
	@Tag(name = "Podcasts")
	@PostMapping("podcastAddFavorie/{idPod}")
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
	@GetMapping("podcastShowFavorie")
	public ResponseEntity<Object> podcastAddFavorie(){

		return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
				favpodService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Podcasts")
	@DeleteMapping("podcastDelFavorie/{idFavorie}")
	public boolean podcastDelFavorie(@PathVariable Long idFav){

		return favpodService.remove(idFav);
		
	}


	
	
	
	
	
	
	/*
	 * GESTION DES FILMS
	 */
	//Films
	@Tag(name = "Films")
	@GetMapping("movies")
	public List<Film> showM(){

		return filmService.show();
	}

	@Tag(name = "Films")
	@GetMapping("movies/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){

		return filmService.showById(id);
	}
    
	@Tag(name = "Films")
	@GetMapping("moviesbynamecontain/{id}")
	public List<Film> showbyIdM(@PathVariable String name){

		return filmService.showByNameContaining(name);
	}
	
	/*
	 * GESTION DES SERIES
	 */
	//Series
	@Tag(name = "Series")
	@GetMapping("series")
	public List<Serie> showS(){

		return serieService.show();
	}

	@Tag(name = "Series")
	@GetMapping("series/{id}")
	public Optional<Serie> showbyIdS(@PathVariable Long id){

		return serieService.showById(id);
	}
	@Tag(name = "Series")
	@GetMapping("seriesbynamecontain/{name}")
	public List<Serie> showbyIdS(@PathVariable String name){

		return serieService.showbyNameContaining(name);
	}
	
	
	/*
	 * GESTION DES EPISODES
	 */
	//Episodes
	@Tag(name = "Episodes")
	@GetMapping("episodes")
    public List<Episode> showE(){

		return episodeService.show();
	}

	@Tag(name = "Episodes")
	@GetMapping("episodes/{id}")
	public Optional<Episode> showbyIdE(@PathVariable Long id){

		return episodeService.showById(id);
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodesbynamecontain/{name}")
	public List<Episode> showbyIdE(@PathVariable String name){

		return episodeService.showByNameContain(name);
	}
}
