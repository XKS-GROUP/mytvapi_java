package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
				
				
				for (String gr : g.getGenreList()) {
					
						Genre existingGenre = genreRep.findByName(gr);
					
					if(existingGenre != null) {
						
						addFilmGenre(g, existingGenre);
						
					}
					else {
						
						Genre ngr = new Genre();						
						ngr.setName(gr);
						genreRep.save(ngr);
						addFilmGenre(g, ngr);
						
						
						
					}/**/
					
				}		
				
			} 
			
			else {
				addFilmGenre(g, genreRep.findByName("AUCUN"));
			}
			
			return film;
			
	}
	
	
	//Lier  films   genres
	public void addFilmGenre(Film film, Genre genre) {

		FilmGenre filmGenre = new FilmGenre();
		
		filmGenre.setFilm(film);
		
		//userRole.setUser(user);

		/*if (role == null) {
			role = roleService.findDefaultRole();
		}*/
		filmGenre.setGenre(genre);
		
		filmGenreRep.save(filmGenre);
		

	}

	public List<Film> show() {
		
		return rep.findAll();
	}
	
	public List<Film> showByNameContaining(String n) {
		
		return rep.findByNameContaining(n);
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

}
