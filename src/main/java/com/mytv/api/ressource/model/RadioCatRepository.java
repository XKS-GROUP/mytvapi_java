package com.mytv.api.ressource.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadioCatRepository extends JpaRepository<CategoryRL, Long> {

	CategoryRL findByName(String name);

}
