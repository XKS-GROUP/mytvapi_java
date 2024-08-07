package com.mytv.api.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.ressource.Actor;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.repository.ArticleRepository;
import com.mytv.api.news.service.ArticleService;
import com.mytv.api.security.EntityResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class NewsController {

	@Autowired
	ArticleService artService;
	
	
	/*
	 * 
	 * CRUD ARTICLE
	 * 
	 */
	@Tag(name = "Article")
	@GetMapping("articles")
	public ResponseEntity<Object> article_create(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.show());
	}
    
    @Tag(name = "Article")
	@GetMapping("articles/all/")
	public ResponseEntity<Object> article_show_Paging(Pageable p){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.showPage(p));
	}
    
    @Tag(name = "Article")
	@GetMapping("articles/{id}")
	public ResponseEntity<Object> article_show_byid(long id){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.showById(id));
	}
    
    @Tag(name = "Article")
	@PostMapping("articles/create")
	public ResponseEntity<Object> article_create(Article a){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.create(a));
    	
    }
    
    @Tag(name = "Article")
	@PutMapping("articles/update/{id}")
	public ResponseEntity<Object> article_update( Long id, Article a){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, artService.update(id, a));
		
	}
    
    @Tag(name = "Article")
	@DeleteMapping("articles/delete/{id}")
	public ResponseEntity<Object> article_delete( Long id){
    	artService.delete(id);
    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "Article supprimé" );
	}
	
	
}
