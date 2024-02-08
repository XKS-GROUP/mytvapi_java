package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.Season;


@AutoConfiguration
public interface SeasonService {

	Season create(Season u);
	List<Season> show();
	List<Season> showById(Long id);
	Season upadte(Long id, Season p);
	Boolean delete(Long id);
}
