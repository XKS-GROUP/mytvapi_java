package com.mytv.api.service.gestMedia;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.repository.SerieRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class SerieService {


	@Autowired
	private SerieRepository rep;


	public Serie create(Serie g) {

		return rep.save(g);

	}

	public List<Serie> show() {

		return rep.findAll();
	}

	public List<Serie> showbyNameContaining(String n) {

		return rep.findByNameContaining(n);
	}

	public Serie upadte(final Long id, Serie u) {

		Serie old = rep.findById(id).get();

		old = u;


		old.setIdSerie(id);

		return rep.save(old);
	}



	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Serie> showById(final Long id) {

		return rep.findById(id);

	}

}
