package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.FavFilm;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.FavFilmRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavFilmService {

	@Autowired
	FavFilmRepository favfilmRep;
	
	
	public FavFilm addFav(FavFilm ff) {
		
		return favfilmRep.save(ff);
	}
	
	public List<FavFilm> show(){
		
		return favfilmRep.findAll();
	}
	
	public Page<FavFilm> showPage(Pageable p){
		
		return favfilmRep.findAll(p);
	}
	
	public List<FavFilm> findByUser(User u) {
		
		return favfilmRep.findByUser(u) ;
	}
	
	public List<FavFilm> findByFilm(Film f) {
		
		return favfilmRep.findByFilm(f);
	}
	
	
	public boolean remove(Long id) {
		
		favfilmRep.deleteById(id);
		 
		return true;
		
	}
	
}
