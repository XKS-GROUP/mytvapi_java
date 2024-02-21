package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.repository.GenreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenreService {
	
	
	@Autowired
	private GenreRepository genreRep;

	
	public Genre create(Genre g) {
		
		//Appel de la fonction upload pour enregistrer l'image sur R2
		
		
		
		return genreRep.save(g);
		
	}
	
	public List<Genre> show() {
		
		return genreRep.findAll();
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

	
	
	public Optional<Genre> showById(final Long id) {
		
		return genreRep.findById(id);
		
	}

}
