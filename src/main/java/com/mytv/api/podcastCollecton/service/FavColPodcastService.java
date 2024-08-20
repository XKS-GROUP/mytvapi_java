package com.mytv.api.podcastCollecton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.podcastCollecton.model.FavColPod;
import com.mytv.api.podcastCollecton.repository.FavColPodcastRepository;
import com.mytv.api.user.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavColPodcastService {

	@Autowired
	FavColPodcastRepository rep;
	
	
	public FavColPod addFav(FavColPod fp) {
		
		return rep.save(fp);
	}
	
	public List<FavColPod> show(){
		
		return rep.findAll();
	}
	
	public Page<FavColPod> showPage(Pageable p){
		
		return rep.findAll(p);
	}
	
	public List<FavColPod> findByUser(User u) {
		
		return rep.findByUser(u) ;
	}
	
	public List<FavColPod> findByPodcast(FavColPod p) {
		
		return rep.findByColpodcast(p);
	}
	
	
	public boolean remove(Long id) {
		
		rep.deleteById(id);
		 
		return true;
		
	}
}
