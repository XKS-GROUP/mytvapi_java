package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;
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
	
	public Page<Saison> showPage(Pageable p) {
		return seasRep.findAll(p);
	}
	
	public List<Saison> showByLangue(Long id, Pageable p){
		
		return seasRep.findAll().stream()
                     .filter(f -> f.getLangue().contains(id))
                     .collect(Collectors.toList());
	};
	
	
	public List<Saison> showBySerie(Serie idSerie) {
		return seasRep.findByIdSerie(idSerie);
	}
	
	public List<Saison> search(String n, Pageable p) {
		return seasRep.findByNameOrOverviewContaining(n, n, p);
	}
	
	public List<Saison> searchByLangue(String n, Long langue, Pageable p) {
		
		return seasRep.findByNameOrOverviewContaining(n, n, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList());
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

	public Saison findByName(String name) {

		return seasRep.findByName(name);
	}

}
