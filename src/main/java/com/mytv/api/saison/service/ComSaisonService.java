package com.mytv.api.saison.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.saison.model.ComSaison;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.repository.ComSaisonRepository;
import com.mytv.api.user.model.User;

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
	
	public Page<ComSaison> showPage(Pageable p){
		
		return comsaisonRep.findAll(p);
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
