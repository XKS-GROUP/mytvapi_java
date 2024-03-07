package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestUser.Jwt;
import com.mytv.api.model.gestUser.User;

public interface JwtRepository extends JpaRepository<Jwt, Long> {
	
	Optional<Jwt> findByValue(String value);
	List<Jwt> findByUser(User user);
	List<Jwt> deleteByUser(User user);
	
}
