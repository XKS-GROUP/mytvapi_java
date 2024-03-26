package com.mytv.api.service.gestUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.Subscription;
import com.mytv.api.repository.SubscriptionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionServiceImplement implements SubscriptionService {

	@Autowired
	private SubscriptionRepository sbRep;

	@Override
	public Subscription create(Subscription sb) {

		return sbRep.save(sb);
	}

	@Override
	public List<Subscription> show() {

		return sbRep.findAll();
	}

	@Override
	public Subscription upadte(Long id, Subscription p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {
		sbRep.deleteById(id);
		return true;
	}

}
