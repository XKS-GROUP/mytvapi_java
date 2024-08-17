package com.mytv.api.film.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.film.model.Film;
import com.mytv.api.film.model.FilmGenre;
import com.mytv.api.film.repository.FilmRepository;
import com.mytv.api.ressource.model.Genre;
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
	FilmGenreRepository filmGenreRep;

	@Autowired
	GenreRepository genreRep;

	@Autowired
	GenreService genreService;

	public Film create(Film g) {

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

	public Film createFilm(Film g) {

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

	public List<Film> show() {

		return rep.findAll();
	}

	public Page<Film> showPages(Pageable p) {

		return rep.findAll(p);
	}

	public Page<Film> showByLangue(Long id, Pageable p){
		
		PageImpl<Film> res = new PageImpl<Film>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Film> showByGenre(Long id, Pageable p){
		
		PageImpl<Film> res = new PageImpl<Film>(rep.findAll().stream()
				   .filter(f -> f.getGenreList().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Film> showByGenreAndLang(Long g, Long l, Pageable p){
		
		
		PageImpl<Film> res = new PageImpl<Film>(
				rep.findAll().stream()
                .filter(f -> f.getGenreList().contains(g))
                .filter(f -> f.getLangue().contains(l))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};

	public List<Film> showByNameContaining(String n) {

		return rep.findByNameContaining(n);
	}
	
	public Page<Film> search(String val, Pageable p ) {

		return rep.findByNameContainingOrOverviewContaining(val, val, p);
	}
	
	public Page<Film> searchByLangue(String val, Long genre, Pageable p ) {

		
		PageImpl<Film> res = new PageImpl<Film>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getLangue().contains(genre))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}

	public Page<Film> searchByGenre(String val,Long langue, Pageable p ) {

		PageImpl<Film> res = new PageImpl<Film>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getGenreList().contains(langue))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Film> searchByGenreAndLang(String val , Long g, Long l, Pageable p){
		
		
		PageImpl<Film> res = new PageImpl<Film>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getGenreList().contains(g))
                .filter(f -> f.getLangue().contains(l))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};


	public Film upadte(final Long id, Film u) {

		Film old = rep.findById(id).get();

		old = u;
		old.setIdFilm(id);

		return rep.save(old);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Film> showById(final Long id) {

		return rep.findById(id);

	}

	public Film findByName(String name) {
		
		return rep.findByName(name);
	}
	public List<Film> top10(){
		
		return rep.findByTop10True();
	}
	
	public Film Addtop10(Long id, boolean status){
		
		Film r =  rep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
	public List<Film> top(){
		
		return rep.findByTopTrue();
	}
	
	public Film Addtop(Long id, boolean status){
		
		Film r =  rep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}

}
