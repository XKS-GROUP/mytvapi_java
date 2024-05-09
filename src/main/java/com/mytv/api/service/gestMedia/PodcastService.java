package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.repository.PodcastRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PodcastService {


	@Autowired
	private PodcastRepository rep;


	public Podcast create(Podcast g) {

		return rep.save(g);

	}

	public List<Podcast> show() {

		return rep.findAll();
	}

	public Page<Podcast> showPage(Pageable p) {

		return rep.findAll(p);
	}
	public List<Podcast> search(String n) {

		return rep.findByNameOrOverviewContaining(n, n);
	}

	public Podcast upadte(final Long id, Podcast u) {


		u.setIdPodcast(id);

		return rep.save(u);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}

	public Optional<Podcast> showById(final Long id) {

		return rep.findById(id);

	}

}
