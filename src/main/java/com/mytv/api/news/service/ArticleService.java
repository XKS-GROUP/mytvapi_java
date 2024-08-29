package com.mytv.api.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.news.model.Article;
import com.mytv.api.news.repository.ArticleRepository;
import com.mytv.api.news.repository.CategArticleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {

	@Autowired
	ArticleRepository rep;
	
	CategArticleRepository rep_cat;
	
	
	public void refresh() {
		
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
		return rep.save(a);
	}
	
	public Article findByTitle(String title) {
		
		return rep.findByTitle(title);
	}
	
	public Page<Article> search(String s, Pageable p){
		
		return rep.findByTitleContainingOrContentContaining(s, s, p);
	}
	
	public Page<Article> filtre_recherche_complet(String s,List<Long> categ, Pageable p){
		
		PageImpl<Article> res = new PageImpl<Article>(
				rep.findByTitleContainingOrContentContaining(s, s, p).stream()
                   .toList()
				   , p
				   , rep.findAll().size());
		
		
		if(categ != null) {
			
			return res = new PageImpl<Article>(
					 rep.findByTitleContainingOrContentContaining(s, s, p).stream()
	                 .filter(f -> f.getCategories().containsAll(categ)).toList()
					   , p
					   , rep.findAll().size());
		}
		else {

			return res;
		}
		
	}
	
	public Page<Article> filtre_complet(List<Long> categ, Pageable p){
		
		PageImpl<Article> res = new PageImpl<Article>(
				rep.findAll(p).stream()
                   .toList()
				   , p
				   , rep.findAll().size());
		
		if(categ != null) {
			
			return res = new PageImpl<Article>(
					rep.findAll(p).stream()
	                .filter(f -> f.getCategories().containsAll(categ)).toList()
					   , p
					   , rep.findAll().size());
			
		}
		else {

			return res;
		}
		
	}
	
	public Article update(Long id, Article a) {
		
		a.setId(id);
		a.setList_categories(rep_cat.findAllById(a.getCategories()));
		refresh();
		return rep.save(a);
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
