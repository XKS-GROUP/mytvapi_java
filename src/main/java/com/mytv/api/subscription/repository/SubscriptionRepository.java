package com.mytv.api.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.subscription.model.Subscription;

@Repository
public interface SubscriptionRepository extends  JpaRepository<Subscription, Long>{


}
