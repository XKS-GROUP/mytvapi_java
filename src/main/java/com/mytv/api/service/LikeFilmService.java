package com.mytv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.LikeFilm;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.LikeFilmRepository;

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
