package com.mytv.api.ressource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.ressource.model.CategorieLive;
import com.mytv.api.ressource.repository.CategorieLiveRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategorieLiveService {


	@Autowired
	private CategorieLiveRepository rep;


	public CategorieLive create(CategorieLive g) {

		return rep.save(g);

	}

	public List<CategorieLive> show() {

		return rep.findAll();
	}
	
	public Page<CategorieLive> showPaging(Pageable p) {

		return rep.findAll(p);
	}

	public CategorieLive upadte(final Long id, CategorieLive u) {


		u.setId(id);

		return rep.save(u);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<CategorieLive> showById(final Long id) {

		return rep.findById(id);

	}

}
