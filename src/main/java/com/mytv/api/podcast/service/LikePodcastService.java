package com.mytv.api.podcast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.podcast.model.LikePodcast;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.repository.LikePodcastRepository;
import com.mytv.api.user.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikePodcastService {

	@Autowired
	LikePodcastRepository likePodRep;
	
	public LikePodcast addLike(LikePodcast lp) {
		
		return likePodRep.save(lp);
	}
	
	public List<LikePodcast> show(){
		
		return likePodRep.findAll();
	}
	
	public Page<LikePodcast> showPage(Pageable p){
		
		return likePodRep.findAll(p);
	}
	
	public Optional<LikePodcast> findByUser(User u) {
		
		return likePodRep.findByUser(u);
	}
	
	public List<LikePodcast> findByPodcast(Podcast p) {
		
		return likePodRep.findByPodcast(p);
	}
	
	public Long nbretotalLike(Podcast p) {
		
		return (long) likePodRep.findByPodcast(p).size();
	}
	
	public boolean removeLike(Long id) {
		
		likePodRep.deleteById(id);
		 
		 return true;
		
	}
	
	public LikePodcast updateByid(Long id, LikePodcast p) {

		LikePodcast old = likePodRep.findById(id).get();
		old = p;
		old.setIdLike(id);

		return likePodRep.save(old);
	}
}
