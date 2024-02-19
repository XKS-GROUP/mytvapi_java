package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.ContributorType;

@Repository
public interface ContributorTypeRepository extends  JpaRepository<ContributorType, Long> {

}
