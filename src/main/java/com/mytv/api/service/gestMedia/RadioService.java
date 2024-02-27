package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Radio;
import com.mytv.api.repository.RadioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RadioService {
	
	
	@Autowired
	private RadioRepository radioRep;

	
	public Radio create(Radio g) {
		
		return radioRep.save(g);
		
	}
	
	public List<Radio> show() {
		
		return radioRep.findAll();
	}
	
	public List<Radio> showByNameContaining(String n) {
		
		return radioRep.findByNameContaining(n);
	}
	
	public Radio upadte(final Long id, Radio u) {
		
		Radio old = radioRep.findById(id).get();
		
		old = u;

		old.setIdRadio(id);
		
		return radioRep.save(old);
	}
	
	
	
	public Boolean delete(Long id) {
			
		radioRep.deleteById(id);
		
		return null;
		
	}

	public Optional<Radio> showById(final Long id) {
		
		return radioRep.findById(id);
		
	}

}
