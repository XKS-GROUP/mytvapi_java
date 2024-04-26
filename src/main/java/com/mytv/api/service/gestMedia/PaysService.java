package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.repository.PaysRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaysService {


	@Autowired
	private PaysRepository rep;


	public Pays create(Pays g) {

		return rep.save(g);

	}

	public List<Pays> show() {

		return rep.findAll();
	}
	
	public Page<Pays> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public Pays upadte(Long id, Pays u) {

		u.setIdPays(id);

		return rep.save(u);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;

	}

	public Optional<Pays> showById(final Long id) {

		return rep.findById(id);

	}

}
