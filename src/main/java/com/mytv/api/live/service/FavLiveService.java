package com.mytv.api.live.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.livetv.model.FavLiveTv;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.repository.FavLiveRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavLiveService {

	@Autowired
	FavLiveRepository favLiveRep;
	
	
	public FavLiveTv addFav(FavLiveTv fl) {
		
		return favLiveRep.save(fl);
	}
	
	public List<FavLiveTv> show(){
		
		return favLiveRep.findAll();
	}
	
	public Page<FavLiveTv> showPage(Pageable p){
		
		return favLiveRep.findAll(p);
	}
	
	public List<FavLiveTv> findByUser(FirebaseUser u) {
		
		return favLiveRep.findByUser(u) ;
	}
	
	public List<FavLiveTv> findByLivetv(LiveTv l) {
		
		return favLiveRep.findByLivetv(l);
	}
	
	public boolean remove(Long id) {
		
		favLiveRep.deleteById(id);
		 
		return true;
		
	}
	
}
