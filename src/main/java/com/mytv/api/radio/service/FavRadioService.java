package com.mytv.api.radio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.radio.model.FavRadio;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.repository.FavRadioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavRadioService {

	@Autowired
	FavRadioRepository favRadioRep;
	
	
	public void refresh() {
		
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			favRadioRep.findAll().forEach(
					
					f -> {
						
						f.getRadio().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			//Afficher que les favories du l utilisateur actuelle
			favRadioRep.findByUid(u.getUid()).forEach(
					
					f -> {
						System.out.println("ICI c est sans soucis");
						f.getRadio().setFavorie(true);
					}
					
				);
		}
	}
	public FavRadio addFav(FavRadio fr) {
		refresh();
		fr.getRadio().setFavorie(true);
		return favRadioRep.save(fr);
	}
	
	public List<FavRadio> show(){
		refresh();
		return favRadioRep.findAll();
	}
	
	public Page<FavRadio> showPage(Pageable p){
		refresh();
		return favRadioRep.findAll(p);
	}
	
	public List<FavRadio> findByUid(String uid) {
		refresh();
		return favRadioRep.findByUid(uid);
	}
	
	public List<FavRadio> findByUser(FirebaseUser u) {
		refresh();
		return favRadioRep.findByUser(u);
	}
	
	public List<FavRadio> findByUser(Radio r) {
		refresh();
		return favRadioRep.findByRadio(r);
	}
	
	
	public boolean remove(Long id) {
		refresh();
		favRadioRep.deleteById(id);
		 
		return true;
		
	}
	
	public Object findByid(Long id) {
		refresh();
		return favRadioRep.findById(id).get();
	}
	
}
