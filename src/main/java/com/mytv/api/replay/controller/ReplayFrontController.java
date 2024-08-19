package com.mytv.api.replay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/")
@SecurityRequirement(name = "bearerAuth")
public class ReplayFrontController {
	@Autowired
	private CommonfunctionReplay fnc;
	
	/*
     * OPERATION SUR LES REPLAY
     * 
     * */
    
    @Tag(name = "Replay")
	@GetMapping("replay")
	public ResponseEntity<Object> replay_show(){

		return fnc.replay_show();
	}
    
    @Tag(name = "Replay")
	@GetMapping("replay/all/")
	public ResponseEntity<Object> replay_show_paging(Pageable p){

		return fnc.replay_show_paging(p);
	}
    
    @Tag(name = "Replay")
	@GetMapping("replay/search/")
	public ResponseEntity<Object> replay_search(
		@RequestParam String s, 
		Pageable p){

    	return fnc.replay_search(s, p);

    }
    
    @Tag(name = "Replay")
	@GetMapping("replay/{id}")
	public ResponseEntity<Object> replay_get_byid(@PathVariable long id){

		return fnc.replay_get_byid(id);
	}
    
    /*
	 * 
	 * OPERATIONS SUR LES CATEGORIES DE REPLAY
	 * 
	 * 
	 */
    
	@Tag(name = "Replay Categuorie")
	@GetMapping("replay/categ")
	public ResponseEntity<Object> replay_categ_show(){

		return fnc.replay_categ_show();
	}
	
	@Tag(name = "Replay Categuorie")
	@GetMapping("replay/categ/all")
	public ResponseEntity<Object> replay_categ_Paging(Pageable p){

		return fnc.replay_categ_Paging(p);
	}

	@Tag(name = "Replay Categuorie")
	@GetMapping("replay/categ/{id}")
	public ResponseEntity<Object> replay_categ_show_byid(@PathVariable Long id){

		return fnc.replay_categ_show_byid(id);
	}
	
	
	 /*
	 * OPERATION SUR LES COLLECTIONS DE REPLAY
	 * 
	 * */

    @Tag(name = "Replay Collection")
	@GetMapping("replay/collections")
	public ResponseEntity<Object> replay_collection_show(){

		return fnc.replay_collection_show();
	}
    
    @Tag(name = "Replay Collection")
	@GetMapping("replay/collections/all/")
	public ResponseEntity<Object> showCollPaging(Pageable p){

		return fnc.replay_collection_paging(p);
	}
    
    @Tag(name = "Replay Collection")
	@GetMapping("replay/collections/search/")
	public ResponseEntity<Object> searchCollection(
		@RequestParam String s, 
		Pageable p){

    	return fnc.replay_collection_search(s, p);
    }
    
    @Tag(name = "Replay Collection")
	@GetMapping("replay/collections/{id}")
	public ResponseEntity<Object> showCollectionById(@PathVariable long id){

		return fnc.replay_collection_byid(id);
	}

}
