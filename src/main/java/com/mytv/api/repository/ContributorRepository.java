package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Contributor;

public interface ContributorRepository extends  JpaRepository<Contributor, Long> {

}
