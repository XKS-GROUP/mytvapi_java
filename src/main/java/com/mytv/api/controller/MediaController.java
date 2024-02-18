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

import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.service.gestMedia.LiveTvSetvice;
import com.mytv.api.service.gestMedia.RadioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")

public class MediaController {
	
	@Autowired
	private RadioService radioService;
	
	private LiveTvSetvice liveService;
	
	
	//Radio
	@PostMapping(path="movie/create")
	public Radio create(@RequestBody Radio u) {
		
		return radioService.create(u);
	}
	
	
	@GetMapping("movie/")
	public List<Radio> showM(){
		
		return radioService.show();
	}
	
	
	@GetMapping("movie/{id}")
	public Optional<Radio> showbyIdM(@PathVariable Long id){
		
		return radioService.showById(id);
	}
	
	@PutMapping("movie/update/{id}")
	public Radio update(@PathVariable Long id, @RequestBody Radio u){
		
		return radioService.upadte(id, u);
		
	}
	
	
	@DeleteMapping(path="movie/delete/{id}")
	public Boolean deleteM (@PathVariable Long id) {
		
		radioService.delete(id);
		
		return true;
	}
	
	//ROUTES LiveTV
	
	@PostMapping(path="live/create")
	public LiveTv create(@RequestBody LiveTv u) {
		
		return liveService.create(u);
	}
	
	
	@GetMapping("live/")
	public List<LiveTv> show(){
		
		return liveService.show();
	}
	
	
	@GetMapping("live/{id}")
	public Optional<LiveTv> showbyId(@PathVariable Long id){
		
		return liveService.showById(id);
	}
	
	@PutMapping("live/update/{id}")
	public LiveTv update(@PathVariable Long id, @RequestBody LiveTv u){
		
		return liveService.upadte(id, u);
		
	}
	
	
	@DeleteMapping(path="live/delete/{id}")
	public Boolean delete (@PathVariable Long id) {
		
		liveService.delete(id);
		
		return true;
	}
	
	//Podcast

}
