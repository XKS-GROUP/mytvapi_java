package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.FilmGenre;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.repository.FilmGenreRepository;
import com.mytv.api.repository.FilmRepository;
import com.mytv.api.repository.GenreRepository;

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

		filmGenre.setFilm(film);

		//filmGenre.setGenre(genre);

		filmGenreRep.save(filmGenre);


	}
	
	public void addFilmGenreId(Film film, Long genre) {

		FilmGenre filmGenre = new FilmGenre();

		filmGenre.setFilm(film);

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
	
	public List<Film> showByGenreAndLang(Long g, Long l, Pageable p){
		
		return rep.findAll(p).stream()
                     .filter(f -> f.getGenreList().contains(g))
                     .collect(Collectors.toList());
		
	};

	public List<Film> showByNameContaining(String n) {

		return rep.findByNameContaining(n);
	}
	
	public Page<Film> search(String val, Pageable p ) {

		return rep.findByNameOrOverviewContaining(val, val, p);
	}
	
	public Page<Film> searchByLangue(String val, Long genre, Pageable p ) {

		
		PageImpl<Film> res = new PageImpl<Film>(
				rep.findByNameOrOverviewContaining(val, val).stream()
                .filter(f -> f.getLangue().contains(genre))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}

	public Page<Film> searchByGenre(String val,Long langue, Pageable p ) {

		PageImpl<Film> res = new PageImpl<Film>(
				rep.findByNameOrOverviewContaining(val, val).stream()
                .filter(f -> f.getGenreList().contains(langue))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}


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

}
