package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.ressource.Language;

@Repository
public interface LangRepository extends  JpaRepository<Language, Long>{
	List<Language> findByName(String name, Pageable p);
	Language findByName(String name);
}
