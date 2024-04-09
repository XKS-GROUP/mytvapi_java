package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.repository.EpisodeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class EpisodeService {


	@Autowired
	private EpisodeRepository rep;


	public Episode create(Episode g) {

		return rep.save(g);

	}

	public List<Episode> show() {

		return rep.findAll();
	}
	
	public List<Episode> showBySaison(Long idSaison) {

		return rep.findByIdSaison(idSaison);
	}
	
	public Page<Episode> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public List<Episode> showByNameContain(String name) {

		return rep.findByNameContaining(name);
	}

	public Episode upadte(final Long id, Episode u) {

		Episode old = rep.findById(id).get();

		old = u;

		old.setIdEpisode(id);

		return rep.save(old);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Episode> showById(final Long id) {

		return rep.findById(id);

	}

}
