package com.mytv.api.service;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.Rating;


@AutoConfiguration
public interface RatingService {

	Rating create(Rating u);
	List<Rating> show();
	List<Rating> showById(Long id);
	Rating upadte(Long id, Rating p);
	Boolean delete(Long id);

}
