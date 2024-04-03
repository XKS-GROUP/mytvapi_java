package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.ComSerie;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.ComSerieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ComSerieService {

	@Autowired
	ComSerieRepository comserieRep;
	
	
	public ComSerie addCom(ComSerie cf) {
		
		return comserieRep.save(cf);
	}
	
	public List<ComSerie> show(){
		
		return comserieRep.findAll();
	}
	
	public List<ComSerie> findByUser(User u) {
		
		return comserieRep.findByUser(u) ;
	}
	
	public List<ComSerie> findBySerie(Serie s) {
		
		return comserieRep.findBySerie(s);
	}
	
	
	public boolean remove(Long id) {
		
		comserieRep.deleteById(id);
		 
		return true;
		
	}
	
}
