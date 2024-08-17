package com.mytv.api.serie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.serie.model.ComSerie;
import com.mytv.api.serie.model.Serie;
import com.mytv.api.user.model.User;

public interface ComSerieRepository extends JpaRepository<ComSerie, Long>{
	List<ComSerie> findByUser(User u);
	List<ComSerie> findBySerie(Serie s);
	Optional<ComSerie> findByUserAndSerie(User user, Serie serie); 
}
