package com.mytv.api.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.config.AlgoliaConfig;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.repository.ArticleRepository;
import com.mytv.api.news.repository.CategArticleRepository;
import com.mytv.api.news.repository.FavArticleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {

	@Autowired
	ArticleRepository rep;
	
	@Autowired
	CategArticleRepository rep_cat;
	
	@Autowired
	FavArticleRepository rep_fav_article;
	
	@Autowired
	private AlgoliaConfig algoClient;
	
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			rep_fav_article.findAll().forEach(
					
					f -> {
						
						f.getArticle().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			//Afficher que les favories du l utilisateur actuelle
			rep_fav_article.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getArticle().setFavorie(true);
					}
					
				);
		}
		
	   //Reaficher les objets categories	
       List<Article> l = rep.findAll();
		
		l.forEach(  
				
				g -> {
					
					g.setList_categories(rep_cat.findAllById(g.getCategories()));
					
				}
			);
		
		}
	
	public Article create(Article a) {
		
		a.setList_categories(rep_cat.findAllById(a.getCategories()));
		refresh();
		
		var resp = algoClient.searchClient().saveObject("article", a);
		
		algoClient.searchClient().waitForTask("article", resp.getTaskID());
		return rep.save(a);
	}
	
	
	public Page<Article> similaire_show(Long id, Pageable p) {
		refresh();
		Article m =  rep.findById(id).get();
		
		PageImpl<Article> res = new PageImpl<Article>(rep.findAll().stream()
				   .filter(rd -> rd.getCategories().containsAll(m.getCategories()))
				   .toList() 
				   , p
				   , rep.findAll().size());
		return res;
	}
	
	public Article findByTitle(String title) {
		refresh();
		return rep.findByTitle(title);
	}
	
	public Page<Article> search(String s, Pageable p){
		refresh();
		return rep.findByTitleContainingOrContentContaining(s, s, p);
	}
	
	public Page<Article> filtre_recherche_complet(String s,Long categ, Pageable p){
		refresh();
		PageImpl<Article> res = new PageImpl<Article>(
				rep.findByTitleContainingOrContentContaining(s, s, p).stream()
                   .toList()
				   , p
				   , rep.findAll(p).stream()
                   .toList().size());
		
		if(s == null || s.isBlank() || s.isEmpty()) {
			
			return filtre_complet(categ, p);
			
		}
		else if(categ != null) {
			
			return res = new PageImpl<Article>(
					 rep.findByTitleContainingOrContentContaining(s, s, p).stream()
	                 .filter(f -> f.getCategories().contains(categ)).toList()
				   , p
				   , rep.findByTitleContainingOrContentContaining(s, s, p).stream()
	                 .filter(f -> f.getCategories().contains(categ)).toList().size());
		}
		else {

			return res;
		}
		
	}
	
	public Page<Article> filtre_recherche_complet_front(String s,Long categ, Pageable p){
		refresh();
		PageImpl<Article> res = new PageImpl<Article>(
				rep.findByTitleContainingOrContentContaining(s, s, p).stream()
				   .filter(f -> f.isStatus())
                   .toList()
				   , p
				   , rep.findAll(p).stream()
				   .filter(f -> f.isStatus())
                   .toList().size());
		
		if(s == null || s.isBlank() || s.isEmpty()) {
			
			return filtre_complet_front(categ, p);
			
		}
		else if(categ != null) {
			
			return res = new PageImpl<Article>(
					 rep.findByTitleContainingOrContentContaining(s, s, p).stream()
	                 .filter(f -> f.getCategories().contains(categ))
	                 .filter(f -> f.isStatus())
	                 .toList()
					   , p
					   , rep.findByTitleContainingOrContentContaining(s, s, p).stream()
		                 .filter(f -> f.getCategories().contains(categ))
		                 .filter(f -> f.isStatus())
		                 .toList().size());
		}
		else {

			return res;
		}
		
	}
	
	public Page<Article> filtre_complet(Long categ, Pageable p){
		refresh();
		PageImpl<Article> res = new PageImpl<Article>(
				rep.findAll(p).stream()
                   .toList()
				   , p
				   , rep.findAll().size());
		
		if(categ != null) {
			
			return res = new PageImpl<Article>(
					rep.findAll(p).stream()
	                .filter(f -> f.getCategories().contains(categ)).toList()
					   , p
					   , rep.findAll().size());
			
		}
		else {

			return res;
		}
		
	}
	
	public Page<Article> filtre_complet_front(Long categ, Pageable p){
		refresh();
		PageImpl<Article> res = new PageImpl<Article>(
				rep.findAll(p).stream()
				.filter(f -> f.isStatus())
                   .toList()
				   , p
				   , rep.findAll(p).stream()
					.filter(f -> f.isStatus())
	                   .toList().size());
		
		if(categ != null) {
			
			return res = new PageImpl<Article>(
					rep.findAll(p).stream()
	                .filter(f -> f.getCategories().contains(categ))
	                .filter(f -> f.isStatus())
	                .toList()
	                
					   , p
					   , rep.findAll(p).stream()
		                .filter(f -> f.getCategories().contains(categ))
		                .filter(f -> f.isStatus())
		                .toList().size());
			
		}
		else {

			return res;
		}
		
	}

	
	public Article update(Long id, Article a) {
		
		a.setId(id);
		a.setList_categories(rep_cat.findAllById(a.getCategories()));
		refresh();
		
		Article ar = rep.save(a);
		algoClient.refreshArticle();
		
		return ar;
	}
	
	public List<Article> show(){
		refresh();
		return rep.findAll();
	}
	
	public Article showById(Long id){

		refresh();
		return rep.findById(id).get();
	}
	
	public Page<Article> showPage(Pageable p){
		refresh();
		return rep.findAll(p);
	}
	
	public Page<Article> searchby(String val, Pageable p){
		refresh();
		return rep.findByTitleContainingOrContentContaining(val, val, p);
	}
	
	public void delete(Long id) {
		refresh();
		rep.deleteById(id);
		
	}
	
}
