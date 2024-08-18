package com.mytv.api.replay.collection.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mytv.api.replay.collection.model.ReplayCollection;
import com.mytv.api.replay.collection.repository.ReplayCollectionRepository;

public class ReplayCollectionService {

	@Autowired
	ReplayCollectionRepository rep;
	
	
	public ReplayCollection create(ReplayCollection p) {

		return rep.save(p);
	}

	public List<ReplayCollection> show() {

		return rep.findAll();
	}
	
	
	public Page<ReplayCollection> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public ReplayCollection update(Long id, ReplayCollection p) {

		p.setId(id);

		return rep.save(p);
	}
	
	public Optional<ReplayCollection> showById(Long id) {

		return rep.findById(id);

	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}
}
