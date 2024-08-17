package com.mytv.api.podcast;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatPodcastService {


	@Autowired
	private CatPodcastRepository rep;


	public CatPodcast create(CatPodcast g) {

		return rep.save(g);

	}

	public List<CatPodcast> show() {

		return rep.findAll();
	}
	
	public Page<CatPodcast> showPaging(Pageable p) {

		return rep.findAll(p);
	}

	public CatPodcast upadte(final Long id, CatPodcast u) {

		CatPodcast old = rep.findById(id).get();

		old = u;

		old.setIdCatPod(id);

		return rep.save(old);
	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<CatPodcast> showById(final Long id) {

		return rep.findById(id);

	}

}
