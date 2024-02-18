package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.repository.FilmRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ServiceFilm {
	
	
	@Autowired
	private FilmRepository rep;

	
	public Film create(Film g) {
		
		return rep.save(g);
		
	}
	
	public List<Film> show() {
		
		return rep.findAll();
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
