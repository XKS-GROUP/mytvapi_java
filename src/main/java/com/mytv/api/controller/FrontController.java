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
import com.mytv.api.model.gestMedia.CategoryRL;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;
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
import com.mytv.api.service.gestMedia.CategoryLrService;
import com.mytv.api.service.gestMedia.EpisodeService;
import com.mytv.api.service.gestMedia.GenreService;
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
	@GetMapping("radios/search/contain/{name}")
	public List<Radio> showbyNameContain(@PathVariable String nom){

		return radioService.showByNameContaining(nom);
	}
	
	//LIKE
	
	//AFFICHE LIKE PAR RADIO
	@Tag(name = "Radios")
	@GetMapping("radios/likes/show/likebyRadio/{idRadio}")
	public List<LikeRadio> radioLikebyRadio(@PathVariable Long idRadio){
		
		Radio r = radioService.showById(idRadio).get();
		
		return likeradioService.findByUser(r);
	}
	
	//AFFICHE NOMBRE LIKE PAR RADIO
	@Tag(name = "Radios")
	@GetMapping("radios/like/show/nblikebyRadio/{idRadio}")
	public Long radioNbLike(@PathVariable Long idRadio){
		
		return likeradioService.nbretotalLike(radioService.showById(idRadio).get());
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
	public void radioDelLike(@PathVariable Long id){
		
		 likeradioService.removeLike(id);
		 
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
	public ResponseEntity<Object> radioAddFavorie(){

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
	public List<LiveTv> showL(){

		return liveService.show();
	}

	@Tag(name = "Lives")
	@GetMapping("lives/{id}")
	public Optional<LiveTv> showbyIdL(@PathVariable Long id){

		return liveService.showById(id);
	}
    
	@Tag(name = "Lives")
	@GetMapping("lives/search/contain/{nom}")
	public List<LiveTv> showLbyNameContainL(@PathVariable String nom){

		return liveService.showByNameContaining(nom);
	}
			
			//LIKE
			
			//AFFICHE LIKE PAR RADIO
			@Tag(name = "Lives")
			@GetMapping("lives/likes/show/byLive/{idLive}")
			public List<LikeLivetv> liveLikebyLive(@PathVariable Long idLive){
				
				LiveTv l = liveService.showById(idLive).get();
				
				return likeliveService.findByLivetv(l);
			}
			
			//AFFICHE NOMBRE LIKE PAR RADIO
			@Tag(name = "Lives")
			@GetMapping("lives/all/nblikebyRadio/{idLive}")
			public Long liveNbLike(@PathVariable Long idLive){
				
				return likeliveService.nbretotalLike(liveService.showById(idLive).get());
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
			public void liveDelLike(@PathVariable Long id){
				
				 likeliveService.removeLike(id);
				 
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
	public List<Podcast> showP(){

		return podcastservice.show();
	}

	@Tag(name = "Podcasts")
	@GetMapping("podcasts/{id}")
	public Optional<Podcast> showbyIdP(@PathVariable Long id){

		return podcastservice.showById(id);
	}
	
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/search/contain/{name}")
	public List<Podcast> showbyIdP(@PathVariable String name){

		return podcastservice.showByNameContaining(name);
	}
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/comments/all")
	public List<ComPodcast> podcastShowAllComment(){

		return compodService.show() ;
		
	}
	
	//AFFICHE COMMENTAIRE D UN PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/comments/byId/{idPod}")
	public List<ComPodcast> podcastShowCommentById(@PathVariable Long idPod){
		
		return compodService.findByPodcast(podcastservice.showById(idPod).get()) ;
		
	}
	
	//ADD COMMENT
	@Tag(name = "Podcasts")
	@PostMapping("podcasts/comments/add/{idPod}")
	public ComPodcast LiveAddComment(@PathVariable Long idPod, @RequestBody String com){
		
		Podcast l = podcastservice.showById(idPod).get();
		User u = userService.findCurrentUser();
		ComPodcast comp = new ComPodcast();
		comp.setUser(u);
		comp.setPodcast(l);
		comp.setContenu(com);
		return compodService.addCom(comp);
		
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
	@GetMapping("podcasts/likes/byLive/{idPod}")
	public List<LikePodcast> podcastLikebyLive(@PathVariable Long idPod){
		
		Podcast l = podcastservice.showById(idPod).get();
		
		return likepodService.findByPodcast(l);
	}
	
	//AFFICHE NOMBRE LIKE PAR PODCAST
	@Tag(name = "Podcasts")
	@GetMapping("podcasts/likes/nblikebyRadio/{idLive}")
	public Long podcastNbLike(@PathVariable Long idPod){
		
		return likepodService.nbretotalLike(podcastservice.showById(idPod).get());
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
	public void podcastDelLike(@PathVariable Long id){
		
		likepodService.removeLike(id);
		 
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
	public ResponseEntity<Object> podcastAddFavorie(){

		return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
				favpodService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Podcasts")
	@DeleteMapping("podcasts/favories/all/{idFavorie}")
	public boolean podcastDelFavorie(@PathVariable Long idFav){

		return favpodService.remove(idFav);
		
	}


	
	
	
	
	
	
	/*
	 * GESTION DES FILMS
	 */
	//Films
	@Tag(name = "Films")
	@GetMapping("films")
	public List<Film> showM(){

		return filmService.show();
	}

	@Tag(name = "Films")
	@GetMapping("films/{id}")
	public Optional<Film> showbyIdM(@PathVariable Long id){

		return filmService.showById(id);
	}
    
	@Tag(name = "Films")
	@GetMapping("films/search/contain/{id}")
	public List<Film> showbyIdM(@PathVariable String name){

		return filmService.showByNameContaining(name);
	}
	
////////////////////////////////////////////////////////////////////////
/*
 * DEBUT DU MODUL
 */
	//COM
		//AFFICHE TOUS LES COMMENTAIRES
		@Tag(name = "Films")
		@GetMapping("films/comments/all")
		public List<ComFilm> filmShowAllComment(){

			return comfilmService.show() ;
			
		}
		
		//AFFICHE COMMENTAIRE D UN FILM
		@Tag(name = "Films")
		@GetMapping("films/comments/show/byIdFilm/{idFilm}")
		public List<ComFilm> filmShowCommentById(@PathVariable Long idFilm){
			
			return comfilmService.findByFilm(filmService.showById(idFilm).get()) ;
			
		}
		
		//ADD COMMENT
		@Tag(name = "Films")
		@PostMapping("films/comments/add/{idFilm}")
		public ComFilm filmAddComment(@PathVariable Long idFilm, @RequestBody String com){
			
			Film f = filmService.showById(idFilm).get();
			User u = userService.findCurrentUser();
			ComFilm comp = new ComFilm();
			comp.setUser(u);
			comp.setFilm(f);
			comp.setContenu(com);
			return comfilmService.addCom(comp);
			
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
		public List<LikeFilm> filmLikebyLive(@PathVariable Long idfilm){
			
			Film l = filmService.showById(idfilm).get();
			
			return likefilmService.findByFilm(l);
			
		}
		
		//AFFICHE NOMBRE LIKE PAR PODCAST
		@Tag(name = "Films")
		@GetMapping("films/likes/nblikebyFilm/{idfilm}")
		public Long filmNbLike(@PathVariable Long idfilm){
			
			return likepodService.nbretotalLike(podcastservice.showById(idfilm).get());
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
		public void filmDelLike(@PathVariable Long id){
			
			likefilmService.removeLike(id);
			 
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
		@GetMapping("films/favorie/all")
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
	public List<Serie> showS(){

		return serieService.show();
	}

	@Tag(name = "Series")
	@GetMapping("series/{id}")
	public Optional<Serie> showbyIdS(@PathVariable Long id){

		return serieService.showById(id);
	}
	@Tag(name = "Series")
	@GetMapping("series/search/contain/{name}")
	public List<Serie> showbyIdS(@PathVariable String name){

		return serieService.showbyNameContaining(name);
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Series")
	@GetMapping("serie/comments/all")
	public List<ComSerie> serieShowAllComment(){
	
	return comserieService.show();
	
	}
	
	//AFFICHE COMMENTAIRE D UNE SERIE
	@Tag(name = "Series")
	@GetMapping("series/comments/show/byId/{idSerie}")
	public List<ComSerie> serieShowCommentById(@PathVariable Long idSerie){
	
	return comserieService.findBySerie(serieService.showById(idSerie).get()) ;
	
	}
	
	//ADD COMMENT
	@Tag(name = "Series")
	@PostMapping("series/comment/all/{idSerie}")
	public ComSerie serieAddComment(@PathVariable Long idSerie, @RequestBody String com){
	
	Serie f = serieService.showById(idSerie).get();
	User u = userService.findCurrentUser();
	ComSerie comp = new ComSerie();
	comp.setUser(u);
	comp.setSerie(f);
	comp.setContenu(com);
	return comserieService.addCom(comp);
	
	}
	
	@Tag(name = "Series")
	@DeleteMapping("series/comments/delete/{idCom}")
	public ResponseEntity<Object> serieDelComment(@PathVariable Long idCom){
	
	return EntityResponse.generateResponse("Comentaire Supprimé avec succès", HttpStatus.OK, 
	comserieService.remove(idCom));
	
	}
	
	//LIKE
	
	//AFFICHE LIKE PAR FILM
	@Tag(name = "Series")
	@GetMapping("series/likes/show/likebyLive/{idSerie}")
	public List<LikeSerie> serieLikebyLive(@PathVariable Long idSerie){
	
	Serie l = serieService.showById(idSerie).get();
	
	return likeserieService.findBySerie(l);
	
	}
	
	//AFFICHE NOMBRE LIKE PAR PODCAST
	@Tag(name = "Series")
	@GetMapping("series/likes/shows/nblikebyRadio/{idSerie}")
	public Long serieNbLike(@PathVariable Long idSerie){
	
	return likeserieService.nbretotalLike(serieService.showById(idSerie).get());
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
	public void serieDelLike(@PathVariable Long idLike){
	
	likeserieService.removeLike(idLike);
	
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
	public ResponseEntity<Object> serieAddFavorie(){
	
	return EntityResponse.generateResponse("Liste des livetv favorie", HttpStatus.OK, 
	favserieService.findByUser(userService.findCurrentUser()));
	}
	
	@Tag(name = "Series")
	@DeleteMapping("series/favories/delete/{idFavorie}")
	public boolean serieDelFavorie(@PathVariable Long idFav){
	
	return favserieService.remove(idFav);
	
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
    public List<Episode> showE(){

		return episodeService.show();
	}

	@Tag(name = "Episodes")
	@GetMapping("episodes/{id}")
	public Optional<Episode> showbyIdE(@PathVariable Long id){

		return episodeService.showById(id);
	}
	
	@Tag(name = "Episodes")
	@GetMapping("episodes/search/contain/{name}")
	public List<Episode> showbyIdE(@PathVariable String name){

		return episodeService.showByNameContain(name);
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Episodes")
	@GetMapping("episodes/comments/All")
	public List<ComEpisode> episodeShowAllComment(){
	
	return comepisodeService.show();
	
	}
	
	//AFFICHE COMMENTAIRE D UN EPISODE
	@Tag(name = "Episodes")
	@GetMapping("episodes/comments/show/byId/{idEpisode}")
	public List<ComEpisode> episodeShowCommentById(@PathVariable Long idEpisode){
	
	return comepisodeService.findByEpisode(episodeService.showById(idEpisode).get()) ;
	
	}
	
	//ADD COMMENT
	@Tag(name = "Episodes")
	@PostMapping("episodes/comments/add/{idEpisode}")
	public ComEpisode episodeAddComment(@PathVariable Long idEpisode, @RequestBody String com){
	
	Episode f = episodeService.showById(idEpisode).get();
	User u = userService.findCurrentUser();
	ComEpisode comp = new ComEpisode();
	
	comp.setUser(u);
	comp.setEpisode(f);
	comp.setContenu(com);
	return comepisodeService.addCom(comp);
	
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
	@GetMapping("episodes/likes/howlikebyLive/{idEpisode}")
	public List<LikeEpisode> episodeLikebyLive(@PathVariable Long idEpisode){
	
	Episode l = episodeService.showById(idEpisode).get();
	
	return likeepisodeService.findByEpisode(l);
	
	}
	
	//AFFICHE NOMBRE LIKE PAR EPISODE
	@Tag(name = "Episodes")
	@GetMapping("episodes/likes/show/nblikebyEpisode/{idEpisode}")
	public Long episodeNbLike(@PathVariable Long idEpisode){
	
	return likeepisodeService.nbretotalLike(episodeService.showById(idEpisode).get());
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
	public void episodeDelLike(@PathVariable Long idEpisode){
	
	likeepisodeService.removeLike(idEpisode);
	
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
	public boolean episodeDelFavorie(@PathVariable Long idFav){
	
	return favepisodeService.remove(idFav);
	
	}
	
	
	/*
	 * GESTION DES SAISONS
	 */
	
	//SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/all")
    public List<Saison> showSaison(){

		return saisonService.show();
	}

	@Tag(name = "Saison")
	@GetMapping("saisons/show/{id}")
	public Saison showbyId(@PathVariable Long id){

		return saisonService.showById(id);
	}
	
	@Tag(name = "Saison")
	@GetMapping("saisons/search/contain/{name}")
	public List<Saison> showbyIdSaison(@PathVariable String name){

		return saisonService.showByNameContaining(name);
	}
	
	////////////////////////////////////////////////////////////////////////
	/*
	* DEBUT DU MODUL
	*/
	//COM
	//AFFICHE TOUS LES COMMENTAIRES
	@Tag(name = "Saison")
	@GetMapping("saisons/comments/all")
	public List<ComSaison> saisonShowAllComment(){
	
	return comsaisonService.show();
	
	}
	
	//AFFICHE COMMENTAIRE D UNE SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/comments/show/byId/{idSaison}")
	public List<ComSaison> saisonShowCommentById(@PathVariable Long idSaison){
	
	return comsaisonService.findBySaison(saisonService.showById(idSaison));
	
	}
	
	//ADD COMMENT
	@Tag(name = "Saison")
	@PostMapping("saisons/comments/add/{idSaison}")
	public ComSaison saisonAddComment(@PathVariable Long idSaison, @RequestBody String com){
	
	Saison s = saisonService.showById(idSaison);
	User u = userService.findCurrentUser();
	ComSaison comp = new ComSaison();
	
	comp.setUser(u);
	comp.setSaison(s);
	comp.setContenu(com);
	return comsaisonService.addCom(comp);
	
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
	public List<LikeSaison> saisonLikebySaison(@PathVariable Long idSaison){
	
	Saison s = saisonService.showById(idSaison);
	
	return likesaisonService.findBySaison(s);
	
	}
	
	//AFFICHE NOMBRE LIKE PAR SAISON
	@Tag(name = "Saison")
	@GetMapping("saisons/likes/nblikebyEpisode/{idSaison}")
	public Long saisonNbLike(@PathVariable Long idSaison){
	
	return likesaisonService.nbretotalLike(saisonService.showById(idSaison));
	
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
	public void saisonDelLike(@PathVariable Long idSaison){
	
	likeepisodeService.removeLike(idSaison);
	
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
	public boolean saisonDelFavorie(@PathVariable Long idFav){
	
	return favsaisonService.remove(idFav);
	
	}
}
