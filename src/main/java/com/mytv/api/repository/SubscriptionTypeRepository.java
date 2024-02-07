package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestUser.SubscriptionType;

public interface SubscriptionTypeRepository extends  JpaRepository<SubscriptionType, Long>{

}
