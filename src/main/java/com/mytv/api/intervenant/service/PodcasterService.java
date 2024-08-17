package com.mytv.api.intervenant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.model.Podcasteur;
import com.mytv.api.intervenant.repository.PodcasterRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PodcasterService {

	@Autowired
	PodcasterRepository rep;
	
	
	public Podcasteur create(Podcasteur p) {

		return rep.save(p);

	}

	public List<Podcasteur> show() {

		return rep.findAll();
	}
	
	public Page<Podcasteur> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Podcasteur update(Long id, Podcasteur p) {

		p.setId(id);

		return rep.save(p);
	}
	
	public Optional<Podcasteur> showById(Long id) {

		return rep.findById(id);

	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}
}
