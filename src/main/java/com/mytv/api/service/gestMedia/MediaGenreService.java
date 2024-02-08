package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.MediaGenre;

@AutoConfiguration
public interface MediaGenreService {
	
	MediaGenre create(MediaGenre u);
	List<MediaGenre> show();
	List<MediaGenre> showById(Long id);
	MediaGenre upadte(Long id, MediaGenre p);
	Boolean delete(Long id);

}
