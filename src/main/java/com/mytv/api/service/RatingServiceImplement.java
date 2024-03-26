package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.Rating;
import com.mytv.api.repository.RatingRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RatingServiceImplement implements RatingService{

	@Autowired
	private RatingRepository ratRep;

	@Override
	public Rating create(Rating u) {
		return ratRep.save(u);
	}

	@Override
	public List<Rating> show() {

		return ratRep.findAll();
	}

	@Override
	public List<Rating> showById(Long id) {

		return null;
	}

	@Override
	public Rating upadte(Long id, Rating p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {

		ratRep.deleteById(id);
		return true;
	}




}
