package com.mytv.api.podcast.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.PodcasterRepository;
import com.mytv.api.podcast.CatPodcastRepository;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcast.repository.PodcastRepository;
import com.mytv.api.ressource.model.PodcastGenre;
import com.mytv.api.ressource.repository.CategoryLrRepository;
import com.mytv.api.ressource.repository.LangRepository;
import com.mytv.api.ressource.repository.PaysRepository;

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

	public Podcast create(Podcast g) {

		g.setList_langues(rep_langue.findAllById(g.getLangue()));
		//g.setList_categories(rep_categ.findAllById(g.getCategories()));
		
		
		return rep.save(g);

	}

	public List<Podcast> show() {

		return rep.findAll();
		
	}

	public Page<Podcast> showPage(Pageable p) {

		return rep.findAll(p);
	}
	
	public Page<Podcast> showByCateg(Long id, Pageable p){
		
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
				   .filter(f -> f.getCategories().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Podcast> showByLang(Long id, Pageable p){
		
		PageImpl<Podcast> res = new PageImpl<Podcast>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<Podcast> showByGenreAndLang(Long genre , Long langue, Pageable p){
		
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

		return rep.findByNameContainingOrOverviewContaining(n, n, p);
	}
	
	public Page<Podcast> searchByCateg(String n, Long categ, Pageable p) {

		PageImpl<Podcast> res = new PageImpl<Podcast>(
				rep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getCategories().contains(categ))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Podcast> searchByLang(String n,Long langue, Pageable p) {

		PageImpl<Podcast> res = new PageImpl<Podcast>(
				rep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList())
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<Podcast> searchByGenreAndLang(String val, Long genre , Long langue, Pageable p){
		
		PageImpl<Podcast> res = new PageImpl<Podcast>(
				    rep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getCategories().contains(genre))
				   .filter(f -> f.getLangue().contains(langue))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};

	public Podcast upadte(final Long id, Podcast u) {


		u.setIdPodcast(id);

		return rep.save(u);
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
	
	
	public List<Podcast> top(){
		
		return rep.findByTopTrue();
	}
	
	public Podcast Addtop(Long id, boolean status){
		
		Podcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		
		return pc;
		
	}
	


}
