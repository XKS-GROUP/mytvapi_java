package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.repository.PodcastRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PodcastService {


	@Autowired
	private PodcastRepository rep;


	public Podcast create(Podcast g) {

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

}
