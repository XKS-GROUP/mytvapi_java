package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.ComSaison;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestUser.User;

public interface ComSaisonRepository extends JpaRepository<ComSaison, Long> {
	
	List<ComSaison> findByUser(User u);
	List<ComSaison> findBySaison(Saison s);
	Optional<ComSaison> findByUserAndSaison(User u, Saison s);
	
}
