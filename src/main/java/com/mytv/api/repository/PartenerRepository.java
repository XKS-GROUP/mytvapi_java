package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestPub.Partener;

@Repository
public interface PartenerRepository extends  JpaRepository<Partener, Long>{

}
