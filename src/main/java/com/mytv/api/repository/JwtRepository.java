package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestUser.Jwt;

public interface JwtRepository extends JpaRepository<Jwt, Long> {

}
