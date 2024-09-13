package com.mytv.api.film.service;


import java.net.URL;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.aws.service.AmazonS3ServiceImpl;
import com.mytv.api.config.AlgoliaConfig;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.model.FilmGenre;
import com.mytv.api.film.repository.FavFilmRepository;
import com.mytv.api.film.repository.FilmRepository;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.intervenant.repository.DirectorRepository;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.service.GenreService;
import com.mytv.api.serie.repository.FilmGenreRepository;
import com.mytv.api.serie.repository.GenreRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class ServiceFilm {


	@Autowired
	private FilmRepository rep;
	
	@Autowired
	private FavFilmRepository rep_fav_film;

	@Autowired
	FilmGenreRepository filmGenreRep;

	@Autowired
	GenreRepository genreRep;

	@Autowired
	GenreService genreService;
	
	@Autowired
	private ActorRepository rep_actor;
	
	@Autowired
	private DirectorRepository rep_dirs;
	
	@Autowired
	private LangRepository rep_langue;
	
	@Autowired
	private AlgoliaConfig algoClient;
	
	public Film create(Film g) {

			g.setActeurs(rep_actor.findAllById(g.getActeurList()));
			g.setGenres(genreRep.findAllById(g.getGenreList()));
			g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
			g.setList_langues(rep_langue.findAllById(g.getLangue()));
			
			//Recuperation de la liste des genres
			//Teste de chaque valeur, si il n existe pas ce genre sera creer
			Film film = rep.save(g);

			if (!g.getGenreList().isEmpty()){

				for (Long gr : g.getGenreList()) {
						
				
						addFilmGenreId(g, gr);

					}
					
				}
			
			var resp = algoClient.searchClient().saveObject("film", film);
			
			algoClient.searchClient().waitForTask ("film", resp.getTaskID());
			
			return film;

	}

	
	public Page<Film> similaire_show(Long id, Pageable p) {
		
		refresh();
		
		Film m =  rep.findById(id).get();
		
		PageImpl<Film> res = new PageImpl<Film>(rep.findAll().stream()
				
				   .filter(rd -> rd.getGenreList().containsAll(m.getGenreList()) )
				   .toList()
				   , p
				   , rep.findAll().size());
		
		return res;	
		
	}
	
	//Lier  films   genres
	public void addFilmGenre(Film film, Genre genre) {

		FilmGenre filmGenre = new FilmGenre();

		//filmGenre.setFilm(film);

		//filmGenre.setGenre(genre);

		filmGenreRep.save(filmGenre);


	}
	
	public void addFilmGenreId(Film film, Long genre) {

		FilmGenre filmGenre = new FilmGenre();

		//filmGenre.setFilm(film);

		filmGenre.setGenre(genre);

		filmGenreRep.save(filmGenre);


	}

	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			rep_fav_film.findAll().forEach(
					
					f -> {
						
						f.getFilm().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			//Afficher que les favories du l utilisateur actuelle
			rep_fav_film.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getFilm().setFavorie(true);
					}
					
				);
		}
		
	       List<Film> l = rep.findAll();
			
			l.forEach(  
					
					g -> {
						
						g.setActeurs(rep_actor.findAllById(g.getActeurList()));
						g.setGenres(genreRep.findAllById(g.getGenreList()));
						g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
						g.setList_langues(rep_langue.findAllById(g.getLangue()));
					}
			);
			
			
		}
	
	public List<Film> show() {

		refresh();
		return rep.findAll();
	}

	public List<Film> show_f() {

		refresh();
		return rep.findAll().stream().filter(
				
				f->f.isStatus()
				
				).toList();
	} 
	
	public Page<Film> showPages(Pageable p) {
		refresh();
		return rep.findAll(p);
	}
	
	public Page<Film> showPages_front(Pageable p) {
		refresh();
		
		PageImpl<Film> res = new PageImpl<Film>(rep.findAll(p).stream()
				.filter(f-> f.isStatus())
				   .toList() 
				   , p
				   , rep.findAll().size());
		
		return res;
	}

	@SuppressWarnings("unused")
	public PageImpl<Film> filtre_complet(Long genre, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Film> res = new PageImpl<Film>(rep.findAll(p).stream()
				   .toList() 
				   , p
				   , rep.findAll().size());
		
		if(genre != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .toList().size());
		}
		else if(langue != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
		}
		else if(pays != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
			
		}
		else if(genre != null && langue != null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
		}
		else if(genre != null && pays != null && langue == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else if(pays != null && langue != null && genre == null) {
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
		}
		else if (pays != null && langue != null && genre != null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public PageImpl<Film> filtre_complet_front(Long genre, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Film> res = new PageImpl<Film>(rep.findAll(p).stream()
				.filter(f -> f.isStatus())
				   .toList() 
				   , p
				   , rep.findAll(p).stream()
					.filter(f -> f.isStatus()).toList().size());
		
		if(genre != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(langue != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(pays != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(genre != null && langue != null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(genre != null && pays != null && langue == null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(pays != null && langue != null && genre == null) {
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if (pays != null && langue != null && genre != null) {
			
			return res = new PageImpl<Film>(rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll(p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public PageImpl<Film> filtre_recherche_complet(String val, Long genre, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Film> res = null;
		if(val==null || val.isBlank() ||val.isEmpty()) {
			return filtre_complet(genre, langue, pays, p);
		}
		else if(genre != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .toList().size());
		}
		else if(langue != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
		}
		else if(pays != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
			
		}
		else if(genre != null && langue != null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findAll().size());
			
		}
		else if(genre != null && pays != null && langue == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else if(pays != null && langue != null && genre == null) {
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
		}
		else if (pays != null && langue != null && genre != null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else if (pays == null && langue == null && genre == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .toList().size());
		}
		else {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .toList().size());
				
		}
			
	};
	
	
	@SuppressWarnings("unused")
	public PageImpl<Film> filtre_recherche_complet_front(String val, Long genre, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Film> res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .filter(f -> f.isStatus())
				   .toList() 
				   , p
				   , rep.findAll(p).stream()
				   .filter(f -> f.isStatus())
				   .toList().size());
		
		if(val==null || val.isBlank() ||val.isEmpty()) {
			System.out.println(" Declanche le truc papa");
			return filtre_complet_front(genre, langue, pays, p);
		}
		else if(genre != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(langue != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(pays != null && genre == null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
			
		}
		else if(genre != null && langue != null && pays == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findAll().size());
			
		}
		else if(genre != null && pays != null && langue == null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if(pays != null && langue != null && genre == null) {
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else if (pays != null && langue != null && genre != null) {
			
			return res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList()
					   , p
					   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getGenreList().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.isStatus())
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	
	public Film update( Long id, Film g) {

		g.setIdFilm(id);
		g.setActeurs(rep_actor.findAllById(g.getActeurList()));
		g.setGenres(genreRep.findAllById(g.getGenreList()));
		g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
		g.setList_langues(rep_langue.findAllById(g.getLangue()));
		refresh();
		
		Film f = rep.save(g);
		algoClient.refreshFilm();
		return f;
	}

	public Boolean delete(Long id) {

		
		rep.deleteById(id);
		refresh();
		algoClient.refreshFilm();
		return null;

	}

	public Optional<Film> showById(Long id) {

		refresh();
		/*
		Optional<Film> f = rep.findById(id);
		
		String objetId = f.get().getVideoFile();
		
		int indexDernierSlash = objetId.lastIndexOf('/');

		String nomFichier = objetId.substring(indexDernierSlash + 1);
		
		URL pre = AmazonS3ServiceImpl.generatePresignedUrl( nomFichier, 10);
		
		f.get().setVideoFile(pre.toString());
		*/
		
		return rep.findById(id);

	}

	public Film findByName(String name) {
		refresh();
		return rep.findByName(name);
	}
	
	public List<Film> top10(){
		refresh();
		return rep.findByTop10True();
	}
	
	public Film Addtop10(Long id, boolean status){
		
		Film r =  rep.findById(id).get();
		r.setTop10(status);
		refresh();
		return r;
	}
	
	public Film top(){
		refresh();
		return rep.findByTopTrue();
	}
	
	public Film Addtop(Long id, boolean status){
		
		Film r =  rep.findById(id).get();
		r.setTop10(status);
		refresh();
		return r;
	}
	
	public Film checktoplimit() {
		refresh();
		if(rep.findByTopTrue() != null) {
			
			return rep.findByTopTrue();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	public List<Film> checktop10limit() {
		refresh();
		if(rep.findByTop10True().size() <=10) {
			
			return null;
		}
		else {
			
			return rep.findByTop10True();
		}
	}

}
