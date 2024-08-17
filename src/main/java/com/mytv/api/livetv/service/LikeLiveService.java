package com.mytv.api.livetv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.livetv.model.LikeLivetv;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.repository.LikeLivetvRepository;
import com.mytv.api.user.model.User;

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
	
	public Page<LikeLivetv> showPage(Pageable p){
		
		return likeliveRep.findAll(p);
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
