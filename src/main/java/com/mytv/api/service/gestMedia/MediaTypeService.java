package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.MediaType;


@AutoConfiguration
public interface MediaTypeService {
	
	MediaType create(MediaType u);
	List<MediaType> show();
	List<MediaType> showById(Long id);
	MediaType upadte(Long id, MediaType p);
	Boolean delete(Long id);

}
