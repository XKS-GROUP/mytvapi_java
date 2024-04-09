package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.ComPodcast;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.ComPodcastRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ComPodcastService {

	@Autowired
	ComPodcastRepository compodRep;
	
	
	public ComPodcast addCom(ComPodcast fp) {
		
		return compodRep.save(fp);
	}
	
	public List<ComPodcast> show(){
		
		return compodRep.findAll();
	}
	
	public Page<ComPodcast> showPage(Pageable p){
		
		return compodRep.findAll(p);
	}
	
	public List<ComPodcast> findByUser(User u) {
		
		return compodRep.findByUser(u) ;
	}
	
	public List<ComPodcast> findByPodcast(Podcast p) {
		
		return compodRep.findByPodcast(p);
	}
	
	
	public boolean remove(Long id) {
		
		compodRep.deleteById(id);
		 
		return true;
		
	}
	
}
