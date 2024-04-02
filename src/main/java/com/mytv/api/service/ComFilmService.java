package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.ComFilm;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.ComFilmRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ComFilmService {

	@Autowired
	ComFilmRepository comfilmRep;
	
	
	public ComFilm addCom(ComFilm cf) {
		
		return comfilmRep.save(cf);
	}
	
	public List<ComFilm> show(){
		
		return comfilmRep.findAll();
	}
	
	public List<ComFilm> findByUser(User u) {
		
		return comfilmRep.findByUser(u) ;
	}
	
	public List<ComFilm> findByFilm(Film f) {
		
		return comfilmRep.findByFilm(f);
	}
	
	public boolean remove(Long id) {
		
		comfilmRep.deleteById(id);
		 
		return true;
		
	}
	
}
