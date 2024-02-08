package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.ContributorType;
import com.mytv.api.repository.ContributorTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributorTypeServiceImplement implements ContributorTypeService{
	
	@Autowired
	private ContributorTypeRepository conTypeRep;

	@Override
	public ContributorType create(ContributorType u) {

		return conTypeRep.save(u);
	}

	@Override
	public List<ContributorType> show() {

		return conTypeRep.findAll();
	}

	@Override
	public List<ContributorType> showById(Long id) {

		return null;
	}

	@Override
	public ContributorType upadte(Long id, ContributorType p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {

		conTypeRep.deleteById(id);
		return true;
	}

}
