package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.ComSaison;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.ComSaisonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ComSaisonService {

	@Autowired
	ComSaisonRepository comsaisonRep;
	
	
	public ComSaison addCom(ComSaison cs) {
		
		return comsaisonRep.save(cs);
	}
	
	public List<ComSaison> show(){
		
		return comsaisonRep.findAll();
	}
	
	public List<ComSaison> findByUser(User u) {
		
		return comsaisonRep.findByUser(u) ;
	}
	
	public List<ComSaison> findBySaison(Saison s) {
		
		return comsaisonRep.findBySaison(s);
	}
	
	
	public boolean remove(Long id) {
		
		comsaisonRep.deleteById(id);
		 
		return true;
		
	}
	
}
