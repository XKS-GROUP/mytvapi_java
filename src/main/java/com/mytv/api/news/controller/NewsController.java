package com.mytv.api.news.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.dto.StatusDTO;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.model.CategArticle;
import com.mytv.api.news.service.ArticleService;
import com.mytv.api.news.service.CategArticleService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.service.CommonFunction;

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
	
	@Autowired
	CategArticleService catArtService;
	
	@Autowired
	CommonFunction fnc;
	
	/*
	 * 
	 * ROUTES CATEG ARTICLE
	 * 
	 * 
	 * 
	 */
	
	
	
	@Tag(name = "Categorie Article")
	@GetMapping("articles/categ")
	public ResponseEntity<Object> cat_article_show(){

		return fnc.cat_article_show();
	}
    
    @Tag(name = "Categorie Article")
	@GetMapping("articles/categ/all/")
	public ResponseEntity<Object> cat_article_show_Paging(Pageable p){

    	return fnc.cat_article_show_Paging(p);
	}
    
    @Tag(name = "Categorie Article")
	@GetMapping("articles/categ/{id}")
	public ResponseEntity<Object> cat_article_show_byid(@PathVariable long id){

    	return fnc.cat_article_show_byid(id);
	}
    
    @Tag(name = "Categorie Article")
	@PostMapping("articles/categ/create")
	public ResponseEntity<Object> cat_article_create(@Valid @RequestBody CategArticle ca){

    	return fnc.cat_article_create(ca);
    	
    }
    
    @Tag(name = "Categorie Article")
	@PutMapping("articles/categ/update/{id}")
	public ResponseEntity<Object> cat_article_update(@PathVariable Long id, @Valid @RequestBody CategArticle a){

    	return fnc.cat_article_update(id, a);
	}
    
    @Tag(name = "Categorie Article")
    @PutMapping("articles/categ/update/status/{id}")
	public ResponseEntity<Object> cat_article_update_status(@PathVariable Long id,
			@Valid @RequestBody StatusDTO status) {

		return fnc.cat_article_update_status(id, status);

	}
    
    @Tag(name = "Categorie Article")
	@DeleteMapping("articles/categ/delete/{id}")
	public ResponseEntity<Object> cat_article_delete(@PathVariable Long id){
    	catArtService.delete(id);
    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "Categorie supprimé" );
	}
	
	
	/*
	 * 
	 * ROUTES ARTICLES
	 * 
	 * 
	 * 
	 */
    
    
	@Tag(name = "Article")
	@GetMapping("articles")
	public ResponseEntity<Object> article_show(){

		return fnc.article_show();
	}
    
    @Tag(name = "Article")
	@GetMapping("articles/all/")
	public ResponseEntity<Object> article_show_Paging(Pageable p){

    	return fnc.article_show_Paging(p);
	}
    
    @Tag(name = "Article")
	@GetMapping("articles/{id}")
	public ResponseEntity<Object> article_show_byid(@PathVariable long id){

    	return fnc.article_show_byid(id);
	}
    
    @Tag(name = "Article")
	@PostMapping("articles/create")
	public ResponseEntity<Object> article_create(@Valid @RequestBody Article a){

    	return fnc.article_create(a);
    	
    }
    
	@Tag(name = "Article")
	@PutMapping(path="articles/update/status/{id}")
	public ResponseEntity<Object> updateStatusR(@PathVariable Long id,
			@Valid @RequestBody StatusDTO status) {

		return fnc.article_update_status(id, status);

	}
	
    
    @Tag(name = "Article")
	@PutMapping("articles/update/{id}")
	public ResponseEntity<Object> article_update(@PathVariable Long id, @Valid @RequestBody Article a){

    	return fnc.article_update(id, a);
		
	}
    
    
    @Tag(name = "Article")
	@GetMapping("articles/search/")
	public ResponseEntity<Object> showbyNameContain(
			@RequestParam String s,
			Pageable p,
			@RequestParam (required = false) List<Long> categ
			){
		
		return fnc.article_search_filtre(s, categ, p);
	}
    
    
    @Tag(name = "Article")
	@DeleteMapping("articles/delete/{id}")
	public ResponseEntity<Object> article_delete(@PathVariable Long id){
    	artService.delete(id);
    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "Article supprimé" );
	}
	
	
}
