package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


			for (String gr : g.getGenreList()) {

					Genre existingGenre = genreRep.findByName(gr);

				if(existingGenre != null) {

					addSerieGenre(g, existingGenre);

				}
				else {

					Genre ngr = new Genre();
					ngr.setName(gr);
					genreRep.save(ngr);
					addSerieGenre(g, ngr);



				}/**/

			}

		}

		else {
			addSerieGenre(g, genreRep.findByName("AUCUN"));
		}
		
		return serie;

	}

	public List<Serie> show() {

		return rep.findAll();
	}

	public List<Serie> showbyNameContaining(String n) {

		return rep.findByNameContaining(n);
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

			serieGenre.setSerie(serie);

			//userRole.setUser(user);

			/*if (role == null) {
				role = roleService.findDefaultRole();
			}*/
			serieGenre.setGenre(genre);

			serieGenreRep.save(serieGenre);


		}

}
