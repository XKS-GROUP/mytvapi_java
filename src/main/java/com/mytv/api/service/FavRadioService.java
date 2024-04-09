package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.FavRadio;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.FavRadioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavRadioService {

	@Autowired
	FavRadioRepository favRadioRep;
	
	
	public FavRadio addFav(FavRadio fr) {
		
		return favRadioRep.save(fr);
	}
	
	public List<FavRadio> show(){
		
		return favRadioRep.findAll();
	}
	
	public Page<FavRadio> showPage(Pageable p){
		
		return favRadioRep.findAll(p);
	}
	
	public List<FavRadio> findByUser(User u) {
		
		return favRadioRep.findByUser(u) ;
	}
	
	public List<FavRadio> findByUser(Radio r) {
		
		return favRadioRep.findByRadio(r);
	}
	
	
	public boolean remove(Long id) {
		
		favRadioRep.deleteById(id);
		 
		return true;
		
	}
	
}
