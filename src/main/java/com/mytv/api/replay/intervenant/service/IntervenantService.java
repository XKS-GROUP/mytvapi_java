package com.mytv.api.replay.intervenant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.replay.intervenant.model.Intervenant;
import com.mytv.api.replay.intervenant.repository.IntervenantRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class IntervenantService {
	@Autowired
	IntervenantRepository rep;
	
	
	public Intervenant create(Intervenant p) {

		return rep.save(p);
	}

	public List<Intervenant> show() {

		return rep.findAll();
	}
	
	public Intervenant findByFirstAndLast(String f, String l) {
		
		return rep.findByFistNameAndLastName(f, l);
	}
	
	public Page<Intervenant> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Intervenant update(Long id, Intervenant p) {

		p.setId(id);

		return rep.save(p);
	}
	
	public Optional<Intervenant> showById(Long id) {

		return rep.findById(id);

	}


	public Boolean delete(Long id) {

		rep.deleteById(id);

		return true;

	}
}
