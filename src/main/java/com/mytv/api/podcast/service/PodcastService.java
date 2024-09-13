package com.mytv.api.podcast.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mytv.api.config.AlgoliaConfig;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.intervenant.repository.PodcasterRepository;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.repository.FavPodcastRepository;
import com.mytv.api.podcast.repository.PodcastRepository;
import com.mytv.api.podcastcateg.repository.CatPodcastRepository;
import com.mytv.api.ressource.repository.LangRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PodcastService {


	@Autowired
	private PodcastRepository rep;

	@Autowired
	private PodcasterRepository rep_podcasteur;
	
	@Autowired
	private FavPodcastRepository rep_fav_podcast;
	
	@Autowired
	private CatPodcastRepository  rep_categ;
	
	@Autowired
	private LangRepository rep_langue;
	
	@Autowired
	private AlgoliaConfig algoClient;

	public Podcast create(Podcast p) {

		p.setList_langues(rep_langue.findAllById(p.getLangue()));
		p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
		p.setList_categories(rep_categ.findAllById(p.getCategories()));
		
		var resp = algoClient.searchClient().saveObject("podcast", p);
		
		algoClient.searchClient().waitForTask("podcast", resp.getTaskID());
		
		return rep.save(p);
	}
	
	public Page<Podcast> similaire_show(Long id, Pageable p) {
		
		Podcast m =  rep.findById(id).get();
		
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream() 
				   .filter(rd -> rd.getCategories().containsAll(m.getCategories())) 
				   .toList() 
        		   , p 
				   , rep.findAll().size()); 
		
		//return rep.findDistinctByCategIn(m.getList_categories(), p);
		
		return res;
	}
	
	public Podcast checktoplimit() {
		refresh();
		if(rep.findByTopTrue() != null) {
			
			return rep.findByTopTrue();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	
	public List<Podcast> checktop10limit() {
		refresh();
		if(rep.findByTop10True().size() <=10) {
			
			return null;
		}
		else {
			
			return rep.findByTop10True();
		}
	}

	/*
	 * 
	 * cette fonction a pour objectif de rafraichir la liste des different objets renvoyees
	 * 
	 */
	
	public void refresh() {
		
	
		//Si l user est un abonne
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) {
			
			
			//Retirer les favories de tous les users
			rep_fav_podcast.findAll().forEach(
					
					f -> {
						
						f.getPodcast().setFavorie(false);
					}
					
				);
			
			
			FirebaseUser u = (FirebaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			//Afficher que les favories du l utilisateur actuelle
			rep_fav_podcast.findByUid(u.getUid()).forEach(
					
					f -> {
						
						f.getPodcast().setFavorie(true);
					}
					
				);
			
			
		}
		
		
       List<Podcast> l = rep.findAll();
		
		l.forEach(  
				
				p -> {
					p.setList_langues(rep_langue.findAllById(p.getLangue()));
					p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
					p.setList_categories(rep_categ.findAllById(p.getCategories()));
				}
				
		);
		
	}
	
	
	public List<Podcast> show() {
		refresh();
		return rep.findAll();
	}
	
	public List<Podcast> show_front() {
		refresh();
		return rep.findAll().stream().filter(
				f-> f.isStatus()
				).toList();
	}


	public Page<Podcast> showPage(Pageable p) {
		refresh();
		return rep.findAll(p);
	}
	
	public Page<Podcast> showByCateg(Long id, Pageable p){
		refresh();
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
				   .filter(f -> f.getCategories().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Podcast> showByLang(Long id, Pageable p){
		refresh();
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Podcast> showByGenreAndLang(Long genre , Long langue, Pageable p){
		refresh();
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
				   .filter(f -> f.getCategories().contains(genre))
				   .filter(f -> f.getLangue().contains(langue))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Podcast> filtre_complet(Long genre, Long langue, Pageable p){
		
		
		if(genre != null && langue !=null) {
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					 .filter(f -> f.getCategories().contains(genre))
					 .filter(f -> f.getLangue().contains(langue))
					   .toList() 
					   , p
					   , rep.findAll().stream()
						 .filter(f -> f.getCategories().contains(genre))
						 .filter(f -> f.getLangue().contains(langue))
						  .toList() .size());
				
				return res;
		}
		else if (genre != null && langue ==null){
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .toList() .size());
				
				return res;
		}
		else if( langue != null && genre ==null ) {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
				
				return res;
		}
		else {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .toList().size());
				
				return res;
		}
		
	}
	
	public Page<Podcast> filtre_complet_front(Long genre, Long langue, Pageable p){
		
		
		if(genre == null && langue ==null) {
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .filter(f-> f.isStatus())
					   .toList() .size());
				
				return res;
		}
		else if (genre != null && langue ==null){
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		
		else if( langue != null && genre ==null ) {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		else {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList() 
					   , p
					   , rep.findAll().stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f-> f.isStatus())
					   .toList().size());
				
				return res;
		}
		
	}
	
	
	public Page<Podcast> filtre_recherche_complet(String s, Long genre, Long langue, Pageable p){
		
		
		if(s==null || s.isBlank() ||s.isEmpty()) {
			return filtre_complet(genre, langue, p);
		}
		else if(genre != null && langue !=null) {
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
				
				return res;
		}
		else if (genre != null && langue ==null){
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .toList().size());
				
				return res;
		}
		else if( langue != null && genre ==null ) {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .toList().size());
				
				return res;
		}
		else {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .toList().size());
				
				return res;
		}
		
	}
	
	public Page<Podcast> filtre_recherche_complet_front(String s, Long genre, Long langue, Pageable p){
		
		
		if(s==null || s.isBlank() ||s.isEmpty()) {
			
			return filtre_complet_front(genre, langue, p);
		}
		else if(genre != null && langue !=null) {
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f->f.isStatus())
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f->f.isStatus())
					   .toList() 
					   .size());
				
				return res;
		}
		else if (genre != null && langue ==null){
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f->f.isStatus())
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getCategories().contains(genre))
					   .filter(f->f.isStatus())
					   .toList().size());
				
				return res;
		}
		else if( langue != null && genre ==null ) {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f->f.isStatus())
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f -> f.getLangue().contains(langue))
					   .filter(f->f.isStatus())
					   .toList().size());
				
				return res;
		}
		else {
			
			 PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f->f.isStatus())
					   .toList() 
					   , p
					   , rep.findByNameContainingOrOverviewContaining(s, s, p).stream()
					   .filter(f->f.isStatus())
					   .toList().size());
				
				return res;
		}
		
	}
	

	
	//public List<Podcast> 
	
	public Page<Podcast> search(String n, Pageable p) {
		refresh();
		return rep.findByNameContainingOrOverviewContaining(n, n, p);
	}
	
	public Page<Podcast> searchByCateg(String n, Long categ, Pageable p) {
		refresh();
		PageImpl<Podcast> res = new PageImpl<Podcast>(
				rep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getCategories().contains(categ))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Podcast> searchByLang(String n,Long langue, Pageable p) {
		refresh();
		PageImpl<Podcast> res = new PageImpl<Podcast>(
				rep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Podcast> searchByGenreAndLang(String val, Long genre , Long langue, Pageable p){
		refresh();
		PageImpl<Podcast> res = new PageImpl<Podcast>(
				    rep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getCategories().contains(genre))
				   .filter(f -> f.getLangue().contains(langue))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};

	public Podcast update(Long id, Podcast p) {

		p.setIdPodcast(id);
		p.setList_langues(rep_langue.findAllById(p.getLangue()));
		p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
		p.setList_categories(rep_categ.findAllById(p.getCategories()));
		refresh();
		Podcast pd = rep.save(p);
		algoClient.refreshPodcast();
		return pd;
	}

	public Boolean delete(Long id) {
		refresh();
		rep.deleteById(id);
		algoClient.refreshPodcast();
		return true;

	}

	public Optional<Podcast> showById(final Long id) {
		refresh();
		return rep.findById(id);

	}

	public Podcast findByName(String name) {
		refresh();
		return rep.findByName(name);
	}
	
	public List<Podcast> top10(){
		refresh();
		return rep.findByTop10True();
	}
	
	public Podcast Addtop10(Long id, boolean status){
		
		Podcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		refresh();
		return pc;
		
	}
	
	
	public Podcast top(){
		refresh();
		return rep.findByTopTrue();
	}
	
	public Podcast Addtop(Long id, boolean status){
		
		Podcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		refresh();
		return pc;
		
	}
	


}
