package com.mytv.api.replay.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mytv.api.dto.StatusDTO;
import com.mytv.api.replay.categorie.model.ReplayCateg;
import com.mytv.api.replay.categorie.service.ReplayCategService;
import com.mytv.api.replay.collection.model.ReplayCollection;
import com.mytv.api.replay.collection.service.ReplayCollectionService;
import com.mytv.api.replay.replay.model.Replay;
import com.mytv.api.replay.replay.service.ReplayService;
import com.mytv.api.security.request.EntityResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommonfunctionReplay {

	@Autowired
	ReplayService replay_service;
	
	
	@Autowired
	ReplayCategService replay_categ_service;
	
	@Autowired
	ReplayCollectionService replay_collection_service;
	/*
	 * Operations sur les Replays
	 * 
	 */

	public ResponseEntity<Object> replay_show() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_service.show());
	}
	

	public ResponseEntity<Object> replay_show_paging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_service.showPages(p));
	}

	
	public ResponseEntity<Object> replay_search(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				replay_service.search(s, p));
	}
	

	public ResponseEntity<Object> replay_get_byid(long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_service.showById(id));
	}
	

	public ResponseEntity<Object> replay_create(Replay r) {

		String nom = r.getName().toString() ;

		if (replay_service.findByName(nom) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Cette valeur existe déja"));
			
		} else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, replay_service.create(r));
		}
	}
	

	public ResponseEntity<Object> updateCollection(Long id, Replay a) {

		a.setId(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_service.create(a));

	}

	public ResponseEntity<Object> replay_delete(Long id) {
		replay_service.delete(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");

	}

	
	/*
	 * 
	 * OPERATIONS SUR LES CATEGORIES DE REPLAY
	 * 
	 * 
	 */
    
	public ResponseEntity<Object> replay_categ_show() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_categ_service.show());
	}

	public ResponseEntity<Object> replay_categ_Paging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,

				replay_categ_service.showPage(p));
	}

	public ResponseEntity<Object> replay_categ_show_byid(Long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_categ_service.showById(id));
	}

	public ResponseEntity<Object> replay_categ_update(Long id, ReplayCateg r) {
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_categ_service.upadte(id, r));

	}

	public ResponseEntity<Object> replay_categ_update_status(Long id, StatusDTO status) {

		ReplayCateg l = replay_categ_service.showById(id).get();
		l.setStatus(status.getStatus());

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_categ_service.upadte(id, l));

	}

	public ResponseEntity<Object> replay_categ_create(ReplayCateg u) {

		
		if (replay_categ_service.showByName(u.getName()) != null) {

			return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST, Map.of("name","Cette valeur existe déja"));
		}
		else {

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, replay_categ_service.create(u));
		}
	}

	public ResponseEntity<Object> replay_categ_delete(Long id) {

		replay_categ_service.delete(id);

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, true);
	}

	public ResponseEntity<Object> replay_categ_show_ByName(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_categ_service.showByName(s, p));
	}


	/*
	 * OPERATION SUR LES COLLECTIONS DE REPLAYS
	 * 
	 */

	public ResponseEntity<Object> replay_collection_show(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_collection_service.show());
	}

	public ResponseEntity<Object> replay_collection_paging(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_collection_service.showPage(p));
	}

	public ResponseEntity<Object> replay_collection_search(String s, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,
				replay_collection_service.findByNameOrOverviewContaining(s, s, p));
	}

	public ResponseEntity<Object> replay_collection_byid(long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_collection_service.showById(id));
	}

	public ResponseEntity<Object> replay_collection_create(ReplayCollection r) {

		String nom = r.getName().toString() ;

		if (replay_collection_service.showByName(nom) != null) {

			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, Map.of("name", "Cette collection existe déja"));
			
		} else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, replay_collection_service.create(r));
		}
	}

	public ResponseEntity<Object> replay_collection_update(Long id, ReplayCollection a) {

		a.setId(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, replay_collection_service.create(a));

	}


	public ResponseEntity<Object> replay_collection_delete(Long id) {
		replay_collection_service.delete(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");

	}

}
