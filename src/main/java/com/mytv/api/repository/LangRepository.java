package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.ressource.Language;

@Repository
public interface LangRepository extends  JpaRepository<Language, Long>{
	Language findByName(String name);
}
