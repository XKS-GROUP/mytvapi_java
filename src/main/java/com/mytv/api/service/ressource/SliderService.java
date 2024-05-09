package com.mytv.api.service.ressource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.util.Slider;
import com.mytv.api.repository.SliderRepository;

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
	public List<Slider> showByName(String n) {

		return rep.findByNameContaining(n);
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

	
}
