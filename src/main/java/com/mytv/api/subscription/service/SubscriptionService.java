package com.mytv.api.subscription.service;

import java.util.List;

import com.mytv.api.user.model.Subscription;


public interface SubscriptionService {

	Subscription create(Subscription u);
	List<Subscription> show();
	//List<User> showById();
	Subscription upadte(Long id, Subscription p);
	Boolean delete(Long id);

}
