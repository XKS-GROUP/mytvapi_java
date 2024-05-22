package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Film;
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
	
	public Page<Radio> showPage(Pageable p) {

		return radioRep.findAll(p);
	}
	
	public List<Radio> showByLangue(Long id, Pageable p){
		
		return radioRep.findAll().stream()
                     .filter(f -> f.getLangue().contains(id))
                     .collect(Collectors.toList());
		
	};
	
	public List<Radio> showByCateg(Long id, Pageable p){
		
		return radioRep.findAll().stream()
                     .filter(f -> f.getCategories().contains(id))
                     .collect(Collectors.toList());
		
	};
	
	
	
	public List<Radio> search(String n, Pageable p) {

		return radioRep.findByNameOrOverviewContaining(n, n, p);
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

	public Radio findByName(String name) {
		
		
		return radioRep.findByName(name);
	}

}
