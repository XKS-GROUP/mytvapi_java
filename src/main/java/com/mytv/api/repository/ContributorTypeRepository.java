package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.ContributorType;

public interface ContributorTypeRepository extends  JpaRepository<ContributorType, Long> {

}
