package com.mytv.api.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.model.FavArticle;
import com.mytv.api.news.repository.FavArticleRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class FavArticleService {

	@Autowired
	FavArticleRepository rep;
	
	public FavArticle addFav(FavArticle fp) {
		refresh();
		fp.getArticle().setFavorie(true);
		return rep.save(fp);
	}
	
	public List<FavArticle> show(){
		refresh();
		return rep.findAll();
	}
	
	public Page<FavArticle> showPage(Pageable p){
		refresh();
		return rep.findAll(p);
	}
	
	public List<FavArticle> findByUser(FirebaseUser u) {
		refresh();
		return rep.findByUser(u) ;
	}
	
	public List<FavArticle> findByArticle(Article p) {
		refresh();
		return rep.findByArticle(p);
	}
	
	
	public boolean remove(Long id) {
		refresh();
		rep.deleteById(id);
		 
		return true;
		
	}
	
	
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			rep.findAll().forEach(
					
					f -> {
						
						f.getArticle().setFavorie(false);
					}
					
				);
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			//Afficher que les favories du l utilisateur actuelle
			rep.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getArticle().setFavorie(true);
						
						System.out.println(" METTRE TOUS LES FAVORIES A TRUE");
					}
					
				);
		}
	}

	public Object findById(Long id) {
		refresh();
		return rep.findById(id);
	}

	public Object findByUid(String uid) {
		refresh();
		return rep.findByUid(uid);
	}
}
