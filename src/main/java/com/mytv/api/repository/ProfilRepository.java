package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestUser.Profil;


@Repository
public interface ProfilRepository extends  JpaRepository<Profil, Long>{

	Profil findByProfilName(String name);
}
