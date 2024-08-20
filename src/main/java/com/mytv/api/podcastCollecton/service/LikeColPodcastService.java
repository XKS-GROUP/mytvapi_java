package com.mytv.api.podcastCollecton.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcastCollecton.model.LikeColPod;
import com.mytv.api.podcastCollecton.repository.LikeColPodcastRepository;
import com.mytv.api.user.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LikeColPodcastService {

	@Autowired
	LikeColPodcastRepository rep;
	
	public LikeColPod addLike(LikeColPod lp) {
		
		return rep.save(lp);
	}
	
	public List<LikeColPod> show(){
		
		return rep.findAll();
	}
	
	public Page<LikeColPod> showPage(Pageable p){
		
		return rep.findAll(p);
	}
	
	public Optional<LikeColPod> findByUser(User u) {
		
		return rep.findByUser(u);
	}
	
	public List<LikeColPod> findByPodcast(Podcast p) {
		
		return rep.findByPodcast(p);
	}
	
	public Long nbretotalLike(Podcast p) {
		
		return (long) rep.findByPodcast(p).size();
	}
	
	public boolean removeLike(Long id) {
		
		rep.deleteById(id);
		 
		 return true;
		
	}
	
	public LikeColPod updateByid(Long id, LikeColPod p) {

		p.setIdLike(id);

		return rep.save(p);
	}
}
