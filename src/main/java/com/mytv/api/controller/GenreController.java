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

import com.mytv.api.model.gestMedia.Genre;
import com.mytv.api.service.gestMedia.GenreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/genre")

public class GenreController {
	
	
	
	@Autowired
	private GenreService genreService;
	
	
	@GetMapping("/")
	public List<Genre> show(){
		
		return genreService.show();
	}
	
	
	@GetMapping("/{id}")
	public Optional<Genre> showbyId(@PathVariable Long id){
		
		return genreService.showById(id);
	}
	
	@PutMapping("/update/{id}")
	public Genre update(@PathVariable Long id, @RequestBody Genre u){
		
		return genreService.upadte(id, u);
		
	}
	
	
	@PostMapping(path="/create")
	public Genre create(@RequestBody Genre u) {
		
		return genreService.create(u);
	}
	
	
	
	@DeleteMapping(path="/delete/{id}")
	public Boolean delete (@PathVariable Long id) {
		
		genreService.delete(id);
		
		return true;
	}


}
