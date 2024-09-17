package com.mytv.api.newsletter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.newsletter.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

    Optional<Subscriber> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Subscriber> findAllBySubscribedTrue();

	List<Subscriber> findByEmailContaining(String s, Pageable p);
	
	List<Subscriber> findByEmailContainingAndSubscribedTrue(String s, Pageable p);
}
