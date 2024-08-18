package com.mytv.api.replay.replay.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.intervenant.repository.DirectorRepository;
import com.mytv.api.replay.categorie.repository.ReplayCategRepository;
import com.mytv.api.replay.intervenant.repository.IntervenantRepository;
import com.mytv.api.replay.replay.model.Replay;
import com.mytv.api.replay.replay.repository.ReplayRepository;
import com.mytv.api.ressource.model.Genre;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.service.GenreService;
import com.mytv.api.serie.repository.FilmGenreRepository;
import com.mytv.api.serie.repository.GenreRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ReplayService {

	@Autowired
	private ReplayRepository rep;


	@Autowired
	ReplayCategRepository rep_categ;
	
	@Autowired
	private IntervenantRepository rep_intervenant;
	
	@Autowired
	private LangRepository rep_langue;
	

	public Replay create(Replay r) {

		return rep.save(r);

	}

	public List<Replay> show() {

		return rep.findAll();
	}

	public Page<Replay> showPages(Pageable p) {

		return rep.findAll(p);
	}
/*
	public Page<Replay> showByLangue(List <Language> id, Pageable p){
		
		PageImpl<Replay> res = new PageImpl<Replay>(rep.findAll().stream()
				   .filter(f -> f.getLangues() == id)
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Replay> showByGenre(Long id, Pageable p){
		
		PageImpl<Replay> res = new PageImpl<Replay>(rep.findAll().stream()
				   .filter(f -> f.getGenreList().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Replay> showByGenreAndLang(Long g, Long l, Pageable p){
		
		
		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findAll().stream()
                .filter(f -> f.getGenreList().contains(g))
                .filter(f -> f.getLangue().contains(l))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};

	public List<Replay> showByNameContaining(String n) {

		return rep.findByNameContaining(n);
	}
	
	public Page<Replay> search(String val, Pageable p ) {

		return rep.findByNameContainingOrOverviewContaining(val, val, p);
	}
	
	public Page<Replay> searchByLangue(String val, Long genre, Pageable p ) {

		
		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getLangue().contains(genre))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}

	public Page<Replay> searchByGenre(String val,Long langue, Pageable p ) {

		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getGenreList().contains(langue))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Replay> searchByGenreAndLang(String val , Long g, Long l, Pageable p){
		
		
		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getGenreList().contains(g))
                .filter(f -> f.getLangue().contains(l))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
*/
	public Replay upadte( Long id, Replay g) {

		g.setId(id);

		return rep.save(g);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Replay> showById(Long id) {

		return rep.findById(id);

	}

	public Replay findByName(String name) {
		
		return rep.findByName(name);
	}
	


}
