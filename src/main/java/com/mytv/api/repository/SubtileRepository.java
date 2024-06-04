package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Subtile;

@Repository
public interface SubtileRepository extends JpaRepository<Subtile, Long>{

}
