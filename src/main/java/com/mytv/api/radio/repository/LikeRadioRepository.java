package com.mytv.api.radio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.radio.model.LikeRadio;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.user.model.User;

@Repository
public interface LikeRadioRepository extends JpaRepository<LikeRadio, Long>{
	
	Optional<LikeRadio> findByUser(User u);
	List<LikeRadio> findByRadio(Radio r);
	Optional<LikeRadio> findByUserAndRadio(User u, Radio r);
}
