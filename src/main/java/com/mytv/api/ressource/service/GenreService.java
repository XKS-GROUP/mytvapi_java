package com.mytv.api.ressource.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.ressource.model.Genre;
import com.mytv.api.serie.repository.GenreRepository;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Schema(description = "Modèle représentant le genre de serie et film.")
public class GenreService {


	@Autowired
	private GenreRepository genreRep;


	public Genre create(Genre g) {

		//Appel de la fonction upload pour enregistrer l'image sur R2

		return genreRep.save(g);

	}

	public List<Genre> show() {

		return genreRep.  findAll();
	}
	
	public Page<Genre> showPage(Pageable p) {

		return genreRep.findAll(p);
	}

	public Page<Genre> showByPages(Pageable p) {

		return genreRep.findAll(p);
	}

	public List<Genre> showByName(String name, Pageable p) {

		return genreRep.findByName(name, p);
	}

	public Genre upadte(final Long id, Genre u) {

		Genre old = genreRep.findById(id).get();

		old = u;

		old.setIdGenre(id);

		return genreRep.save(old);
	}



	public Boolean delete(Long id) {

		genreRep.deleteById(id);

		return null;

	}

	public List<Genre> findByNameContain(String name, Pageable p){

		return genreRep.findByNameContaining(name, p);
	}

	public List<Genre> findByNameContain(String name){

		return genreRep.findByNameContaining(name);
	}

	public Optional<Genre> showById(final Long id) {

		return genreRep.findById(id);

	}

}
