package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikeSerie;

public interface LikeSerieRepository extends JpaRepository<LikeSerie, Long>{

}
