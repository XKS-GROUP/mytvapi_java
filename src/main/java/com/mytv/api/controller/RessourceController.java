package com.mytv.api.controller;

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

import com.mytv.api.model.gestPub.Publicite;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.gestPub.PubliciteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class RessourceController {

	@Autowired
	PubliciteService pubService;
	
	
	@Tag(name = "Publicité")
	@GetMapping("pub")
	public ResponseEntity<Object> showPub(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.show());
	}
    
	@Tag(name = "Publicité")
	@GetMapping("pub/all/")
	public ResponseEntity<Object> showPubPaging(Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.showPage(p));
		
	}
	
	@Tag(name = "Publicité")
	@GetMapping("pub/search/")
	public ResponseEntity<Object> searchPub(
		@RequestParam String s, 
		Pageable p){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.findByName(s, p));
	}
    
	@Tag(name = "Publicité")
	@GetMapping("pub/{id}")
	public ResponseEntity<Object> showPubById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.showById(id));
	}
    
	
	@Tag(name = "Publicité")
	@PostMapping("pub/create")
	public ResponseEntity<Object> createPub(
			@Valid @RequestBody Publicite p){

    	
		if(pubService.findByName(p.getName()) != null ) {
		
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST, "Cette publicité existe déja");
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, pubService.create(p));
		}
	}
    
	
	@Tag(name = "Publicité")
	@PutMapping("pub/update/{id}")
	public ResponseEntity<Object> updatePub(@PathVariable Long id, @Valid @RequestBody Publicite p){

		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, pubService.update(id, p ));
		
	}
    
	@Tag(name = "Publicité")
	@DeleteMapping("pub/delete/{id}")
	public ResponseEntity<Object> deletePub(@PathVariable Long id){
    	pubService.deleteById(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");
		
	}
    
    
    /*
     * 
     * CRUD acteurs
     * 
     */
    //
	
}
