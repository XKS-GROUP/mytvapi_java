package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.util.FirebaseSetting;

@Repository
public interface FirebaseRepository extends JpaRepository<FirebaseSetting, Long>{

}
