package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.Rating;

public interface RatingRepository extends  JpaRepository<Rating, Long>{

}
