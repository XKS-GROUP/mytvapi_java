package com.mytv.api.replay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.mytv.api.replay.categorie.model.ReplayCateg;
import com.mytv.api.replay.collection.model.ReplayCollection;
import com.mytv.api.replay.replay.model.Replay;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

	@Autowired
	private CommonfunctionReplay fnc;
	
	/*
     * Collection Podcast
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
    
    @Tag(name = "Replay")
	@PostMapping("replay/create")
	public ResponseEntity<Object> replay_create(@Valid @RequestBody Replay r){
    	
    	return fnc.replay_create(r);
    	
	}
    
    @Tag(name = "Replay")
	@PutMapping("replay/update/{id}")
	public ResponseEntity<Object> updateCollection(@PathVariable Long id, @Valid @RequestBody Replay a){
    	
    	return fnc.updateCollection(id, a);
		
	}
        
    @Tag(name = "Replay")
	@DeleteMapping("replay/delete/{id}")
	public ResponseEntity<Object> replay_delete(@PathVariable Long id){

    	return fnc.replay_delete(id);
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

	@Tag(name = "Replay Categuorie")
	@PutMapping("replay/categ/update/{id}")
	public ResponseEntity<Object> replay_categ_update(@PathVariable Long id,@Valid @RequestBody ReplayCateg r){
		
		return fnc.replay_categ_update(id, r);
	}
	
	@Tag(name = "Replay Categuorie")
	@PutMapping("replay/categ/update/status/{id}")
	public ResponseEntity<Object> replay_categ_update_status(@PathVariable Long id, @Valid @RequestBody StatusDTO status){
		
		return fnc.replay_categ_update_status(id, status);

	}

	@Tag(name = "Replay Categuorie")
	@PostMapping("replay/categ/create")
	public ResponseEntity<Object> replay_categ_create(@Valid @RequestBody ReplayCateg u) {
		
		return fnc.replay_categ_create(u);
	}

	
	@Tag(name = "Replay Categuorie")
	@DeleteMapping("replay/categ/delete/{id}")
	public ResponseEntity<Object> replay_categ_delete (@PathVariable Long id) {

		return fnc.replay_categ_delete(id);
	}

	@Tag(name = "Replay Categuorie")
	@GetMapping("replay/categ/search/")
	public ResponseEntity<Object> replay_categ_show_ByName( 
			@RequestParam String s, Pageable p){

		return fnc.replay_categ_show_ByName(s, p);
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
    
    @Tag(name = "Replay Collection")
	@PostMapping("replay/collections/create")
	public ResponseEntity<Object> createCollection(@Valid @RequestBody ReplayCollection r){
    	
    	return fnc.replay_collection_create(r);
	}
    
    @Tag(name = "Replay Collection")
	@PutMapping("replay/collections/update/{id}")
	public ResponseEntity<Object> updateCollection(@PathVariable Long id, @Valid @RequestBody ReplayCollection a){
    	
    	return fnc.replay_collection_update(id, a);
	}
    
    @Tag(name = "Replay Collection")
	@DeleteMapping("replay/collections/delete/{id}")
	public ResponseEntity<Object> deleteCollection(@PathVariable Long id){

    	return fnc.replay_collection_delete(id);
	}
}
