package com.mytv.api.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestUser.Validation;


@Repository
public interface ValidationRepository extends CrudRepository<Validation, Long> {

    Optional<Validation> findByCode(String code);
    Validation findByUtilisateurId(Long id);

    Long deleteByCode(String code);
    Long deleteByUtilisateurId(Long id);

}
