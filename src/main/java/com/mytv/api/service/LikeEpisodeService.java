package com.mytv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.LikeEpisode;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.LikeEpisodeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeEpisodeService {

	@Autowired
	LikeEpisodeRepository likeepisodeRep;
	
	public LikeEpisode addLike(LikeEpisode le) {
		
		return likeepisodeRep.save(le);
	}
	
	public List<LikeEpisode> show(){
		
		return likeepisodeRep.findAll();
	}
	
	public Optional<LikeEpisode> findByUser(User u) {
		
		return likeepisodeRep.findByUser(u);
	}
	
	public List<LikeEpisode> findByEpisode(Episode ep) {
		
		return likeepisodeRep.findByEpisode(ep);
	}
	
	public Long nbretotalLike(Episode ep) {
		
		return (long) likeepisodeRep.findByEpisode(ep).size();
	}
	
	public boolean removeLike(Long id) {
		
		likeepisodeRep.deleteById(id);
		 
		 return true;
		
	}
	
	public LikeEpisode updateByid(Long id, LikeEpisode p) {

		LikeEpisode old = likeepisodeRep.findById(id).get();
		old = p;
		old.setIdLike(id);

		return likeepisodeRep.save(old);
	}
}
