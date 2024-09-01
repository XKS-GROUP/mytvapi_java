package com.mytv.api.film.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.film.model.FavFilm;
import com.mytv.api.film.model.Film;
import com.mytv.api.film.repository.FavFilmRepository;
import com.mytv.api.firebase.model.FirebaseUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavFilmService {

	@Autowired
	FavFilmRepository favfilmRep;
	
	
	public FavFilm addFav(FavFilm ff) {
		refresh();
		ff.getFilm().setFavorie(true);
		return favfilmRep.save(ff);
	}
	
	public List<FavFilm> show(){
		refresh();
		return favfilmRep.findAll();
	}
	
	public Page<FavFilm> showPage(Pageable p){
		refresh();
		return favfilmRep.findAll(p);
	}
	
	public List<FavFilm> findByUser(FirebaseUser u) {
		refresh();
		return favfilmRep.findByUser(u) ;
	}
	
	public List<FavFilm> findByFilm(Film f) {
		refresh();
		return favfilmRep.findByFilm(f);
	}
	
	
	public boolean remove(Long id) {
		refresh();
		favfilmRep.deleteById(id);
		 
		return true;
		
	}
	
	@Autowired
	FavFilmRepository rep_fav_film;
	
	public void refresh() {
		
		//Si l user est un abonne
				if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
					
					
					//Retirer les favories de tous les users
					rep_fav_film.findAll().forEach(
							
							f -> {
								
								f.getFilm().setFavorie(false);
							}
							
						);
					
					
					FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
					//Afficher que les favories du l utilisateur actuelle
					rep_fav_film.findByUid(u.getUid()).forEach(
							
							f -> {
								
								f.getFilm().setFavorie(true);
							}
							
						);
				}
	}
	public Object findByid(Long id) {

		refresh();
		return rep_fav_film.findById(id);
	}

	public List<FavFilm> findByUid(String uid) {
		refresh();
		return rep_fav_film.findByUid(uid);
	}
	
}
