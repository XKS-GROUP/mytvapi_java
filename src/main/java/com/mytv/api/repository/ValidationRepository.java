package com.mytv.api.repository;


import org.springframework.data.repository.CrudRepository;

import com.mytv.api.model.gestUser.Validation;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {

    Optional<Validation> findByCode(String code);
}
