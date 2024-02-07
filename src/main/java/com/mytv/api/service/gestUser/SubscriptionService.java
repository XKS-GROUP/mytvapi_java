package com.mytv.api.service.gestUser;

import java.util.List;

import com.mytv.api.model.gestUser.Subscription;


public interface SubscriptionService {
	
	Subscription create(Subscription u);
	List<Subscription> show();
	//List<User> showById();
	Subscription upadte(Long id, Subscription p);
	Boolean delete(Long id);

}
