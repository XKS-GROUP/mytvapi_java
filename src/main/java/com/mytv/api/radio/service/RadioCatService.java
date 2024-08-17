package com.mytv.api.radio.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.radio.repository.RadioCatRepository;
import com.mytv.api.ressource.model.CategoryRL;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RadioCatService {


	@Autowired
	private RadioCatRepository radioRep;


	public CategoryRL create(CategoryRL g) {

		return radioRep.save(g);

	}

	public List<CategoryRL> show() {

		return radioRep.findAll();
	}
	
	public Page<CategoryRL> showPage(Pageable p) {

		return radioRep.findAll(p);
	}

	public CategoryRL upadte(final Long id, CategoryRL u) {

		CategoryRL old = radioRep.findById(id).get();

		old = u;

		old.setIdcat(id);

		return radioRep.save(old);
	}



	public Boolean delete(Long id) {

		radioRep.deleteById(id);

		return null;

	}

	public Optional<CategoryRL> showById(final Long id) {

		return radioRep.findById(id);

	}

}
