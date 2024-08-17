package com.mytv.api.setting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.setting.model.FirebaseSetting;

@Repository
public interface FirebaseRepository extends JpaRepository<FirebaseSetting, Long>{

}
