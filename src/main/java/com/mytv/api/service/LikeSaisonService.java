package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.LikeSaison;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.LikeSaisonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeSaisonService {

	@Autowired
	LikeSaisonRepository likeSaisonRep;
	
	public LikeSaison addLike(LikeSaison ls) {
		
		return likeSaisonRep.save(ls);
	}
	
	public List<LikeSaison> show(){
		
		return likeSaisonRep.findAll();
	}
	
	public List<LikeSaison> findByUser(User u) {
		
		return likeSaisonRep.findByUser(u);
	}
	
	public List<LikeSaison> findBySaison(Saison s) {
		
		return likeSaisonRep.findBySaison(s);
	}
	
	public Long nbretotalLike(Saison s) {
		
		return (long) likeSaisonRep.findBySaison(s).size();
		
	}
	
	public boolean removeLike(Long id) {
		
		likeSaisonRep.deleteById(id);
		 
		 return true;
		
	}
	
}
