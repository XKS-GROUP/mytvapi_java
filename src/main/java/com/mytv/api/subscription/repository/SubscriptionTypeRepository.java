package com.mytv.api.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.user.model.SubscriptionType;

@Repository
public interface SubscriptionTypeRepository extends  JpaRepository<SubscriptionType, Long>{

	SubscriptionType findByName(String name);
}
