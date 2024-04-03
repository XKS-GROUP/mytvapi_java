package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.FavSerie;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.FavSerieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavSerieService {

	@Autowired
	FavSerieRepository favSerieRep;
	
	public FavSerie addFav(FavSerie ls) {
		
		return favSerieRep.save(ls);
	}
	
	public List<FavSerie> show(){
		
		return favSerieRep.findAll();
	}
	
	public List<FavSerie> findByUser(User u) {
		
		return favSerieRep.findByUser(u);
	}
	
	public List<FavSerie> findBySerie(Serie s) {
		
		return favSerieRep.findBySerie(s);
	}
	
	public Long nbretotalLike(Serie s) {
		
		return (long) favSerieRep.findBySerie(s).size();
	}
	
	
	public boolean remove(Long id) {
		
		favSerieRep.deleteById(id);
		 
		return true;
		
	}
}
