package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Live;
import com.mytv.api.repository.LiveRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LiveService {

	@Autowired
	LiveRepository rep;
	
	
	public Live create(Live g) {

		return rep.save(g);

	}

	public List<Live> show() {

		return rep.findAll();
	}
	
	public Page<Live> showPage(Pageable p) {

		return rep.findAll(p);
	}

	
	public List<Live> showByCategorie(Long id, Pageable p){
		
		return rep.findAll().stream()
                     .filter(f -> f.getIdCats().contains(id))
                     .collect(Collectors.toList());
		
	};
	
	public List<Live> search(String val, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p);
	}
	
	public List<Live> searchByCateg(String val, Long categ, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getIdCats().contains(categ))
                .collect(Collectors.toList());
	}

	public Live update(final Long id, Live u) {

		u.setIdLive(id);

		return rep.save(u);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Live> showById(final Long id) {

		return rep.findById(id);

	}

	public Live findByName(String name) {
		
		return rep.findByName(name);
	}

}
