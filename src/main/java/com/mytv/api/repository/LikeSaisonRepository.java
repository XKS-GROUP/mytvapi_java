package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikeSaison;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestUser.User;
public interface LikeSaisonRepository extends JpaRepository<LikeSaison,Long>{
	List<LikeSaison> findByUser(User u);
	List<LikeSaison> findBySaison(Saison s);
	Optional<LikeSaison> findByUserAndSaison(User u, Saison s);
}
