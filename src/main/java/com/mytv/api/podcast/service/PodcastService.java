package com.mytv.api.podcast.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.repository.PodcasterRepository;
import com.mytv.api.podcast.model.Podcast;
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
	private CatPodcastRepository  rep_categ;
	
	@Autowired
	private LangRepository rep_langue;

	public Podcast create(Podcast p) {

		p.setList_langues(rep_langue.findAllById(p.getLangue()));
		p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
		p.setList_categories(rep_categ.findAllById(p.getCategories()));
		
		return rep.save(p);

	}

	/*
	 * 
	 * cette fonction a pour objectif de rafraichir la liste des different objets renvoyees
	 * 
	 */
	public void refresh() {
		
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

	public Podcast upadte(final Long id, Podcast p) {

		p.setIdPodcast(id);
		p.setList_langues(rep_langue.findAllById(p.getLangue()));
		p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
		p.setList_categories(rep_categ.findAllById(p.getCategories()));

		return rep.save(p);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}

	public Optional<Podcast> showById(final Long id) {

		return rep.findById(id);

	}

	public Podcast findByName(String name) {

		return rep.findByName(name);
	}
	
	public List<Podcast> top10(){
		
		return rep.findByTop10True();
	}
	
	public Podcast Addtop10(Long id, boolean status){
		
		Podcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		
		return pc;
		
	}
	
	
	public Podcast top(){
		
		return rep.findByTopTrue().get(0);
	}
	
	public Podcast Addtop(Long id, boolean status){
		
		Podcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		
		return pc;
		
	}
	


}
