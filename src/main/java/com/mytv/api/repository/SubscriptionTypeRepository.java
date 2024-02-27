package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestUser.SubscriptionType;

@Repository
public interface SubscriptionTypeRepository extends  JpaRepository<SubscriptionType, Long>{

	SubscriptionType findByName(String name);
}
