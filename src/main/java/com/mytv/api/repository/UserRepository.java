package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestUser.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{

}
