package com.mytv.api.film.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
	
	public Film createFilm(Film g) {

			g.setActeurs(rep_actor.findAllById(g.getActeurList()));
			g.setGenres(genreRep.findAllById(g.getGenreList()));
			g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
			g.setList_langues(rep_langue.findAllById(g.getLangue()));
			//Recuperation de la liste des genres
			//Teste de chaque valeur, si il n existe pas ce genre sera creer
			Film film = rep.save(g);

			if (!g.getGenreList().isEmpty()){

				for (Long gr : g.getGenreList()) {
						
					
						Genre existingGenre = genreRep.findById(gr).get();

					if(existingGenre != null) {

						addFilmGenre(g, existingGenre);

					}
					else {

						Genre ngr = new Genre();
						ngr.setName("AUCUN");
						if(genreRep.findByName(ngr.getName()) != null ) {
							
							addFilmGenre(g, genreRep.findByName(ngr.getName()));
						}
						else {
							genreRep.save(ngr);
							addFilmGenre(g, ngr);
						}
						

					}/**/

				}

			}

			else {
				
				Genre ngr = new Genre();
				ngr.setName("AUCUN");
				if(genreRep.findByName(ngr.getName()) != null ) {
					
					addFilmGenre(g, genreRep.findByName(ngr.getName()));
				}
				else {
					genreRep.save(ngr);
					addFilmGenre(g, ngr);
				}
				addFilmGenre(g, genreRep.findByName("AUCUN"));
			}

			return film;

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

	public Page<Film> showPages(Pageable p) {
		refresh();
		return rep.findAll(p);
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
				   , rep.findAll().size());
		
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
		PageImpl<Film> res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .toList() 
				   , p
				   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .toList() .size());
		
		if(genre != null && langue == null && pays == null) {
			
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
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public PageImpl<Film> filtre_recherche_complet_front(String val, Long genre, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Film> res = new PageImpl<Film>(rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .filter(f -> f.isStatus())
				   .toList() 
				   , p
				   , rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .filter(f -> f.isStatus())
				   .toList().size());
		
		if(genre != null && langue == null && pays == null) {
			
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
	
	
	
	public Film upadte(final Long id, Film g) {

		g.setIdFilm(id);
		g.setActeurs(rep_actor.findAllById(g.getActeurList()));
		g.setGenres(genreRep.findAllById(g.getGenreList()));
		g.setDirectors(rep_dirs.findAllById(g.getDirectorList()));
		g.setList_langues(rep_langue.findAllById(g.getLangue()));
		refresh();
		return rep.save(g);
	}

	public Boolean delete(Long id) {

		
		rep.deleteById(id);
		refresh();
		return null;

	}

	public Optional<Film> showById(Long id) {

		refresh();
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
