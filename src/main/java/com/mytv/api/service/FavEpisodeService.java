package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.FavEpisode;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.FavEpisodeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavEpisodeService {

	@Autowired
	FavEpisodeRepository favepisodeRep;
	
	
	public FavEpisode addFav(FavEpisode fe) {
		
		return favepisodeRep.save(fe);
	}
	
	public List<FavEpisode> show(){
		
		return favepisodeRep.findAll();
	}
	
	public Page<FavEpisode> showPage(Pageable p){
		
		return favepisodeRep.findAll(p);
	}
	
	public List<FavEpisode> findByUser(User u) {
		
		return favepisodeRep.findByUser(u) ;
	}
	
	public List<FavEpisode> findByEpisode(Episode ep) {
		
		return favepisodeRep.findByEpisode(ep);
	}
	
	public boolean remove(Long id) {
		
		favepisodeRep.deleteById(id);
		 
		return true;
		
	}
	
}
