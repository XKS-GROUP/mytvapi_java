package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestUser.Profil;
import com.mytv.api.model.gestUser.User;



@Repository
public interface ProfilRepository extends  JpaRepository<Profil, Long>{

	Profil findByProfilName(String name);
	
	List<Profil>  findByUtilisateur (User u);
}
