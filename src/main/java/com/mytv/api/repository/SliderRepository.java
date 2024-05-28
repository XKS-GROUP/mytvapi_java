package com.mytv.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.util.Slider;

public interface SliderRepository extends JpaRepository<Slider, Long> {

	Page<Slider> findByNameContaining(String n, Pageable p);
	//Page<Slider> findByNameOrEmailContaining(String name,String email, Pageable p);

	Slider findByName(String name);

}
