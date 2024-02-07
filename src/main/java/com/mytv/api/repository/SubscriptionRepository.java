package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestUser.Subscription;

public interface SubscriptionRepository extends  JpaRepository<Subscription, Long>{

}
