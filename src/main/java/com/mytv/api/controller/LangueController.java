package com.mytv.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.gestMedia.Language;
import com.mytv.api.service.gestMedia.LangueService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/langue")

public class LangueController {
	
	
	
	@Autowired
	private LangueService langService;
	
	
	@GetMapping("/")
	public List<Language> show(){
		
		return langService.show();
	}
	
	
	@GetMapping("/{id}")
	public Optional<Language> showbyId(@PathVariable Long id){
		
		return langService.showById(id);
	}
	
	@PutMapping("/update/{id}")
	public Language update(@PathVariable Long id, @RequestBody Language u){
		
		return langService.upadte(id, u);
		
	}
	
	
	@PostMapping(path="/create")
	public Language create(@RequestBody Language u) {
		
		return langService.create(u);
	}
	
	
	
	@DeleteMapping(path="/delete/{id}")
	public Boolean delete (@PathVariable Long id) {
		
		langService.delete(id);
		
		return true;
	}


}
