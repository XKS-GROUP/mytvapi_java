package com.mytv.api.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		
		return rep.save(fp);
	}
	
	public List<FavArticle> show(){
		
		return rep.findAll();
	}
	
	public Page<FavArticle> showPage(Pageable p){
		
		return rep.findAll(p);
	}
	
	public List<FavArticle> findByUser(FirebaseUser u) {
		
		return rep.findByUser(u) ;
	}
	
	public List<FavArticle> findByArticle(Article p) {
		
		return rep.findByArticle(p);
	}
	
	
	public boolean remove(Long id) {
		
		rep.deleteById(id);
		 
		return true;
		
	}
	
}
