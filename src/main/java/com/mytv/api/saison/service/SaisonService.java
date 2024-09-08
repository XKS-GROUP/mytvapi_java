package com.mytv.api.saison.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.saison.repository.FavSaisonRepository;
import com.mytv.api.saison.repository.SeasonRepository;
import com.mytv.api.serie.model.Serie;

@Service
public class SaisonService{

	@Autowired
	private SeasonRepository seasRep;

	public Saison create(Saison u) {
		
		
		//u.setSerieRef(u.getIdSerie().getIdSerie());
		
		return seasRep.save(u);
		
	}

	public List<Saison> show() {
		refresh();
		return seasRep.findAll();
	}
	
	public Page<Saison> showPage(Pageable p) {
		refresh();
		return seasRep.findAll(p);
	}
	
	public Page<Saison> showByLangue(Long id, Pageable p){
		refresh();
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   
				   ,seasRep.findAll().size());
			
			return res;
	};
	
	public Page<Saison> showBySerie(Serie serie, Pageable p) {
		refresh();
		return seasRep.findByIdSerie(serie, p);
	}
	
	public Page<Saison> showByidSerie(Long idSerie, Pageable p) {
		refresh();
		return seasRep.findBySerieRef(idSerie, p);
	}
	
	
	public Page<Saison> showByLangueAndSerie(Long langue, Long serie, Pageable p){
		refresh();
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getSerieRef() == serie)
				   .toList() 
				   , p
				   
				   ,seasRep.findAll().size());
			
			return res;
	};
	
	
	
	public Page<Saison> filtre_complet_front(Long serie, Long langue, Pageable p){
		
		if(serie == null && langue ==null) {
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findAll().stream()
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() .size());
				
				return res;
		}
		else if (serie != null && langue ==null){
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
					   .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findAll().stream()
					   .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		else if( langue != null && serie ==null ) {
			
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		else {
			
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findAll().stream()
					 .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findAll().stream()
					   .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		
	}
	
	public Page<Saison> filtre_recherche_complet_front(String n,Long serie, Long langue, Pageable p){
		
		if(serie == null && langue ==null) {
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() .size());
				
				return res;
		}
		else if (serie != null && langue ==null){
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		else if( langue != null && serie ==null ) {
			
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		else {
			
			 PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					 .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
					   .filter(f -> f.getSerieRef() == serie)
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f -> !f.getEpisodes().isEmpty())
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		
	}
	
	
	
	
	public Page<Saison> search(String n, Pageable p) {
		refresh();
		return seasRep.findByNameContainingOrOverviewContaining(n, n, p);
	}
	
	public Page<Saison> searchByLangue(String n, Long langue, Pageable p) {
		refresh();
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList()) 
				   , p
				   ,seasRep.findAll().size());
			
			return res;
	}
	
	public Page<Saison> searchBySerie(String n, Long serie, Pageable p) {
		refresh();
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(n, n, p).stream()
                .filter(f -> f.getSerieRef() == serie)
                .collect(Collectors.toList()) 
				   , p
				   ,seasRep.findAll().size());
			
			return res;
	}
	
	public Page<Saison> searchByLangueAndSerie(String val, Long langue, Long serie, Pageable p){
		refresh();
		PageImpl<Saison> res = new PageImpl<Saison>(seasRep.findByNameContainingOrOverviewContaining(val, val, p).stream()
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getSerieRef() == serie)
				   .toList() 
				   , p
				   
				   ,seasRep.findAll().size());
			
			return res;
	};

	public Saison showById(Long id) {
		refresh();
		return seasRep.findById(id).get();
	}

	public Saison update(Long id, Saison p) {
		refresh();
		p.setIdSaison(id);
	   // p.setSerieRef(p.getIdSerie().getIdSerie());
		
		return seasRep.save(p);
		
	}

	public Boolean delete(Long id) {
		seasRep.deleteById(id);
		return null;
	}

	public Saison findByName(String name) {
		refresh();
		return seasRep.findByName(name);
	}
	
	@Autowired
	FavSaisonRepository rep_fav_saison;
	public void refresh() {
		
		//Si l user est un abonne
				if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
					
					
					//Retirer les favories de tous les users
					rep_fav_saison.findAll().forEach(
							
							f -> {
								
								f.getSaison().setFavorie(false);
							}
							
						);
					
					
					FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					
					//Afficher que les favories du l utilisateur actuelle
					rep_fav_saison.findByUid(u.getUid()).forEach(
							
							f -> {
								
								f.getSaison().setFavorie(true);
							}
							
						);
				}
	}

}
