package com.mytv.api.radio.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.repository.RadioRepository;
import com.mytv.api.ressource.repository.CategoryLrRepository;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.repository.PaysRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RadioService {


	@Autowired
	private RadioRepository radioRep;
	
	@Autowired
	private PaysRepository rep_pays;
	
	@Autowired
	private CategoryLrRepository rep_categ;
	
	@Autowired
	private LangRepository rep_langue;
	
	public void refresh() {
		
	       List<Radio> l = radioRep.findAll();
			
			l.forEach(  
					
					p -> {
						p.setList_langues(rep_langue.findAllById(p.getLangue()));
						p.setList_categories(rep_categ.findAllById(p.getCategories()));
					}
			);
		}

	public Radio create(Radio g) {

		g.setList_langues(rep_langue.findAllById(g.getLangue()));
		g.setList_country(rep_pays.findAllById(g.getCountry()));
		g.setList_categories(rep_categ.findAllById(g.getCategories()));
		
		return radioRep.save(g);

	}

	public List<Radio> show() {
		refresh();
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
	
	public Page<Radio> showByPays(Long id, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getCountry().contains(id)).toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	public Page<Radio> showByCategAndLang(Long categ, Long langue, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCategories().contains(categ))
				   .toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	public Page<Radio> showByPaysAbdLang(Long pays, Long langue, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getCountry().contains(pays))
				   .filter(f -> f.getCategories().contains(langue))
				   .toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	
	
	
	@SuppressWarnings("unused")
	public Page<Radio> filtre_complet(Long categ, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .toList() 
				   , p
				   , radioRep.findAll().size());
		
		if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll().stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public Page<Radio> filtre_recherche_complet(String val, Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .toList() 
				   , p
				   , radioRep.findAll().size());
		
		
		if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll().size());
		}
		else {
			return res;
		}
		
	};
	
	
	public Page<Radio> showByCategAbdLangAndPays(Long categ, Long langue, Long pays, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCategories().contains(categ))
				   .filter(f -> f.getCountry().contains(pays))
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
	
	public Page<Radio> searchByPays(String n, Long pays, Pageable p) {

		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getCountry().contains(pays))
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
	
	
	public Page<Radio> searchByCategAbdLangAndPays(String val, Long categ, Long langue, Long pays, Pageable p){
		
		PageImpl<Radio> res = new PageImpl<Radio>(
				    radioRep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCategories().contains(categ))
				   .filter(f -> f.getCountry().contains(pays))
				   .toList() 
				   , p
				   , radioRep.findAll().size());
			
			return res;
		
	};
	
	public Radio upadte( Long id, Radio u) {

		u.setIdRadio(id);
		u.setList_langues(rep_langue.findAllById(u.getLangue()));
		u.setList_country(rep_pays.findAllById(u.getCountry()));
		u.setList_categories(rep_categ.findAllById(u.getCategories()));

		return radioRep.save(u);
	}

	public Boolean delete(Long id) {

		refresh();
		radioRep.deleteById(id);

		return null;
	}

	public Optional<Radio> showById( Long id) {
		refresh();
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
	
	public Radio top(){
		
		return radioRep.findByTopTrue().get(0);
	}
	
	public Radio Addtop(Long id, boolean status){
		
		Radio r =  radioRep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
}
