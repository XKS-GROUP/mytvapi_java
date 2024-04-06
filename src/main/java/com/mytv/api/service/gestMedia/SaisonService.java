package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.repository.SeasonRepository;

@Service
public class SaisonService{

	@Autowired
	private SeasonRepository seasRep;


	public Saison create(Saison u) {

		return seasRep.save(u);
		
	}


	public List<Saison> show() {
		return seasRep.findAll();
	}

	public List<Saison> showByNameContaining(String n) {
		return seasRep.findByNameContaining(n);
	}


	public Saison showById(Long id) {
		
		return seasRep.findById(id).get();
	}


	public Saison upadte(Long id, Saison p) {

		p.setIdSaison(id);
		return seasRep.save(p);
		
	}


	public Boolean delete(Long id) {
		seasRep.deleteById(id);
		return null;
	}

}
