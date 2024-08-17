package com.mytv.api.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.util.model.Subtile;

@Repository
public interface SubtileRepository extends JpaRepository<Subtile, Long>{

}
