package com.mytv.api.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.user.model.Profil;
import com.mytv.api.user.model.User;


@Repository
public interface ProfilRepository extends  JpaRepository<Profil, Long>{

	Profil findByProfilName(String name);
	
	List<Profil>  findByUtilisateur (User u);
}
