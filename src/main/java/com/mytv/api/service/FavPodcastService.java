package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.FavPodcast;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.FavPodcastRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavPodcastService {

	@Autowired
	FavPodcastRepository favpodRep;
	
	
	public FavPodcast addFav(FavPodcast fp) {
		
		return favpodRep.save(fp);
	}
	
	public List<FavPodcast> show(){
		
		return favpodRep.findAll();
	}
	
	public Page<FavPodcast> showPage(Pageable p){
		
		return favpodRep.findAll(p);
	}
	
	public List<FavPodcast> findByUser(User u) {
		
		return favpodRep.findByUser(u) ;
	}
	
	public List<FavPodcast> findByPodcast(Podcast p) {
		
		return favpodRep.findByPodcast(p);
	}
	
	
	public boolean remove(Long id) {
		
		favpodRep.deleteById(id);
		 
		return true;
		
	}
	
}
