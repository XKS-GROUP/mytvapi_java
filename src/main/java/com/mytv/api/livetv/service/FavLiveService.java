package com.mytv.api.livetv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.livetv.model.FavLiveTv;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.livetv.repository.FavLivetvRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavLiveService {

	@Autowired
	FavLivetvRepository favLiveRep;
	
	
	public FavLiveTv addFav(FavLiveTv fl) {
		refresh();
		return favLiveRep.save(fl);
	}
	
	public List<FavLiveTv> show(){
		refresh();
		return favLiveRep.findAll();
	}
	
	public Page<FavLiveTv> showPage(Pageable p){
		refresh();
		return favLiveRep.findAll(p);
	}
	
	public List<FavLiveTv> findByUser(FirebaseUser u) {
		refresh();
		return favLiveRep.findByUser(u) ;
	}
	
	public List<FavLiveTv> findByLivetv(LiveTv l) {
		refresh();
		return favLiveRep.findByLivetv(l);
	}
	
	public boolean remove(Long id) {
		
		favLiveRep.deleteById(id);
		 
		return true;
		
	}
	
	public void refresh() {
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			favLiveRep.findAll().forEach(
					
					f -> {
						
						f.getLivetv().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			
			//Afficher que les favories du l utilisateur actuelle
			favLiveRep.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getLivetv().setFavorie(true);
					}
					
				);
		}

	
	}

	public Object findByid(Long id) {
		refresh();
		return favLiveRep.findById(id);
	}

	public List<FavLiveTv> findByUid(String uid) {
		
		refresh();
		return favLiveRep.findByUid(uid);
	}
	
}
