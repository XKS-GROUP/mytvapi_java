package com.mytv.api.podcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.podcast.model.FavPodcast;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.repository.FavPodcastRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavPodcastService {

	@Autowired
	FavPodcastRepository favpodRep;
	
	
	public FavPodcast addFav(FavPodcast fp) {
		refresh();
		fp.getPodcast().setFavorie(true);
		return favpodRep.save(fp);
	}
	
	public List<FavPodcast> show(){
		refresh();
		return favpodRep.findAll();
	}
	
	public Page<FavPodcast> showPage(Pageable p){
		refresh();
		return favpodRep.findAll(p);
	}
	
	public List<FavPodcast> findByUser(FirebaseUser u) {
		refresh();
		return favpodRep.findByUser(u) ;
	}
	
	public List<FavPodcast> findByPodcast(Podcast p) {
		refresh();
		return favpodRep.findByPodcast(p);
	}
	
	
	public boolean remove(Long id) {
		
		favpodRep.deleteById(id);
		 
		return true;
		
	}
	
	public void refresh() {
		
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			favpodRep.findAll().forEach(
					
					f -> {
						
						f.getPodcast().setFavorie(false);
					}
					
				);
			
			System.out.println("Etape 1");
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println(u.getUid());
			//Afficher que les favories du l utilisateur actuelle
			favpodRep.findByUid(u.getUid()).forEach(
					
					f -> {
						System.out.println("Etape 3");
						f.getPodcast().setFavorie(true);
					}
					
				);
		}
	}
	


	public Object findByid(Long id) {
		refresh();
		return favpodRep.findById(id).get();
	}

	public List<FavPodcast> findByUid(String uid) {
		refresh();
		return favpodRep.findByUid(uid);
	}
	
}
