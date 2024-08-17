package com.mytv.api.util.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.util.model.Slider;
import com.mytv.api.util.repository.SliderRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class SliderService {

	@Autowired
	private SliderRepository rep;


	public Slider create(Slider s) {

		return rep.save(s);

	}

	public List<Slider> show() {

		return rep.findAll();
	}
	
	public Page<Slider> showPage(Pageable p) {

		return rep.findAll(p);
	}
	public Page<Slider> showByName(String n, Pageable p) {

		return rep.findByNameContaining(n, p);
	}

	public Slider upadte(final Long id, Slider s) {

		s.setId(id);

		return rep.save(s);
	}

	public void delete(Long id) {

		rep.deleteById(id);
	}

	public Optional<Slider> showById(Long id) {

		return rep.findById(id);

	}

	public Slider findByName(String name) {
		
		return rep.findByName(name);
	}

	public Page<Slider> showByPage(Long pg, Pageable p){
		
		    //PageImpl<Slider> res = new PageImpl<Slider>(rep.findAll().stream()
				//   .filter(s -> s.getTargetPage().contains(pg)).toList() 
				  // , p
				  // , rep.findAll().size());
			
			return null;
			
		
	};
	
}
