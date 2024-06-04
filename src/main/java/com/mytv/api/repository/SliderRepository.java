package com.mytv.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.util.Slider;


@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {

	Page<Slider> findByNameContaining(String n, Pageable p);
	Slider findByName(String name);

}
