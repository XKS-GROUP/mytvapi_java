package com.mytv.api.subscription.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.subscription.repository.SubscriptionTypeRepository;
import com.mytv.api.user.model.SubscriptionType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionTypeServiceImplement implements SubscriptionTypeService{

	@Autowired
	private SubscriptionTypeRepository sbRep;

	@Override
	public SubscriptionType create(SubscriptionType u) {

		return sbRep.save(u);
	}

	@Override
	public List<SubscriptionType> show() {

		return sbRep.findAll();
	}
	
	public SubscriptionType showById(Long id) {

		return sbRep.findById(id).get();
	}

	public SubscriptionType findByName(String nom) {
		
		return sbRep.findByName(nom);
	}

	@Override
	public SubscriptionType upadte(Long id, SubscriptionType p) {

		p.setIdSubscriptionType(id);
		return sbRep.save(p);
	}

	
	@Override
	public Boolean delete(Long id) {

		sbRep.deleteById(id);

		return true;
	}

}
