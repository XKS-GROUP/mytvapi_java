package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestUser.Subscription;

@Repository
public interface SubscriptionRepository extends  JpaRepository<Subscription, Long>{


}
