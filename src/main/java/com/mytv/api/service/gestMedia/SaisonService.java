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
		// TODO Auto-generated method stub
		return seasRep.findAll();
	}

	
	public List<Saison> showById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Saison upadte(Long id, Saison p) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean delete(Long id) {
		seasRep.deleteById(id);
		return null;
	}

}
