package com.mytv.api.firebase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;


@Repository
public interface FirebaseUserRepository extends JpaRepository<FirebaseUser, String> {
	
	FirebaseUser findByUid(String uid);
	FirebaseUser findByUsername(String username);
	FirebaseUser findByEmail(String email);
	List<FirebaseUser> findByEmailVerified(boolean emailVerified);

}
