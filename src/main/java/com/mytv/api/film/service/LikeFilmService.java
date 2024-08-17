package com.mytv.api.film.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.film.model.Film;
import com.mytv.api.film.model.LikeFilm;
import com.mytv.api.film.repository.LikeFilmRepository;
import com.mytv.api.user.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeFilmService {

	@Autowired
	LikeFilmRepository likeFilmRep;
	
	public LikeFilm addLike(LikeFilm lf) {
		
		return likeFilmRep.save(lf);
	}
	
	public List<LikeFilm> show(){
		
		return likeFilmRep.findAll();
	}
	
	public Page<LikeFilm> showPage(Pageable p){
		
		return likeFilmRep.findAll(p);
	}
	
	public Optional<LikeFilm> findByUser(User u) {
		
		return likeFilmRep.findByUser(u);
	}
	
	public List<LikeFilm> findByFilm(Film f) {
		
		return likeFilmRep.findByFilm(f);
	}
	
	public Long nbretotalLike(Film f) {
		
		return (long) likeFilmRep.findByFilm(f).size();
	}
	
	public boolean removeLike(Long id) {
		
		likeFilmRep.deleteById(id);
		 
		 return true;
		
	}
	
	public LikeFilm updateByid(Long id, LikeFilm p) {

		LikeFilm old = likeFilmRep.findById(id).get();
		old = p;
		old.setIdLike(id);

		return likeFilmRep.save(old);
	}
}
