package com.mytv.api.serie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.serie.model.LikeSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.user.model.User;

public interface LikeSerieRepository extends JpaRepository<LikeSerie, Long>{
	
	Optional<LikeSerie> findByUser(User u);
	List<LikeSerie> findBySerie(Serie s);
	Optional<LikeSerie> findByUserAndSerie(User u, Serie s);
}
