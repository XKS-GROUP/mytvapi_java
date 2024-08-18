package com.mytv.api.replay.intervenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.replay.intervenant.model.Intervenant;

@Repository
public interface IntervenantRepository extends JpaRepository<Intervenant, Long>{

	Intervenant findByFistNameAndLastName(String f, String l);
}
