package com.mytv.api.ressource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.ressource.model.CategoryRL;

@Repository
public interface CategoryLrRepository extends  JpaRepository<CategoryRL, Long> {

	CategoryRL findByName(String name);

}
