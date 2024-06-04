package com.mytv.api.service.gestPub;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestPub.Publicite;
import com.mytv.api.repository.PubliciteRepository;

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
	
	public Page<Publicite> showPage(Pageable p) {

		return rep.findAll(p);
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
