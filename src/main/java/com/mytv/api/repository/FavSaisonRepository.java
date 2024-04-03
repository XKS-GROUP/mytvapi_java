package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.FavSaison;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestUser.User;

@Repository
public interface FavSaisonRepository extends JpaRepository<FavSaison, Long>{
	
	List<FavSaison> findByUser(User u);
	List<FavSaison> findBySaison(Saison s);
	Optional<FavSaison> findByUserAndSaison(User u, Saison s);

}
