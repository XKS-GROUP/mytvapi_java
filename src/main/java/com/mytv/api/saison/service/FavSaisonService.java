package com.mytv.api.saison.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.saison.model.FavSaison;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.repository.FavSaisonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavSaisonService {

	@Autowired
	FavSaisonRepository favSaisonRep;
	
	public FavSaison addFav(FavSaison ls) {
		refresh();
		ls.getSaison().setFavorie(true);
		return favSaisonRep.save(ls);
	}
	
	public List<FavSaison> show(){
		refresh();
		return favSaisonRep.findAll();
	}
	
	public Page<FavSaison> showPage(Pageable p){
		refresh();
		return favSaisonRep.findAll(p);
	}
	
	public List<FavSaison> findByUser(FirebaseUser u) {
		refresh();
		return favSaisonRep.findByUser(u);
	}
	
	public List<FavSaison> findBySaison(Saison s) {
		refresh();
		return favSaisonRep.findBySaison(s);
	}
	
	public Long nbretotalLike(Saison s) {
		refresh();
		return (long) favSaisonRep.findBySaison(s).size();
	}
	
	
	public boolean remove(Long id) {
		refresh();
		favSaisonRep.deleteById(id);
		 
		return true;
		
	}
	
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			//Retirer les favories de tous les users
			favSaisonRep.findAll().forEach(
					
					f -> {
						
						f.getSaison().setFavorie(false);
					}
					
				);
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			//Afficher que les favories du l utilisateur actuelle
			favSaisonRep.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getSaison().setFavorie(true);
					}
					
				);
		}
	}

	public Object findByid(Long id) {
		refresh();
		return favSaisonRep.findById(id);
	}

	public Object findByUid(String uid) {
		refresh();
		return favSaisonRep.findByUid(uid);
	}
}
