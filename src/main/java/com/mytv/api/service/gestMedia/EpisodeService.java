package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.repository.EpisodeRepository;
import com.mytv.api.repository.SeasonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class EpisodeService {


	@Autowired
	private EpisodeRepository rep;

	@Autowired
	private SeasonRepository repS;

	public Episode create(Episode g) {

		return rep.save(g);

	}

	public List<Episode> show() {

		return rep.findAll();
	}
	
	public List<Episode> showBySaison(Saison idSaison) {

		return rep.findByIdSaison(idSaison);
	}
	
	public Page<Episode> showPage(Pageable p) {

		return rep.findAll(p);
	}
	
	public Page<Episode> showByLangue(Long id, Pageable p){
		
		PageImpl<Episode> res = new PageImpl<Episode>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	};
	
	public List<Episode> showBySerie(Long id, Pageable p){
		
		return rep.findAll().stream()
                     .filter(f -> f.getLangue().contains(id))
                     .collect(Collectors.toList());
	};
	
	public List<Episode> showBySaison(Long id, Pageable p){
		
		
		return rep.findByIdSaison(repS.findById(id).get() , p);
	};
	
	

	public List<Episode> search(String val, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p);
	}
	
	public List<Episode> searchByLangue(String val, Long langue, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList());
	}
	
	public List<Episode> searchBySerie(String val, Long serie, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getLangue().contains(serie))
                .collect(Collectors.toList());
	}

	public Episode upadte(Long id, Episode u) {

		u.setIdEpisode(id);

		return rep.save(u);
	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Episode> showById(final Long id) {

		return rep.findById(id);

	}

	public Episode findByName(String name) {
		
		return rep.findByName(name);
	}

}
