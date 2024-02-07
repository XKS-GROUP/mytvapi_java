package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestPub.Publicite;

public interface PubliciteRepository extends  JpaRepository<Publicite, Long>{

}
