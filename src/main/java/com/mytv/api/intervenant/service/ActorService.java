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

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ActorService {

	@Autowired
	ActorRepository rep;
	
	
	public Actor create(Actor p) {

		return rep.save(p);

	}

	public List<Actor> show() {

		return rep.findAll();
	}
	
	public Page<Actor> showPage(Pageable p) {

		return rep.findAll(p);
	}
	
	
	public Page<Actor> filtre_complet(List<Long> pays, Pageable p){
		
		 PageImpl<Actor> res = new PageImpl<Actor>(rep.findAll().stream()
				   .filter(a -> a.getPays().containsAll(pays)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Object filtre_recherche_complet(String val, List<Long> pays, Pageable p) {
		
		 PageImpl<Actor> res = new PageImpl<Actor>(rep.findByFistNameContainingAndLastNameContaining(val, val)  .stream()
				   .filter(a -> a.getPays().containsAll(pays)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
	}

	public Actor update(Long id, Actor p) {

		p.setIdActor(id);

		return rep.save(p);
	}
	
	public Optional<Actor> showById(Long id) {

		return rep.findById(id);

	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}


}
