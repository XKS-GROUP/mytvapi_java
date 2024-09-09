package com.mytv.api.util.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.util.model.Publicite;
import com.mytv.api.util.repository.PubliciteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PubliciteService {

	
	@Autowired 
	PubliciteRepository rep;
	
	
	public Publicite create(Publicite p) {

		return rep.save(p);

	}

	public List<Publicite> show() {

		return rep.findAll();
	}
	
	public List<Publicite> show_front() {

		return rep.findAll().stream()
				.filter( f ->f.isStatus()).toList();
		
	}
	
	public Page<Publicite> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Page<Publicite> showPage_front(Pageable p) {

		PageImpl<Publicite> res = new PageImpl<Publicite>(
				   rep.findAll(p).stream()
				  .filter(s -> s.isStatus())
				  .toList()  
				  , p 
				  , rep.findAll(p).stream()
				  .filter(s -> s.isStatus())
				  .toList().
				  size());
		
			return res;
	}
	
	public Publicite update(final Long id, Publicite u) {

		u.setIdPublicite(id);

		return rep.save(u);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Publicite> showById(Long id) {

		return rep.findById(id);

	}

	public Page<Publicite> findByName(String name, Pageable p) {
		
		return rep.findByNameContaining(name, p);
	}
	
	
	public Publicite findByName(String name) {
		
		return rep.findByName(name);
	}

	public void deleteById(Long id) {
		
		rep.deleteById(id);
		
	}
	
	
}
