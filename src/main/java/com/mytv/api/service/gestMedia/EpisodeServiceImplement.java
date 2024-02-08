package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.repository.EpisodeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EpisodeServiceImplement implements EpisodeService {
	
	@Autowired
	private EpisodeRepository epRep;

	@Override
	public Episode create(Episode u) {

		return epRep.save(u);
	}

	@Override
	public List<Episode> show() {
		return epRep.findAll();
	}

	@Override
	public List<Episode> showById(Long id) {
		return null;
	}

	@Override
	public Episode upadte(Long id, Episode p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		epRep.deleteById(id);
		return true;
	}

}
