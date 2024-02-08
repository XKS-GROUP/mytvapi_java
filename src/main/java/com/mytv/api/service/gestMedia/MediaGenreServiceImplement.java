package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.MediaGenre;
import com.mytv.api.repository.MediaGenreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaGenreServiceImplement implements MediaGenreService{
	
	@Autowired
	private MediaGenreRepository medGenreRep;

	@Override
	public MediaGenre create(MediaGenre u) {
		return medGenreRep.save(u);
	}

	@Override
	public List<MediaGenre> show() {
		return medGenreRep.findAll();
	}

	@Override
	public List<MediaGenre> showById(Long id) {
		return null;
	}

	@Override
	public MediaGenre upadte(Long id, MediaGenre p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		 medGenreRep.deleteById(id);
		 return true;
	}

}
