package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.Media;

@AutoConfiguration
public interface MediaService {
	
	Media create(Media u);
	List<Media> show();
	List<Media> showById(Long id);
	Media upadte(Long id, Media p);
	Boolean delete(Long id);

}
