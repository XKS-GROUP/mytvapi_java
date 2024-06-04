package com.mytv.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestPub.Publicite;


@Repository
public interface PubliciteRepository extends  JpaRepository<Publicite, Long>{

	Page<Publicite> findByNameContaining(String name, Pageable p);
	
	Publicite findByName(String name);

}
