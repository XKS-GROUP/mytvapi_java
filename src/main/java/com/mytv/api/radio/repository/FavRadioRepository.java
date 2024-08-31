package com.mytv.api.radio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.radio.model.FavRadio;
import com.mytv.api.radio.model.Radio;

@Repository
public interface FavRadioRepository extends JpaRepository<FavRadio, Long>{

	List<FavRadio> findByUser(FirebaseUser u);
	List<FavRadio> findByRadio(Radio r);
	List<FavRadio> findByUid(String uid);
	Page<FavRadio> findByUid(String uid, Pageable p);
	Optional<FavRadio> findByUserAndRadio(FirebaseUser u, Radio r);
}
