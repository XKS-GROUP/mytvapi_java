package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestMedia.SerieGenre;
import com.mytv.api.repository.GenreRepository;
import com.mytv.api.repository.SerieGenreRepository;
import com.mytv.api.repository.SerieRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class SerieService {


	@Autowired
	private SerieRepository rep;
	
	@Autowired
	GenreRepository genreRep;
	
	@Autowired
	SerieGenreRepository serieGenreRep;

	public Serie create(Serie g) {
		
		
		//Recuperation de la liste des genres
		//Teste de chaque valeur, si il n existe pas ce genre sera creer
		Serie serie = rep.save(g);

		if (!g.getGenreList().isEmpty()){

			for (Long gr : g.getGenreList()) {
				
					addSerieGenreId(g.getIdSerie(), gr);

			}
				
		}

		return serie;

	}

	public List<Serie> show() {

		return rep.findAll();
	}
	
	public Page<Serie> showPage(Pageable p) {

		return rep.findAll(p);
	}
	
	
	public List<Serie> showByLangue(Long id, Pageable p){
		
		return rep.findAll().stream()
                     .filter(f -> f.getLangue().contains(id))
                     .collect(Collectors.toList());
		
	};
	
	public List<Serie> showByGenre(Long id, Pageable p){
		
		return rep.findAll().stream()
                     .filter(f -> f.getGenreList().contains(id))
                     .collect(Collectors.toList());
		
	};
	
	
	
	public List<Serie> search(String n, Pageable p) {

		return rep.findByNameOrOverviewContaining(n, n, p);
	}
	
	public List<Serie> searchByGenre(String n, Long genre, Pageable p) {

		return rep.findByNameOrOverviewContaining(n, n, p).stream()
                .filter(f -> f.getGenreList().contains(genre))
                .collect(Collectors.toList());
		
	}
	
	public List<Serie> searchByLangue(String n, Long langue, Pageable p) {

		return rep.findByNameOrOverviewContaining(n, n, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList());
	}

	public Serie upadte(final Long id, Serie u) {

		
		Serie old = rep.findById(id).get();

		old = u;


		old.setIdSerie(id);

		return rep.save(old);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Serie> showById(final Long id) {

		return rep.findById(id);

	}
	
	//Lier  Serie   genres
	public void addSerieGenre(Serie serie, Genre genre) {

		SerieGenre serieGenre = new SerieGenre();

		//serieGenre.setSerie(serie);

		//userRole.setUser(user);

		/*if (role == null) {
			role = roleService.findDefaultRole();
			
		}*/
		//serieGenre.setGenre(genre);

		serieGenreRep.save(serieGenre);


	}
	
	public void addSerieGenreId(Long serie, Long genre) {

		SerieGenre serieGenre = new SerieGenre();

		serieGenre.setSerie(serie);

		serieGenre.setGenre(genre);

		serieGenreRep.save(serieGenre);


	}

	public Serie findByName(String name) {
		
		return rep.findByName(name);
	}

}
