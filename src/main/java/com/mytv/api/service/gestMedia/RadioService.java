package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Radio> showPage(Pageable p) {

		return radioRep.findAll(p);
	}
	
	public Page<Radio> showByLangue(Long id, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	public Page<Radio> showByCateg(Long id, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getCategories().contains(id)).toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	public Page<Radio> showByCategAbdLang(Long categ, Long langue, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCategories().contains(categ))
				   .toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	public Page<Radio> search(String n, Pageable p) {

		return radioRep.findByNameContainingOrOverviewContaining(n, n, p);
	}
	
	public Page<Radio> searchByLangue(String n, Long langue ,Pageable p) {

		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList()) 
				   , p
				   , radioRep.findAll().size());
			
			return res;
	}
	
	public Page<Radio> searchByCateg(String n, Long categ, Pageable p) {

		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getCategories().contains(categ))
                .collect(Collectors.toList()) 
				   , p
				   , radioRep.findAll().size());
			
			return res;
	}

	public Page<Radio> searchByCategAbdLang(String val, Long categ, Long langue, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(
				    radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCategories().contains(categ))
				   .toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
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

	public List<Radio> top10(){
		
		return radioRep.findByTop10True();
	}
	
	public Radio Addtop10(Long id, boolean status){
		
		Radio r =  radioRep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
}
