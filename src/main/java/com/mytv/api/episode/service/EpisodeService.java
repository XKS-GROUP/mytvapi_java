package com.mytv.api.episode.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.repository.EpisodeRepository;
import com.mytv.api.saison.model.Saison;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class EpisodeService {


	@Autowired
	private EpisodeRepository rep;

	//@Autowired
	//private SeasonRepository repS;

	public Episode create(Episode g) {
		//g.setSaisonRef(g.getIdSaison().getIdSaison());
		
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
	
	public Page<Episode> showBySerie(Long id, Pageable p){
		
		return rep.findByIdSerie(id, p);
		
	};
	
	public Page<Episode> showBySaison(Long id, Pageable p){
		
		
		return rep.findBySaisonRef(id , p);
	};
	
	
	public Page<Episode> showBySaisonAndLangueAndSerie(Long saison, long langue, long serie ,  Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getSaisonRef()== saison )
				   .filter(f -> f.getIdSerie() ==  serie )
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Episode> showBySaisonAndSerie(Long saison, long serie ,  Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(rep.findAll().stream()
				   .filter(f -> f.getSaisonRef()== saison )
				   .filter(f -> f.getIdSerie() ==  serie )
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	

	public Page<Episode> search(String val, Pageable p) {

		return rep.findByNameContainingOrOverviewContaining(val, val, p);
	}
	
	public Page<Episode> searchByLangue(String val, Long langue, Pageable p) {
		
		PageImpl<Episode> res = new PageImpl<Episode>(
				rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Episode> searchBySaison(String val, Long saison, Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(
				rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getSaisonRef() == saison)
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Episode> searchBySerie(String val, Long serie, Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(
				rep.findByNameContainingOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getIdSerie() == serie)
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	
	public Page<Episode> searchBySaisonAndLangue(String val, Long saison, long langue, Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getSaisonRef()==saison )
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	
	public Page<Episode> searchBySaisonAndLangueAndSerie(String val, Long saison, long langue, long serie ,  Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(
				
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getSaisonRef()== saison )
				   .filter(f -> f.getIdSerie() ==  serie )
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Episode> searchBySaisonAndSerie(String val, Long saison, long serie ,  Pageable p) {

		PageImpl<Episode> res = new PageImpl<Episode>(
				
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getSaisonRef()== saison )
				   .filter(f -> f.getIdSerie() ==  serie )
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Episode upadte(Long id, Episode u) {

		u.setIdEpisode(id);
		//u.setSaisonRef(u.getIdSaison().getIdSaison());

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
