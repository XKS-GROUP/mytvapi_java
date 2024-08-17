package com.mytv.api.util;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {

	Page<Slider> findByNameContaining(String n, Pageable p);
	Slider findByName(String name);

}
