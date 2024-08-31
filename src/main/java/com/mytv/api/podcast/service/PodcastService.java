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

	public Podcast create(Podcast p) {

		p.setList_langues(rep_langue.findAllById(p.getLangue()));
		p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
		p.setList_categories(rep_categ.findAllById(p.getCategories()));
		
		return rep.save(p);

	}
	
	
	
	public Page<Podcast> similaire_show(Long id, Pageable p) {
		Podcast m =  rep.findById(id).get();
		
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
				   .filter(rd -> rd.getCategories().containsAll(m.getCategories()))
				   .toList() 
				   , p
				   , rep.findAll().size());
		return res;
	}
	
	
	public Podcast checktoplimit() {
		
		if(rep.findByTopTrue() != null) {
			
			return rep.findByTopTrue();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	
	public List<Podcast> checktop10limit() {
		
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
		
	
		rep_fav_podcast.findAll().forEach(
				
				f -> {
					
					f.getPodcast().setFavorie(true);
				}
				
			);
		
		
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
		
		return pc;
		
	}
	
	
	public Podcast top(){
		refresh();
		return rep.findByTopTrue();
	}
	
	public Podcast Addtop(Long id, boolean status){
		
		Podcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		
		return pc;
		
	}
	


}
