package com.mytv.api.service.gestPub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mytv.api.model.gestPub.PartenerCat;
import com.mytv.api.repository.PartenerCatRepository;

public class PartenerCatServiceImplement implements PartenerCatService {
	
	@Autowired
	private PartenerCatRepository partCatRep;

	@Override
	public PartenerCat create(PartenerCat u) {
		
		
		return partCatRep.save(u);
	}

	@Override
	public List<PartenerCat> show() {

		return partCatRep.findAll();
	}

	@Override
	public List<PartenerCat> showById(Long id) {

		return null;
	}

	@Override
	public PartenerCat upadte(Long id, PartenerCat p) {
		
		
		return null;
		
	}

	@Override
	public Boolean delete(Long id) {
		
		partCatRep.deleteById(id);
		return true;
	}

}
