package com.mytv.api.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.user.model.Jwt;
import com.mytv.api.user.model.User;

public interface JwtRepository extends JpaRepository<Jwt, Long> {

	Optional<Jwt> findByValue(String value);
	List<Jwt> findByUser(User user);
	List<Jwt> deleteByUser(User user);

}
