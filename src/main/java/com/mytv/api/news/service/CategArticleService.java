package com.mytv.api.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.news.model.CategArticle;
import com.mytv.api.news.repository.CategArticleRepository;

@Service
public class CategArticleService {
	
	@Autowired
	CategArticleRepository rep;
	
	public CategArticle create(CategArticle cat) {
		
		return rep.save(cat);
	}
	
	public CategArticle update(Long id, CategArticle cat) {
		
		cat.setId(id);
		return rep.save(cat);
	}
	
	public List<CategArticle> show(){
		
		return rep.findAll();
	}

	public Page<CategArticle> showByPage(Pageable p){
		
		return rep.findAll(p);
	}
	
	public void delete(Long id) {
		
		rep.deleteById(id);
	}
}
