package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Contributor;
import com.mytv.api.repository.ContributorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributorServiceImplement implements ContributorService {
	
	@Autowired
	private ContributorRepository contRep;

	@Override
	public Contributor create(Contributor u) {
		
		
		return contRep.save(u);
	}

	@Override
	public List<Contributor> show() {

		return contRep.findAll();
	}

	@Override
	public List<Contributor> showById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contributor upadte(Long id, Contributor p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {

	   contRep.deleteById(id);
	   return true;
	}

}
