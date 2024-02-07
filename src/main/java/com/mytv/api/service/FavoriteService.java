package com.mytv.api.service;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.Favorite;



@AutoConfiguration
public interface FavoriteService {
	Favorite create(Favorite u);
	List<Favorite> show();
	List<Favorite> showById(Long id);
	Favorite upadte(Long id, Favorite p);
	Boolean delete(Long id);
}
