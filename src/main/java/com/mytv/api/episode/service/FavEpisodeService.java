package com.mytv.api.episode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.model.FavEpisode;
import com.mytv.api.episode.repository.FavEpisodeRepository;
import com.mytv.api.firebase.model.FirebaseUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavEpisodeService {

	@Autowired
	FavEpisodeRepository favepisodeRep;
	
	
	public FavEpisode addFav(FavEpisode fe) {
		refresh();
		return favepisodeRep.save(fe);
	}
	
	public List<FavEpisode> show(){
		refresh();
		return favepisodeRep.findAll();
	}
	
	public Page<FavEpisode> showPage(Pageable p){
		refresh();
		return favepisodeRep.findAll(p);
	}
	
	public List<FavEpisode> findByUser(FirebaseUser u) {
		refresh();
		return favepisodeRep.findByUser(u) ;
	}
	
	public List<FavEpisode> findByEpisode(Episode ep) {
		refresh();
		return favepisodeRep.findByEpisode(ep);
	}
	
	public boolean remove(Long id) {
		
		favepisodeRep.deleteById(id);
		 
		return true;
		
	}
	
	public void refresh() {
		
			if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {

				//Retirer les favories de tous les users
				favepisodeRep.findAll().forEach(
							
							f -> {
								
								f.getEpisode().setFavorie(false);
							}
							
						);
					
					
					FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
					System.out.println("Les favories de "+u.getUsername());
					
					//Afficher que les favories du l utilisateur actuelle
					favepisodeRep.findByUid(u.getUid()).forEach(
							
							f -> {
								
								f.getEpisode().setFavorie(true);
							}
							
						);
				}
				
				//Refresh les list d'objet
	}

	public Object findByid(Long id) {
		refresh();
		return favepisodeRep.findById(id);
	}

	public Object findByUid(String uid) {
		
		refresh();
		return favepisodeRep.findByUid(uid);
	}
}
