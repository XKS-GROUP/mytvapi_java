package com.mytv.api.podcastCollecton.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.intervenant.repository.PodcasterRepository;
import com.mytv.api.podcastCollecton.model.ColPodcast;
import com.mytv.api.podcastCollecton.repository.CollectionPodcastRepository;
import com.mytv.api.podcastcateg.repository.CatPodcastRepository;
import com.mytv.api.ressource.repository.LangRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ColPodcastService {
	
	CollectionPodcastRepository rep;
	
	@Autowired
	private PodcasterRepository rep_podcasteur;
	
	@Autowired
	private CatPodcastRepository  rep_categ;
	
	@Autowired
	private LangRepository rep_langue;

	public ColPodcast create(ColPodcast p) {

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
		
       List<ColPodcast> l = rep.findAll();
		
		l.forEach(  
				
				p -> {
					p.setList_langues(rep_langue.findAllById(p.getLangue()));
					p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
					p.setList_categories(rep_categ.findAllById(p.getCategories()));
				}
				
		);
	}
	
	
	public List<ColPodcast> show() {
		refresh();
		return rep.findAll();
	}

	public Page<ColPodcast> showPage(Pageable p) {
		refresh();
		return rep.findAll(p);
	}
	
	public Page<ColPodcast> showByCateg(Long id, Pageable p){
		refresh();
		PageImpl<ColPodcast> res = new PageImpl<ColPodcast>(rep.findAll().stream()
				   .filter(f -> f.getCategories().contains(id))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<ColPodcast> showByLang(Long id, Pageable p){
		refresh();
		PageImpl<ColPodcast> res = new PageImpl<ColPodcast>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<ColPodcast> showByGenreAndLang(Long genre , Long langue, Pageable p){
		refresh();
		PageImpl<ColPodcast> res = new PageImpl<ColPodcast>(rep.findAll().stream()
				   .filter(f -> f.getCategories().contains(genre))
				   .filter(f -> f.getLangue().contains(langue))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	//public List<ColPodcast> 
	
	public Page<ColPodcast> search(String n, Pageable p) {
		refresh();
		return rep.findByNameContainingOrOverviewContaining(n, n, p);
		
	}
	
	public Page<ColPodcast> searchByCateg(String n, Long categ, Pageable p) {
		refresh();
		PageImpl<ColPodcast> res = new PageImpl<ColPodcast>(
				rep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getCategories().contains(categ))
                .toList()
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<ColPodcast> searchByLang(String n,Long langue, Pageable p) {
		refresh();
		PageImpl<ColPodcast> res = new PageImpl<ColPodcast>(
				rep.findByNameContainingOrOverviewContaining(n, n).stream()
                .filter(f -> f.getLangue().contains(langue)).toList()
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<ColPodcast> searchByGenreAndLang(String val, Long genre , Long langue, Pageable p){
		refresh();
		PageImpl<ColPodcast> res = new PageImpl<ColPodcast>(
				    rep.findByNameContainingOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getCategories().contains(genre))
				   .filter(f -> f.getLangue().contains(langue))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};

	public ColPodcast upadte(final Long id, ColPodcast p) {

		p.setIdColPd(id);
		p.setList_langues(rep_langue.findAllById(p.getLangue()));
		p.setList_podcasteur(rep_podcasteur.findAllById(p.getIdPodcasteur()));
		p.setList_categories(rep_categ.findAllById(p.getCategories()));

		return rep.save(p);
	}

	
	public Boolean delete(Long id) {
		refresh();
		rep.deleteById(id);

		return true;

	}

	public Optional<ColPodcast> showById(final Long id) {
		refresh();
		return rep.findById(id);

	}

	public ColPodcast findByName(String name) {
		refresh();
		return rep.findByName(name);
	}
	
	public List<ColPodcast> top10(){
		refresh();
		return rep.findByTop10True();
	}
	
	public ColPodcast Addtop10(Long id, boolean status){
		
		ColPodcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		
		return pc;
		
	}
	
	
	public ColPodcast top(){
		refresh();
		return rep.findByTopTrue();
	}
	
	public ColPodcast Addtop(Long id, boolean status){
		
		ColPodcast pc =  rep.findById(id).get();
		pc.setTop10(status);
		
		return pc;
		
	}
	
	public ColPodcast checktoplimit() {
		
		if(rep.findByTopTrue() != null) {
			
			return rep.findByTopTrue();
		}
		
		else {
			
			return null;
		}
	}
	
	//Si null la limite n'est pas encore atteinte
	public List<ColPodcast> checktop10limit() {
		
		if(rep.findByTop10True().size() <=10) {
			
			return null;
		}
		else {
			
			return rep.findByTop10True();
		}
	}

}
