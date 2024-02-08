package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.Favorite;
import com.mytv.api.repository.FavoriteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoriteServiceImplement implements FavoriteService{
	
	@Autowired
	private FavoriteRepository favRep;

	@Override
	public Favorite create(Favorite u) {

		return favRep.save(u);
	}

	@Override
	public List<Favorite> show() {

		return favRep.findAll();
	}

	@Override
	public List<Favorite> showById(Long id) {

		return null;
	}

	@Override
	public Favorite upadte(Long id, Favorite p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {
		
		favRep.deleteById(id);

		return true;
	}
	
	

}
