package com.mytv.api.serie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.serie.model.ComSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.repository.ComSerieRepository;
import com.mytv.api.user.model.User;

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
	public Page<ComSerie> showPage(Pageable p){
		
		return comserieRep.findAll(p);
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
