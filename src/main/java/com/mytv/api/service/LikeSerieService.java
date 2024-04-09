package com.mytv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.LikeSerie;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.LikeSerieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeSerieService {

	@Autowired
	LikeSerieRepository likeSerieRep;
	
	public LikeSerie addLike(LikeSerie ls) {
		
		return likeSerieRep.save(ls);
	}
	
	public List<LikeSerie> show(){
		
		return likeSerieRep.findAll();
	}
	
	public Page<LikeSerie> showPage(Pageable p){
		
		return likeSerieRep.findAll(p);
	}
	
	public Optional<LikeSerie> findByUser(User u) {
		
		return likeSerieRep.findByUser(u);
	}
	
	public List<LikeSerie> findBySerie(Serie s) {
		
		return likeSerieRep.findBySerie(s);
	}
	
	public Long nbretotalLike(Serie s) {
		
		return (long) likeSerieRep.findBySerie(s).size();
		
	}
	
	public boolean removeLike(Long id) {
		
		likeSerieRep.deleteById(id);
		 
		 return true;
		
	}
	
	public LikeSerie updateByid(Long id, LikeSerie p) {

		LikeSerie old = likeSerieRep.findById(id).get();
		old = p;
		old.setIdLike(id);

		return likeSerieRep.save(old);
	}
}
