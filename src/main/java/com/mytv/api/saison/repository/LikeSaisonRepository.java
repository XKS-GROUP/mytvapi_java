package com.mytv.api.saison.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.saison.model.LikeSaison;
import com.mytv.api.saison.model.Saison;
import com.mytv.api.user.model.User;
public interface LikeSaisonRepository extends JpaRepository<LikeSaison,Long>{
	List<LikeSaison> findByUser(User u);
	List<LikeSaison> findBySaison(Saison s);
	Optional<LikeSaison> findByUserAndSaison(User u, Saison s);
}
