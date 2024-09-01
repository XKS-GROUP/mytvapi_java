package com.mytv.api.serie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.serie.model.FavSerie;
import com.mytv.api.serie.model.Serie;

@Repository
public interface FavSerieRepository extends JpaRepository<FavSerie, Long> {
	
	List<FavSerie> findByUser(FirebaseUser u);
	List<FavSerie> findBySerie(Serie s);
	Optional<FavSerie> findByUserAndSerie(FirebaseUser user, Serie serie);
	List<FavSerie> findByUid(String uid);
	
}
