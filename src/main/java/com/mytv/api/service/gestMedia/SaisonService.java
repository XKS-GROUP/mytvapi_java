package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.repository.SeasonRepository;

@Service
public class SaisonService{

	@Autowired
	private SeasonRepository seasRep;


	public Saison create(Saison u) {
		
		u.setSerieRef(u.getIdSerie().getIdSerie());
		
		return seasRep.save(u);
		
	}

	public List<Saison> show() {
		return seasRep.findAll();
	}
	
	public Page<Saison> showPage(Pageable p) {
		return seasRep.findAll(p);
	}
	
	public Page<Saison> showByLangue(Long id, Pageable p){
		
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   
				   ,seasRep.findAll().size());
			
			return res;
	};
	
	
	public Page<Saison> showBySerie(Serie idSerie, Pageable p) {
		return seasRep.findByIdSerie(idSerie, p);
	}
	
	public List<Saison> search(String n, Pageable p) {
		return seasRep.findByNameOrOverviewContaining(n, n, p);
	}
	
	public Page<Saison> searchByLangue(String n, Long langue, Pageable p) {
		
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameOrOverviewContaining(n, n, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList()) 
				   , p
				   ,seasRep.findAll().size());
			
			return res;
	}


	public Saison showById(Long id) {
		
		return seasRep.findById(id).get();
	}


	public Saison upadte(Long id, Saison p) {

		p.setIdSaison(id);
	    p.setSerieRef(p.getIdSerie().getIdSerie());
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
