package com.mytv.api.radio.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.config.AlgoliaConfig;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.radio.repository.FavRadioRepository;
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
	private FavRadioRepository rep_fav_radio;
	
	@Autowired
	private PaysRepository rep_pays;
	
	@Autowired
	private CategoryLrRepository rep_categ;
	
	@Autowired
	private LangRepository rep_langue;
	
	@Autowired
	private AlgoliaConfig algoClient;
	
	
	
	public void refresh() {
		
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			rep_fav_radio.findAll().forEach(
					
					f -> {
						
						f.getRadio().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			//Afficher que les favories du l utilisateur actuelle
			rep_fav_radio.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getRadio().setFavorie(true);
					}
					
				);
		}
		
		
		//Refresh les list d'objet
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
		
		
		var resp = algoClient.searchClient().saveObject("radio", g);
		
		algoClient.searchClient().waitForTask("radio", resp.getTaskID());
		
		return radioRep.save(g);

	}

	public List<Radio> show() {
		refresh();
		
		return radioRep.findAll();
		
	}
	
	public List<Radio> show_front() {
		refresh();
		
		return radioRep.findAll().stream().filter(
				f-> f.isStatus()
				).toList() ;
		
	}
	
	
	public Page<Radio> similaire_show(Long id, Pageable p) {
		refresh();
		Radio r =  radioRep.findById(id).get();
		
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll().stream()
				   .filter(rd -> rd.getCategories().containsAll(r.getCategories()))
				   .toList()
				   , p
				   , radioRep.findAll().stream()
				   .filter(rd -> rd.getCategories().containsAll(r.getCategories()))
				   .toList().size());
		
		return res;
	}
	
	@SuppressWarnings("unused")
	public Page<Radio> filtre_complet(Long categ, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll(p).stream()
				   .toList() 
				   , p
				   , radioRep.findAll().size());
		
		if(categ != null && langue == null && pays == null) {
			System.out.println("radio dedans"+radioRep.findAll(p).stream().filter(f -> f.getCategories().contains(categ))
					   .toList());
		  
			int z = radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .toList().size();
			System.out.println("radio dedans = "+z +"et categ =" + categ);
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .toList()
					   , p
					   , z
				  );
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public Page<Radio> filtre_complet_front(Long categ, Long langue, Long pays, Pageable p){
		
		refresh();
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll(p).stream()
				   .filter(f-> f.isStatus())
				   .toList() 
				   , p
				   , radioRep.findAll().stream()
				     .filter(f-> f.isStatus()).toList().size());
		
		if(categ != null && langue == null && pays == null) {
		  
			int z = radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f-> f.isStatus())
					   .toList().size();
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , z
				  );
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findAll(p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else {
			return res;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public Page<Radio> filtre_recherche_complet(String val, Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll(p).stream()
				   .toList() 
				   , p
				   , radioRep.findAll().stream()
				   .toList().size());
		
		if (val ==null || val.isEmpty() || val.isBlank()) {
			
		  return  res = new PageImpl<Radio>(radioRep.findAll(p).stream()
					   .toList() 
					   , p
					   , radioRep.findAll().stream()
					   .toList().size());
		}
		if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .toList().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .toList().size());
		}
		else {
			return res ;
		}
		
	};
	
	
	@SuppressWarnings("unused")
	public Page<Radio> filtre_recherche_complet_front(String val, Long categ, Long langue, Long pays, Pageable p){
		refresh();
		PageImpl<Radio> res = new PageImpl<Radio>(radioRep.findAll(p).stream()
				   .toList() 
				   , p
				   , radioRep.findAll().stream()
				   .toList().size());
		
		if (val ==null || val.isEmpty() || val.isBlank()) {
			
			  return  res = new PageImpl<Radio>(radioRep.findAll(p).stream()
						   .toList() 
						   , p
						   , radioRep.findAll().stream()
						   .toList().size());
			}
		if(categ != null && langue == null && pays == null) {
			
		  return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else if(langue != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
			
			
		}
		else if(pays != null && categ == null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && langue != null && pays == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
			
		}
		else if(categ != null && pays != null && langue == null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else if(pays != null && langue != null && categ == null) {
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else if (pays != null && langue != null && categ != null) {
			
			return res = new PageImpl<Radio>(radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList()
					   , p
					   , radioRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
					   .filter(f -> f.getCategories().contains(categ))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> f.getCountry().contains(pays))
					   .filter(f-> f.isStatus())
					   .toList().size());
		}
		else {
			return res ;
		}
		
	};
	
	
	public Radio chekFav(Long idRadio) {
		 
		return null;
	}

	public Radio update( Long id, Radio u) {

		u.setIdRadio(id);
		u.setList_langues(rep_langue.findAllById(u.getLangue()));
		u.setList_country(rep_pays.findAllById(u.getCountry()));
		u.setList_categories(rep_categ.findAllById(u.getCategories()));

		refresh();
		algoClient.refreshRadio();
		return radioRep.save(u);
	}

	public Boolean delete(Long id) {

		
		radioRep.deleteById(id);
		refresh();
		algoClient.refreshRadio();
		return null;
	}

	public Optional<Radio> showById( Long id) {
		refresh();
		return radioRep.findById(id);

	}

	public Radio findByName(String name) {
		
		refresh();
		return radioRep.findByName(name);
	}

	public List<Radio> top10(){
		refresh();
		return radioRep.findByTop10True();
	}
	
	public Radio Addtop10(Long id, boolean status){
		
		Radio r =  radioRep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
	public Radio top(){
		refresh();
		return radioRep.findByTopTrue().get();
	}
	
	public Radio Addtop(Long id, boolean status){
		
		Radio r =  radioRep.findById(id).get();
		r.setTop10(status);
		
		return r;
	}
	
	
	public Radio checktoplimit() {
		
		if(radioRep.findByTopTrue().isPresent()) {
			
			return radioRep.findByTopTrue().get();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	public List<Radio> checktop10limit() {
		
		if(radioRep.findByTop10True().size() <=10) {
			
			return null;
		}
		else {
			
			return radioRep.findByTop10True();
		}
	}
}
