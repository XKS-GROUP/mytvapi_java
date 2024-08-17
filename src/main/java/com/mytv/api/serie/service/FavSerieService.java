package com.mytv.api.serie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.serie.model.FavSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.repository.FavSerieRepository;
import com.mytv.api.user.model.User;

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
	
	public Page<FavSerie> showPage(Pageable p){
		 
		return favSerieRep.findAll(p);
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
