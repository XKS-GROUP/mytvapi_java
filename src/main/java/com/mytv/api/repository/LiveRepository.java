package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Live;

public interface LiveRepository  extends JpaRepository<Live, Long>{

	List<Live> findByNameContaining(String name);

}
