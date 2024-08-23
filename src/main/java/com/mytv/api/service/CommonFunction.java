package com.mytv.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazonaws.services.accessanalyzer.model.ResourceNotFoundException;
import com.mytv.api.dto.StatusDTO;
import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.service.EpisodeService;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.service.ServiceFilm;
import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.model.Director;
import com.mytv.api.intervenant.model.Podcasteur;
import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.intervenant.repository.DirectorRepository;
import com.mytv.api.intervenant.service.PodcasterService;
import com.mytv.api.live.model.Live;
import com.mytv.api.live.service.LiveService;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.service.LiveTvSetvice;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.model.CategArticle;
import com.mytv.api.news.service.ArticleService;
import com.mytv.api.news.service.CategArticleService;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.service.PodcastService;
import com.mytv.api.podcastCollecton.model.ColPodcast;
import com.mytv.api.podcastCollecton.repository.CollectionPodcastRepository;
import com.mytv.api.podcastcateg.model.CatPodcast;
import com.mytv.api.podcastcateg.repository.CatPodcastRepository;
import com.mytv.api.podcastcateg.service.CatPodcastService;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.service.RadioService;
import com.mytv.api.ressource.model.CategorieLive;
import com.mytv.api.ressource.model.CategoryRL;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.model.Pays;
import com.mytv.api.ressource.repository.CategorieLiveRepository;
import com.mytv.api.ressource.repository.CategoryLrRepository;
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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommonFunction {

	@Autowired
	ArticleService artService;
	
	@Autowired
	CategArticleService catArtService;
	
	@Autowired
	private RadioService radioService;

	// Pour le lives tv
	@Autowired
	private CategorieLiveService categliveService;
	
	@Autowired
	private PodcasterService podcasterService;

	// Pour le lives tv
	@Autowired
	private LiveTvSetvice liveService;

	// Pour les lives evenement
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
	 * Collection Podcast
	 * 
	 */

	// CRUD Podcast Collections
	public ResponseEntity<Object> showCollection() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findAll());
	}

	public ResponseEntity<Object> showCollPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findAll(p));
	}

	public ResponseEntity<Object> searchCollection(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				
				
				colPodRep.findByNameContainingOrOverviewContaining(s, s, p));
	}

	public ResponseEntity<Object> showCollectionById(long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.findById(id));
	}

	public ResponseEntity<Object> createCollection(ColPodcast r) {

		String nom = r.getName().toString() ;

		if (colPodRep.findByName(nom) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Cette collection existe déja"));
			
		} else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, colPodRep.save(r));
		}
	}

	public ResponseEntity<Object> updateCollection(Long id, ColPodcast a) {

		a.setIdColPd(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.save(a));

	}

	public ResponseEntity<Object> updateSatusCollection(Long id, StatusDTO status) {

		ColPodcast cl = colPodRep.findById(id).get();
		cl.setIdColPd(id);
		cl.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, colPodRep.save(cl));

	}

	public ResponseEntity<Object> deleteCollection(Long id) {
		colPodRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");

	}

	/*
	 * 
	 * CRUD acteurs
	 * 
	 */
	//

	public ResponseEntity<Object> showActor() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.findAll());
	}

	public ResponseEntity<Object> showActorPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.findAll(p));
	}

	public ResponseEntity<Object> showActorById(long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.findById(id));
	}

	public ResponseEntity<Object> createActor(Actor a) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, actorRep.save(a));
	}

	public ResponseEntity<Object> updateActor(Long id, Actor a) {

		a.setIdActor(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, actorRep.save(a));

	}

	public ResponseEntity<Object> deleteActor(Long id) {
		actorRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, " ");

	}

	//

	// CRUD Directors

	public ResponseEntity<Object> showDir() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.findAll());
	}

	public ResponseEntity<Object> showDirPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.findAll(p));
	}

	public ResponseEntity<Object> showDirById(long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.findById(id));
	}

	public ResponseEntity<Object> createDir(Director a) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, directorsRep.save(a));
	}

	public ResponseEntity<Object> updateDirecteur(Long id, Director a) {

		a.setIdDirector(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, directorsRep.save(a));

	}

	public ResponseEntity<Object> deleteDir(Long id) {
		directorsRep.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");

	}
	
	/*
	 * 
	 * Poscasteur
	 * 
	 * 
	 */


	public ResponseEntity<Object> podShow(){

		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcasterService.show());
	}
    
	public ResponseEntity<Object> podPaging(Pageable p){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcasterService.showPage(p));
	}
    

	public ResponseEntity<Object> podShowById( long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcasterService.showById(id));
	}
    

	public ResponseEntity<Object> podCreate(  Podcasteur p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, podcasterService.create(p));
	}
    

	public ResponseEntity<Object> podUpdate( Long id,   Podcasteur p){
    	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcasterService.update(id, p));
		
	}
    

	public ResponseEntity<Object> podDelete( Long id){
    	
    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcasterService.delete(id));
		
	}
    
	
	
	
	/*
	 * 
	 * Langue
	 * 
	 */

	public ResponseEntity<Object> showLang() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.show());
	}

	public ResponseEntity<Object> showLangPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,

				langService.showPage(p));
	}

	public ResponseEntity<Object> showLangById(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.showById(id));
	}

	public ResponseEntity<Object> updateLang(Long id, Language r) {
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.upadte(id, r));

	}

	public ResponseEntity<Object> updateStatusLang(Long id, StatusDTO status) {

		Language l = langService.showById(id).get();
		l.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.upadte(id, l));

	}

	public ResponseEntity<Object> createLang(Language u) {

		
		if (langService.showByName(u.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST, Map.of("name","Cette langue existe déja"));
			
		}
		else if (langService.showBySlug(u.getSlug()) != null) {

			return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST, Map.of("slug","Cette valeur existe déja"));
			
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, langService.create(u));
		}
	}

	public ResponseEntity<Object> delete(Long id) {

		langService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	public ResponseEntity<Object> showLangByName(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, langService.showByName(s, p));
	}

	/*
	 * 
	 * CRUD PAYS
	 * 
	 * 
	 */

	public ResponseEntity<Object> createPays(Pays u) {
		
		if (paysService.findByname(u.getName()) != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.BAD_REQUEST, Map.of("name","Ce nom de pays existe déja "));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, paysService.create(u));

		}
	}

	public ResponseEntity<Object> showPays() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.show());
	}

	public ResponseEntity<Object> showPaysPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.showPage(p));
	}

	public ResponseEntity<Object> showbyIdPays(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.showById(id));
	}

	public ResponseEntity<Object> updatePays(Long id, Pays u) {

	
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.update(id, u));

	}

	public ResponseEntity<Object> updateStatusPays(Long id, StatusDTO status) {

		Pays p = paysService.showById(id).get();

		p.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, paysService.update(id, p));

	}

	public ResponseEntity<Object> deletePays(Long id) {

		paysService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}
	
	public ResponseEntity<Object> findPays(String name, Pageable p) {

		

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, paysService.findBynamecontain(name, p));
	}

	/*
	 * 
	 * GENRE DE FILMs ET SERIES
	 * 
	 */

	public ResponseEntity<Object> create(Genre g) {

		if (!genreService.findByNameContain(g.getName()).isEmpty()) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name","Ce genre existe déja"));
		}

		else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, genreService.create(g));

		}
	}

	public ResponseEntity<Object> showG() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.show());
	}

	public ResponseEntity<Object> showPage(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showByPages(p));
	}

	public ResponseEntity<Object> showByName(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showByName(s, p));
	}

	public ResponseEntity<Object> showByNameContain(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.findByNameContain(s, p));

	}

	public ResponseEntity<Object> showbyIdG(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.showById(id)
				.orElseThrow(() -> new ResourceNotFoundException("aucune donne avec id= " + id)));
	}

	public ResponseEntity<Object> updateG(Long id, Genre g) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.upadte(id, g));

	}

	public ResponseEntity<Object> updateG(Long id, StatusDTO status) {

		Genre g = genreService.showById(id).get();

		g.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, genreService.upadte(id, g));

	}

	public ResponseEntity<Object> deleteG(Long id) {

		genreService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	/*
	 * 
	 * Categorie LiveTv ou Radio
	 * 
	 * 
	 */

	public ResponseEntity<Object> createCRL(CategoryRL u) {

		if (catlrRep.findByName(u.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name","Cette categorie existe déja"));
		} else {

			return EntityResponse.generateResponse("Succes", HttpStatus.CREATED, catLrService.create(u));

		}
	}

	public ResponseEntity<Object> showCRL() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.show());
	}

	public ResponseEntity<Object> showCRLPaging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.showPaging(p));
	}

	public ResponseEntity<Object> showbyIdCRL(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.showById(id));
	}

	public ResponseEntity<Object> updateCRL(Long id, CategoryRL u) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.upadte(id, u));

	}

	public ResponseEntity<Object> updateStatusCRL(Long id, StatusDTO status) {

		CategoryRL rl = catLrService.showById(id).get();

		rl.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catLrService.upadte(id, rl));

	}

	public ResponseEntity<Object> deleteCRL(Long id) {

		catLrService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	/*
	 * 
	 * Categorie Podcast
	 * 
	 * 
	 */

	public ResponseEntity<Object> createCP(CatPodcast u) {

		
		if (catPodRep.findByName(u.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					Map.of("name", "Cette categorie de podcast existe déja"));
		} else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, catpodService.create(u));

		}
	}

	public ResponseEntity<Object> showCP() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.show());
	}

	public ResponseEntity<Object> showCP(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.showPaging(p));
	}

	public ResponseEntity<Object> showbyIdCP(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.showById(id));
	}

	public ResponseEntity<Object> updateCP(Long id, CatPodcast u) {
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.upadte(id, u));

	}

	public ResponseEntity<Object> updateSatusCP(Long id, StatusDTO status) {

		CatPodcast cp = catpodService.showById(id).get();
		cp.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catpodService.upadte(id, cp));

	}

	public ResponseEntity<Object> deleteCP(Long id) {

		catpodService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	/*
	 * 
	 * 
	 * RADIO
	 * 
	 * 
	 */

	public ResponseEntity<Object> showR() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.show());
	}

	public ResponseEntity<Object> showRadioPage(Pageable p, Long categ, Long langue) {

		if (categ != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showByCateg(categ, p));
		} else if (langue != null && categ == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showByLangue(langue, p));
		} else if (langue != null && categ != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					radioService.showByCategAbdLang(categ, langue, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showPage(p));
		}
	}

	public ResponseEntity<Object> createR(Radio r) {

		if (radioService.findByName(r.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Cette radio existe déja"));
		} else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, radioService.create(r));
		}
	}

	public ResponseEntity<Object> showbyIdR(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.showById(id));
	}

	public ResponseEntity<Object> showbyNameContain(String s, Pageable p, Long categ, Long langue) {

		if (categ != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.searchByCateg(s, categ, p));
		} else if (langue != null && categ == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.searchByLangue(s, langue, p));
		} else if (langue != null && categ != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					radioService.searchByCategAbdLang(s, categ, langue, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.search(s, p));

		}
	}

	public ResponseEntity<Object> updateR(Long id, Radio r) {

		// Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, radioService.upadte(id, r));

	}

	public ResponseEntity<Object> updateStatusR(Long id, StatusDTO status) {

		Radio r = radioService.showById(id).get();
		r.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, radioService.upadte(id, r));

	}

	public ResponseEntity<Object> deleteR(Long id) {

		radioService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	/*
	 * 
	 * ROUTES LiveTV
	 * 
	 * 
	 */

	public ResponseEntity<Object> createL(LiveTv lt) {

		if (liveService.findByName(lt.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Cette chaine tv existe déja"));
		} else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, liveService.create(lt));
		}
	}

	public ResponseEntity<Object> showL() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.show());
	}
	
	public ResponseEntity<Object> livetv_find_by_name(String name) {

		if(liveService.findByName(name) == null) {
			return EntityResponse.generateResponse("ERREUR ", HttpStatus.BAD_REQUEST, Map.of("message", "Cette chaine n existe pas"));
		}else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.findByName(name));
			
		}
	}


	public ResponseEntity<Object> showLivePages(Pageable p, Long genre, Long langue, Long pays) {

		if (genre != null && langue == null && pays == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByGenre(genre, p));
		} else if (langue != null && genre == null && pays == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByLangue(langue, p));
		} else if (pays == null && genre == null && pays == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showByPays(pays, p));
		} else if (pays != null && langue != null && genre != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					liveService.showByPaysGenreLangue(genre, langue, pays, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showPage(p));
		}
	}

	public ResponseEntity<Object> showLbyNameContainL(String s, Pageable p, Long genre, Long langue, Long pays) {

		if (genre != null && langue == null && pays == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.searchByGenre(s, genre, p));
		} else if (langue != null && genre == null && pays == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.searchbyLangue(s, langue, p));
		} else if (pays != null && langue == null && genre == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.searchByPays(s, pays, p));
		} else if (pays != null && langue != null && genre != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					liveService.searchByPaysGenreLangue(s, genre, langue, pays, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.search(s, p));
		}
	}

	public ResponseEntity<Object> showbyIdL(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.showById(id));
	}

	public ResponseEntity<Object> updateL(Long id, LiveTv lt) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.update(id, lt));

	}

	public ResponseEntity<Object> updateStatusL(Long id, StatusDTO status) {

		LiveTv lt = liveService.showById(id).get();
		lt.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, liveService.update(id, lt));

	}

	public ResponseEntity<Object> deleteL(Long id) {

		liveService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	/*
	 * 
	 * CATEGORIE DES LIVES
	 * 
	 */

	public ResponseEntity<Object> createCP(CategorieLive u) {

		if (catLiveRep.findByName(u.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					Map.of("name", "Cette categorie de lıve existe déja"));
		} else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, categliveService.create(u));

		}
	}

	public ResponseEntity<Object> showCL() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.show());
	}

	public ResponseEntity<Object> showCL(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.showPaging(p));
	}

	public ResponseEntity<Object> showbyIdCL(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.showById(id));
	}

	public ResponseEntity<Object> updateCL(

			Long id, CategorieLive u) {
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.upadte(id, u));

	}

	public ResponseEntity<Object> updatStatuseCL(

			Long id, StatusDTO status) {

		CategorieLive cl = categliveService.showById(id).get();
		cl.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, categliveService.upadte(id, cl));

	}

	public ResponseEntity<Object> deleteCL(Long id) {

		catpodService.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	/*
	 * 
	 * LES LIVES
	 * 
	 * 
	 */

	public ResponseEntity<Object> showLives() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.show());
	}

	public ResponseEntity<Object> showLivesByPage(Pageable p, Long categ) {

		if (categ != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.showByCategorie(categ, p));
		} else {
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.showPage(p));
		}
	}

	public ResponseEntity<Object> createLives(Live p) {

		if (lService.findByName(p.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name",  "Ce live existe déja"));
		} else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, lService.create(p));
		}

	}

	public ResponseEntity<Object> showbyIdLives(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.showById(id));
	}

	public ResponseEntity<Object> showbyIdLives(String s, Pageable p, Long categ) {

		if (categ != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.searchByCateg(s, categ, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, lService.search(s, p));
		}
	}

	public ResponseEntity<Object> updateLives(Long id, Live l) {

		// Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, lService.update(id, l));

	}

	public ResponseEntity<Object> updateLives(Long id, StatusDTO status) {

		Live l = lService.showById(id).get();
		l.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, lService.update(id, l));

	}

	public ResponseEntity<Object> deleteLives(Long id) {

		lService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	/*
	 * 
	 * Podcast
	 * 
	 */

	public ResponseEntity<Object> showP() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.show());
	}

	public ResponseEntity<Object> showPodcastByPage(Pageable p, Long categ, Long langue) {

		if (categ != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByCateg(categ, p));
		} else if (langue != null && categ == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showByLang(langue, p));
		} else if (langue != null && categ != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					podcastservice.showByGenreAndLang(categ, langue, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showPage(p));
		}
	}

	public ResponseEntity<Object> createP(Podcast p) {

		if (podcastservice.findByName(p.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Ce Podcast existe déja"));
		} else {
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, podcastservice.create(p));
		}

	}

	public ResponseEntity<Object> showbyIdP(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.showById(id));
	}

	public ResponseEntity<Object> showbyIdP(String s, Pageable p, Long categ, Long langue) {

		if (categ != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByCateg(s, categ, p));
		} else if (langue != null && categ == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.searchByLang(s, langue, p));
		} else if (langue != null && categ != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					podcastservice.searchByGenreAndLang(s, categ, langue, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, podcastservice.search(s, p));
		}
	}

	public ResponseEntity<Object> updateP(Long id, Podcast p) {

		// Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, podcastservice.upadte(id, p));

	}

	public ResponseEntity<Object> updateStatusPodcast(Long id, StatusDTO status) {

		Podcast p = podcastservice.showById(id).get();
		p.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, podcastservice.upadte(id, p));

	}

	public ResponseEntity<Object> deleteP(Long id) {

		podcastservice.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	/*
	 * 
	 * FILMS
	 * 
	 */

	public ResponseEntity<Object> showM() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.show());
	}

	public ResponseEntity<Object> showMovieByPage(Pageable p, Long genre, Long langue) {

		if (langue != null && genre == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showByLangue(langue, p));

		} else if (genre != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showByGenre(genre, p));

		} else if (genre != null && langue != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					filmService.showByGenreAndLang(genre, langue, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showPages(p));
		}

	}

	public ResponseEntity<Object> createM(Film film) {

		if (filmService.findByName(film.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Ce film existe déja"));
		} else {
			// Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, filmService.create(film));
		}

	}

	public ResponseEntity<Object> showbyIdM(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.showById(id));
	}

	public ResponseEntity<Object> search(String s, Pageable p, Long genre, Long langue) {

		if (langue != null && genre == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.searchByLangue(s, langue, p));

		} else if (genre != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.searchByGenre(s, genre, p));

		} else if (genre != null && langue != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					filmService.searchByGenreAndLang(s, genre, langue, p));

		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.search(s, p));

		}
	}

	public ResponseEntity<Object> updateM(Long id, Film film) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, filmService.upadte(id, film));

	}

	public ResponseEntity<Object> updateM(Long id, StatusDTO status) {
		Film film = filmService.showById(id).get();
		film.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, filmService.upadte(id, film));

	}

	public ResponseEntity<Object> deleteM(Long id) {

		filmService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	/*
	 * 
	 * CRUD Series
	 * 
	 * 
	 */

	public ResponseEntity<Object> showS() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.show());
	}

	public ResponseEntity<Object> showSerieByPage(Pageable p, Long genre, Long langue) {

		if (genre != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByGenre(genre, p));

		} else if (langue != null && genre == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showByLangue(langue, p));
		} else if (langue != null && genre != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					serieService.showByGenreAndLangue(genre, langue, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showPage(p));

		}
	}

	public ResponseEntity<Object> createS(Serie serie) {

		if (serieService.findByName(serie.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Cette serie existe déja"));
		} else {
			// Save du tout
			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, serieService.create(serie));
		}

	}

	public ResponseEntity<Object> showbyIdS(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.showById(id));
	}

	public ResponseEntity<Object> showbyIdS(String s, Pageable p, Long genre, Long langue) {

		if (genre != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByGenre(s, genre, p));

		} else if (langue != null && genre == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.searchByLangue(s, langue, p));
		} else if (langue != null && genre != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					serieService.searchByLangueAndGenre(s, langue, genre, p));

		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.search(s, p));

		}
	}

	public ResponseEntity<Object> updateS(Long id, Serie serie) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, serieService.upadte(id, serie));

	}

	public ResponseEntity<Object> updateStatusSerie(Long id, StatusDTO status) {

		Serie serie = serieService.showById(id).get();
		serie.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, serieService.upadte(id, serie));

	}

	public ResponseEntity<Object> deleteS(Long id) {

		if (serieService.showById(id).isEmpty()) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					Map.of("name", "Vous tentez de supprimer une serie qui n'existe pas"));
		} else {

			serieService.delete(id);

			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);

		}

	}

	/*
	 * CRUD DES SAISONS DE SERIE
	 */

	public ResponseEntity<Object> showSaison() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.show());
	}

	public ResponseEntity<Object> showSaisonPage(Pageable p, Long langue, Long serie) {

		if (langue != null && serie == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByLangue(langue, p));
		} else if (serie != null && langue != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showByidSerie(serie, p));

		} else if (langue != null && serie != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					saisonService.showByLangueAndSerie(langue, serie, p));

		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showPage(p));

		}
	}

	public ResponseEntity<Object> showSaisonBySerie(Long idSerie, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				saisonService.showBySerie(serieService.showById(idSerie).get(), p));
	}

	public ResponseEntity<Object> createS(Saison saison) {

		// Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, saisonService.create(saison));

	}

	public ResponseEntity<Object> showbyIdSaison(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.showById(id));
	}

	public ResponseEntity<Object> showbyNameC(String s, Long langue, Long serie, Pageable p) {

		if (langue != null && serie == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					saisonService.searchByLangue(s, langue, p));
		} else if (serie != null && langue != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.searchBySerie(s, serie, p));

		} else if (langue != null && serie != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					saisonService.searchByLangueAndSerie(s, langue, serie, p));

		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, saisonService.search(s, p));
		}

	}

	public ResponseEntity<Object> updateSaison(Long id, Saison saison) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, saisonService.update(id, saison));

	}

	public ResponseEntity<Object> updateStatusSaison(Long id, StatusDTO status) {

		Saison saison = saisonService.showById(id);
		saison.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, saisonService.update(id, saison));

	}

	public ResponseEntity<Object> deleteSaison(Long id) {

		saisonService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}

	// FIN SAISON

	/*
	 * 
	 * EPISODES
	 * 
	 * 
	 */

	public ResponseEntity<Object> showE() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.show());
	}

	public ResponseEntity<Object> showE(Pageable p, Long serie, Long saison, Long langue) {

		if (langue != null && serie == null && saison == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showByLangue(langue, p));
		} else if (saison != null && langue == null && serie == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySaison(saison, p));
		} else if (serie != null && langue == null && saison == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showBySerie(serie, p));
		} else if (serie != null && saison != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					episodeService.showBySaisonAndSerie(serie, saison, p));
		} else if (serie != null && langue != null && saison != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					episodeService.showBySaisonAndLangueAndSerie(serie, langue, saison, p));
		} else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showPage(p));
		}
	}

	public ResponseEntity<Object> showE(Long idSaison) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				episodeService.showBySaison(saisonService.showById(idSaison)));
	}

	public ResponseEntity<Object> showbyIdE(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.showById(id));
	}

	public ResponseEntity<Object> searchEp(String s, Pageable p, Long serie, Long saison, Long langue) {

		if (langue != null && saison == null && serie == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					episodeService.searchByLangue(s, langue, p));

		} else if (saison != null && langue == null && serie == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					episodeService.searchBySaison(s, saison, p));

		}

		else if (serie != null && langue == null && saison == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.searchBySerie(s, serie, p));

		}

		else if (serie != null && saison != null && langue == null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					episodeService.searchBySaisonAndSerie(s, serie, saison, p));
		}

		else if (serie != null && langue != null && saison != null) {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
					episodeService.searchBySaisonAndLangueAndSerie(s, serie, langue, saison, p));
		}

		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, episodeService.search(s, p));

		}
	}

	public ResponseEntity<Object> createE(Episode episode) {

		return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, episodeService.create(episode));

	}

	public ResponseEntity<Object> updateE(Long id, Episode episode) {

		// Save du tout
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, episodeService.upadte(id, episode));

	}

	public ResponseEntity<Object> updateStatusEpisode(Long id, StatusDTO status) {

		Episode episode = episodeService.showById(id).get();
		episode.setStatus(status.getStatus());
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, episodeService.upadte(id, episode));

	}

	public ResponseEntity<Object> deleteE(Long id) {

		episodeService.delete(id);

		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, true);
	}
	// FIN EPISODE
	
	
	/*
	 * 
	 * CRUD CATEG ARTICLE
	 * 
	 */
	public ResponseEntity<Object> cat_article_show(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catArtService.show());
	}
    
	public ResponseEntity<Object> cat_article_show_Paging(Pageable p){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catArtService.showByPage(p));
	}
    
	public ResponseEntity<Object> cat_article_show_byid(long id){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catArtService.showById(id));
	}
    
	public ResponseEntity<Object> cat_article_create(CategArticle ca){

    	
    	if(catArtService.findbyname(ca.getName()) != null) {
    		
    		return EntityResponse.generateResponse("ATTENTION ", HttpStatus.CREATED, Map.of("name", "ce nom existe déja"));
    	}
    	else {
    		
    		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catArtService.create(ca));
    	}
    	
    	
    }
    
	public ResponseEntity<Object> cat_article_update( Long id, CategArticle a){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, catArtService.update(id, a));
		
	}
	
	
	public ResponseEntity<Object> cat_article_update_status(Long id, StatusDTO status) {

		CategArticle a = catArtService.showById(id).get();
		
		a.setStatus(status.getStatus());
		
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, catArtService.update(id, a));

	}
	
	public ResponseEntity<Object> cat_article_delete( Long id){
    	catArtService.delete(id);
    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "Categorie supprimé" );
	}
	
	
	/*
	 * 
	 * CRUD ARTICLE
	 * 
	 */
	
	public ResponseEntity<Object> article_show(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.show());
	}
	
	public ResponseEntity<Object> article_show_Paging(Pageable p){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.showPage(p));
	}
    
	public ResponseEntity<Object> article_show_byid(long id){

		if(artService.showById(id) ==null) {
			
			return EntityResponse.generateResponse("ERREUR", HttpStatus.BAD_REQUEST, Map.of("message", "aucun article pour cette valeur"));
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.showById(id));
    	}
	}

	public ResponseEntity<Object> article_search_filtre(
			String s,
			List<Long> categ,
			Pageable p
			
			){
		
		if(categ == null ) {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.search(s, p));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.searchByCateg(s, categ, p));
		
		}
	}
	
	public ResponseEntity<Object> article_create(Article a){
		
		if(artService.findByTitle(a.getTitle()) != null) {
    		
    		return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST, Map.of("title", "ce titre existe déja"));
    	}
    	else {
    		return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, artService.create(a));
    		
    	}
    	
    }
    
	public ResponseEntity<Object> article_update_status(Long id, StatusDTO status) {

		Article a = artService.showById(id).get();
		
		a.setStatus(status.getStatus());
		
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, artService.update(id, a));

	}
	
	
	public ResponseEntity<Object> article_update( Long id, Article a){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.update(id, a));
		
	}

	public ResponseEntity<Object> article_delete( Long id){
    	artService.delete(id);
    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "Article supprimé" );
	}

}
