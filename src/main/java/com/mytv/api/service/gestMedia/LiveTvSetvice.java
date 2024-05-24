package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.repository.LiveTvRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class LiveTvSetvice {


	@Autowired
	private LiveTvRepository rep;


	public LiveTv create(LiveTv g) {

		return rep.save(g);

	}

	public List<LiveTv> show() {

		return rep.findAll();
	}
	
	public Page<LiveTv> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Page<LiveTv> search(String val, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p);
	}

	public LiveTv update(final Long id, LiveTv u) {

		LiveTv old = rep.findById(id).get();

		old = u;

		old.setIdLiveTv(id);

		return rep.save(old);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<LiveTv> showById(final Long id) {

		return rep.findById(id);

	}
	
	public Page<LiveTv> showByLangue(Long id, Pageable p){
		
		 PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll().stream()
				   .filter(f -> f.getLangue().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<LiveTv> showByGenre(Long id, Pageable p){
		
		 PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll().stream()
				   .filter(f -> f.getIdcategories().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<LiveTv> showByPays(Long id, Pageable p){
		
		 PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll().stream()
				   .filter(f -> f.getCountry().contains(id)).toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	
	public Page<LiveTv> showByPaysGenreLangue(Long categ, Long langue, Long pays, Pageable p){
		
		 PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findAll().stream()
				   .filter(f -> f.getIdcategories().contains(categ))
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCountry().contains(pays))
				   .toList() 
				   , p
				   , rep.findAll().size());
			
			return res;
		
	};
	

	public Page<LiveTv> searchbyLangue(String val, Long langue, Pageable p) {

		 PageImpl<LiveTv> res = new PageImpl<LiveTv>( rep.findByNameOrOverviewContaining(val, val).stream()
	                .filter(f -> f.getLangue().contains(langue))
	                .collect(Collectors.toList()) 
				   , p
				   , rep.findAll().size());
			
			return res;
	}
	
	public Page<LiveTv> searchByGenre(String val,Long genre, Pageable p) {

		PageImpl<LiveTv> res = new PageImpl<LiveTv>( rep.findByNameOrOverviewContaining(val, val).stream()
                .filter(f -> f.getIdcategories().contains(genre))
                .collect(Collectors.toList()) 
			   , p
			   , rep.findAll().size());
		
		return res;
		
	}
	
	public Page<LiveTv> searchByPays(String val,Long pays, Pageable p) {

		PageImpl<LiveTv> res = new PageImpl<LiveTv>( rep.findByNameOrOverviewContaining(val, val).stream()
                .filter(f -> f.getCountry().contains(pays))
                .collect(Collectors.toList()) 
			   , p
			   , rep.findAll().size());
		return res;
		
	}
	
	public Page<LiveTv> searchByPaysGenreLangue(String val, Long categ, Long langue, Long pays, Pageable p){
		
		 PageImpl<LiveTv> res = new PageImpl<LiveTv>(rep.findByNameOrOverviewContaining(val, val).stream()
				   .filter(f -> f.getIdcategories().contains(categ))
				   .filter(f -> f.getLangue().contains(langue))
				   .filter(f -> f.getCountry().contains(pays))
				   .toList() 
				   ,p
				   ,rep.findAll().size());
			
			return res;
		
	};
	
	public LiveTv findByName(String name) {
		
		return rep.findByName(name);
	}

}
