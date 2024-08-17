package com.mytv.api.film.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.film.model.ComFilm;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.repository.ComFilmRepository;
import com.mytv.api.user.model.User;

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
	
    public Page<ComFilm> showPages(Pageable p){
		
		return comfilmRep.findAll(p);
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
