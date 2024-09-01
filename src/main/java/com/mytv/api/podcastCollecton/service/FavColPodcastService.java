package com.mytv.api.podcastCollecton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
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
		refresh();
		fp.getColpodcast().setFavorie(true);
		return rep.save(fp);
	}
	
	public List<FavColPod> show(){
		refresh();
		return rep.findAll();
	}
	
	public Page<FavColPod> showPage(Pageable p){
		refresh();
		return rep.findAll(p);
	}
	
	public List<FavColPod> findByUser(User u) {
		refresh();
		return rep.findByUser(u) ;
	}
	
	public List<FavColPod> findByPodcast(FavColPod p) {
		refresh();
		return rep.findByColpodcast(p);
	}
	
	
	public boolean remove(Long id) {
		
		rep.deleteById(id);
		 
		return true;
		
	}
	
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			rep.findAll().forEach(
					
					f -> {
						
						f.getColpodcast().setFavorie(false);
					}
					
				);
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			//Afficher que les favories du l utilisateur actuelle
			rep.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getColpodcast().setFavorie(true);
					}
					
				);
		}
	}

	public Object findByid(Long id) {
		refresh();
		return rep.findById(id);
	}

	public Object findByUid(String uid) {
		refresh();
		return rep.findByUid(uid);
	}
}
