package com.mytv.api.service.gestUser;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestUser.SubscriptionType;

@AutoConfiguration
public interface SubscriptionTypeService {
	
	SubscriptionType create(SubscriptionType u);
	List<SubscriptionType> show();
	//List<SubscriptionType> showById();
	SubscriptionType upadte(Long id, SubscriptionType p);
	Boolean delete(Long id);


}
