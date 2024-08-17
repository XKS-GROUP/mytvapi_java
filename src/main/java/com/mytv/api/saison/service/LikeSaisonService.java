package com.mytv.api.saison.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.saison.model.LikeSaison;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.repository.LikeSaisonRepository;
import com.mytv.api.user.model.User;

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
	
	public Page<LikeSaison> showPage(Pageable p){
		
		return likeSaisonRep.findAll(p);
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
