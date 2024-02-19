package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.Rating;

@Repository
public interface RatingRepository extends  JpaRepository<Rating, Long>{

}
