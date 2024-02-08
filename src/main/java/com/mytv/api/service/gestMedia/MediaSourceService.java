package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.MediaSource;

@AutoConfiguration
public interface MediaSourceService {
	
	MediaSource create(MediaSource u);
	List<MediaSource> show();
	List<MediaSource> showById(Long id);
	MediaSource upadte(Long id, MediaSource p);
	Boolean delete(Long id);

}
