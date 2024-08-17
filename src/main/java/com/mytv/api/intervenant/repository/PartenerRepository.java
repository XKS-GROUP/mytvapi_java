package com.mytv.api.intervenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.intervenant.model.Partener;

@Repository
public interface PartenerRepository extends  JpaRepository<Partener, Long>{

}
