package com.mytv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.LikeLivetv;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.LikeLivetvRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeLiveService {

	@Autowired
	LikeLivetvRepository likeliveRep;
	
	public LikeLivetv addLike(LikeLivetv l) {
		
		return likeliveRep.save(l);
	}
	
	public List<LikeLivetv> show(){
		
		return likeliveRep.findAll();
	}
	
	public Optional<LikeLivetv> findByUser(User u) {
		
		return likeliveRep.findByUser(u);
	}
	
	public List<LikeLivetv> findByLivetv(LiveTv l) {
		
		return likeliveRep.findByLivetv(l);
	}
	
	public Long nbretotalLike(LiveTv l) {
		
		return (long) likeliveRep.findByLivetv(l).size();
	}
	
	public boolean removeLike(Long id) {
		
		likeliveRep.deleteById(id);
		 
		 return true;
		
	}
	
}
