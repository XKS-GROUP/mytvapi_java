package com.mytv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.LikeRadio;
import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.LikeRadioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeRadioService {

	@Autowired
	LikeRadioRepository likeRadioRep;
	
	public LikeRadio addLike(LikeRadio lk) {
		
		return likeRadioRep.save(lk);
	}
	
	public List<LikeRadio> show(){
		
		return likeRadioRep.findAll();
	}
	
	public Page<LikeRadio> showPage(Pageable p){
		
		return likeRadioRep.findAll(p);
	}
	
	public Optional<LikeRadio> findByUser(User u) {
		
		return likeRadioRep.findByUser(u);
	}
	
	public List<LikeRadio> findByUser(Radio r) {
		
		return likeRadioRep.findByRadio(r);
	}
	
	public Long nbretotalLike(Radio r) {
		
		return (long) likeRadioRep.findByRadio(r).size();
	}
	
	public boolean removeLike(Long id) {
		
		 likeRadioRep.deleteById(id);
		 
		 return true;
		
	}
	
	public LikeRadio updateByid(Long id, LikeRadio r) {

		LikeRadio old = likeRadioRep.findById(id).get();
		old = r;
		old.setIdLike(id);

		return likeRadioRep.save(old);
	}
}
