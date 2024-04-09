package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.ComEpisode;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.ComEpisodeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ComEpisodeService {

	@Autowired
	ComEpisodeRepository comepisodeRep;
	
	
	public ComEpisode addCom(ComEpisode fp) {
		
		return comepisodeRep.save(fp);
	}
	
	public List<ComEpisode> show(){
		
		return comepisodeRep.findAll();
	}
	
	public Page<ComEpisode> showPages(Pageable p){
		
		return comepisodeRep.findAll(p);
	}
	
	public List<ComEpisode> findByUser(User u) {
		
		return comepisodeRep.findByUser(u) ;
	}
	
	public List<ComEpisode> findByEpisode(Episode ep) {
		
		return comepisodeRep.findByEpisode(ep);
	}
	
	
	public boolean remove(Long id) {
		
		comepisodeRep.deleteById(id);
		 
		return true;
		
	}
	
}
