package com.mytv.api.replay.replay.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.replay.categorie.repository.ReplayCategRepository;
import com.mytv.api.replay.replay.model.Replay;
import com.mytv.api.replay.replay.repository.ReplayRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ReplayService {

	@Autowired
	private ReplayRepository rep;


	@Autowired
	ReplayCategRepository rep_categ;
	
	public Replay create(Replay r) {

		return rep.save(r);

	}


	public List<Replay> show() {

		return rep.findAll();
	}

	public Page<Replay> showPages(Pageable p) {

		return rep.findAll(p);
	}

	public Page<Replay> showByLangueList (List<Long> ids, Pageable p){
		
		PageImpl<Replay> res = new PageImpl<Replay>(rep.findAll().stream()
				   .filter(
						   f -> f.getIdLangues().containsAll(ids)
						   ).toList()
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Replay> showByCategorie(List<Long> ids, Pageable p){
		
		PageImpl<Replay> res = new PageImpl<Replay>(rep.findAll().stream()
				   .filter(f -> f.getIdCategories().containsAll(ids)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Replay> showByCtegAndLang(List<Long> c, List<Long> l, Pageable p){
		
		
		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findAll().stream()
                .filter(f -> f.getIdCategories().containsAll(c))
                .filter(f -> f.getIdLangues().containsAll(l))
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
	
	public Page<Replay> searchByLangue(String val, List<Long> categs, Pageable p ) {

		
		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getIdLangues().containsAll(categs))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}

	public Page<Replay> searchByGenre(String val,List<Long> langues, Pageable p ) {

		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getIdCategories().containsAll(langues))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Replay> searchByGenreAndLang(String val , List<Long> c, List<Long> l, Pageable p){
		
		
		PageImpl<Replay> res = new PageImpl<Replay>(
				rep.findByNameContainingOrOverviewContaining(val, val).stream()
                .filter(f -> f.getIdCategories().containsAll(c))
                .filter(f -> f.getIdLangues().containsAll(l))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};

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
