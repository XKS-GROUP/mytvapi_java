package com.mytv.api.intervenant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.model.Actor;
import com.mytv.api.intervenant.repository.ActorRepository;
import com.mytv.api.ressource.repository.PaysRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ActorService {

	@Autowired
	ActorRepository rep;
	
	@Autowired
	PaysRepository rep_pays;
	
	public void refresh() {
		
	  rep.findAll().forEach(
			  
			  p -> p.setList_pays(rep_pays.findAllById(p.getPays()))
			  
			  );
		
		
	}
	
	public Actor create(Actor p) {

		p.setList_pays(rep_pays.findAllById(p.getPays()));
		
		return rep.save(p);

	}

	public List<Actor> show() {
		refresh();
		return rep.findAll();
	}
	
	public Page<Actor> showPage(Pageable p) {
		refresh();
		return rep.findAll(p);
	}
	
	
	public Page<Actor> filtre_complet(Long pays, Pageable p){
		 refresh();
		 PageImpl<Actor> res = new PageImpl<Actor>(rep.findAll().stream()
				   .filter(a -> a.getPays().contains(pays)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Object filtre_recherche_complet(String val, Long pays, Pageable p) {
		refresh();
		
		if(pays==null) {
			
		 PageImpl<Actor> res = new PageImpl<Actor>(rep.findByFistNameContainingAndLastNameContaining(val, val).stream()
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		}
		else {
			
			PageImpl<Actor> res = new PageImpl<Actor>(rep.findByFistNameContainingAndLastNameContaining(val, val).stream()
					   .filter(a -> a.getPays().contains(pays)).toList() 
					   , p
					   , rep.findAll().size());
				
				return res;
		}
	}

	public Actor update(Long id, Actor p) {

		p.setIdActor(id);
		refresh();
		return rep.save(p);
	}
	
	public Optional<Actor> showById(Long id) {
		refresh();
		return rep.findById(id);

	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}


}
