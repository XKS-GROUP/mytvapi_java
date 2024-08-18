package com.mytv.api.replay.categorie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.replay.categorie.model.ReplayCateg;
import com.mytv.api.replay.categorie.repository.ReplayCategRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReplayCategService {
	@Autowired
	private ReplayCategRepository rep;


	public ReplayCateg create(ReplayCateg g) {

		//Appel de la fonction upload pour enregistrer l'image sur R2

		return rep.save(g);

	}

	public List<ReplayCateg> show() {

		return rep.  findAll();
	}
	
	public Page<ReplayCateg> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Page<ReplayCateg> showByPages(Pageable p) {

		return rep.findAll(p);
	}

	public List<ReplayCateg> showByName(String name, Pageable p) {

		return rep.findByName(name, p);
	}
	
	public ReplayCateg showByName(String name) {

		return rep.findByName(name);
	}

	public ReplayCateg upadte(Long id, ReplayCateg u) {

		u.setId(id);
		return rep.save(u);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public List<ReplayCateg> findByNameContain(String name, Pageable p){

		return rep.findByNameContaining(name, p);
	}

	public List<ReplayCateg> findByNameContain(String name){

		return rep.findByNameContaining(name);
	}

	public Optional<ReplayCateg> showById(final Long id) {

		return rep.findById(id);

	}

}
