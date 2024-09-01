package com.mytv.api.serie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.serie.model.FavSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.serie.repository.FavSerieRepository;
import com.mytv.api.serie.repository.SerieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavSerieService {

	@Autowired
	FavSerieRepository favSerieRep;
	
	@Autowired
	SerieRepository repSerie;
	
	public FavSerie addFav(FavSerie ls) {
		refresh();
		ls.getSerie().setFavorie(true);
		return favSerieRep.save(ls);
	}
	
	public List<FavSerie> show(){
		refresh();
		return favSerieRep.findAll();
	}
	
	public Page<FavSerie> showPage(Pageable p){
		refresh();
		return favSerieRep.findAll(p);
	}
	
	public List<FavSerie> findByUser(FirebaseUser u) {
		refresh();
		return favSerieRep.findByUser(u);
	}
	
	public List<FavSerie> findBySerie(Serie s) {
		refresh();
		return favSerieRep.findBySerie(s);
	}
	
	public List<FavSerie> findBySerieId(Long id) {
		refresh();
		return favSerieRep.findBySerie(repSerie.findById(id).get());
	}
	
	public Long nbretotalLike(Serie s) {
		refresh();
		return (long) favSerieRep.findBySerie(s).size();
	}
	
	public boolean remove(Long id) {
		refresh();
		favSerieRep.deleteById(id);
		 
		return true;
		
	}
	
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			favSerieRep.findAll().forEach(
					
					f -> {
						
						f.getSerie().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			//Afficher que les favories du l utilisateur actuelle
			favSerieRep.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getSerie().setFavorie(true);
					}
					
				);
		}
	}

	public Object findByid(Long id) {
		refresh();
		return favSerieRep.findById(id);
	}

	public List<FavSerie> findByUid(String uid) {
		refresh();
		
		return favSerieRep.findByUid(uid);
	}
}
