package com.mytv.api.saison.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.saison.model.FavSaison;
import com.mytv.api.saison.model.Saison;

@Repository
public interface FavSaisonRepository extends JpaRepository<FavSaison, Long>{
	
	List<FavSaison> findByUser(FirebaseUser u);
	List<FavSaison> findBySaison(Saison s);
	Optional<FavSaison> findByUserAndSaison(FirebaseUser u, Saison s);

}
