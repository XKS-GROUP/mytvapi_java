package com.mytv.api.ressource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.ressource.model.CategoryRL;
import com.mytv.api.ressource.repository.CategoryLrRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CategoryLrService {


	@Autowired
	private CategoryLrRepository rep;


	public CategoryRL create(CategoryRL g) {

		return rep.save(g);

	}

	public List<CategoryRL> show() {

		return rep.findAll();
	}
	
	public Page<CategoryRL> showPaging(Pageable p) {

		return rep.findAll(p);
	}

	public CategoryRL upadte(final Long id, CategoryRL u) {

		CategoryRL old = rep.findById(id).get();

		old = u;

		old.setIdcat(id);

		return rep.save(old);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<CategoryRL> showById(final Long id) {

		return rep.findById(id);

	}

}
