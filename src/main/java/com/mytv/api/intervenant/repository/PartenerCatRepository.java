package com.mytv.api.intervenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.intervenant.model.PartenerCat;


@Repository
public interface PartenerCatRepository extends  JpaRepository<PartenerCat, Long> {

}
