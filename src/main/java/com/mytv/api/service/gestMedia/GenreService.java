package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.Genre;


@AutoConfiguration
public interface GenreService {
	
	Genre create(Genre u);
	List<Genre> show();
	List<Genre> showById(Long id);
	Genre upadte(Long id, Genre p);
	Boolean delete(Long id);

}
