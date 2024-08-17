package com.mytv.api.radio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.radio.model.FavRadio;
import com.mytv.api.radio.model.Radio;
import com.mytv.api.user.model.User;

@Repository
public interface FavRadioRepository extends JpaRepository<FavRadio, Long>{

	List<FavRadio> findByUser(User u);
	List<FavRadio> findByRadio(Radio r);
	Optional<FavRadio> findByUserAndRadio(User u, Radio r);
}
