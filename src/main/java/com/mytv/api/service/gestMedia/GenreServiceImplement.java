package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.repository.GenreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenreServiceImplement implements GenreService{
	
	@Autowired
	private GenreRepository genreRep;

	@Override
	public Genre create(Genre u) {
		return genreRep.save(u);
	}

	@Override
	public List<Genre> show() {
		return genreRep.findAll();
	}

	@Override
	public List<Genre> showById(Long id) {
		return null;
	}

	@Override
	public Genre upadte(Long id, Genre p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		genreRep.deleteById(id);
		return true;
	}
	
	

}
