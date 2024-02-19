package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

}
