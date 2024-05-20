package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.util.Slider;

public interface SliderRepository extends JpaRepository<Slider, Long> {

	List<Slider> findByNameContaining(String n);

	Slider findByName(String name);

}
