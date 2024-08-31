package com.mytv.api.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.user.model.Profil;


@Repository
public interface ProfilRepository extends  JpaRepository<Profil, Long>{

	Profil findByProfilName(String name);
	List<Profil>  findByUser (FirebaseUser u);
}
