package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.FavSerie;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;

@Repository
public interface FavSerieRepository extends JpaRepository<FavSerie, Long> {
	
	List<FavSerie> findByUser(User u);
	List<FavSerie> findBySerie(Serie s);
	Optional<FavSerie> findByUserAndSerie(User user, Serie serie);
	
}
