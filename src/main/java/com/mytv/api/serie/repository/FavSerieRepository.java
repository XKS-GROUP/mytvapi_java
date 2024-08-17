package com.mytv.api.serie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.serie.model.FavSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.user.model.User;

@Repository
public interface FavSerieRepository extends JpaRepository<FavSerie, Long> {
	
	List<FavSerie> findByUser(User u);
	List<FavSerie> findBySerie(Serie s);
	Optional<FavSerie> findByUserAndSerie(User user, Serie serie);
	
}
