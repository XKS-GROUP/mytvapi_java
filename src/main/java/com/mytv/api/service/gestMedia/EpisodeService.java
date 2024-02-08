package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.Episode;

@AutoConfiguration
public interface EpisodeService {
	
	Episode create(Episode u);
	List<Episode> show();
	List<Episode> showById(Long id);
	Episode upadte(Long id, Episode p);
	Boolean delete(Long id);
	

}
