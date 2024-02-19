package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.Favorite;

@Repository
public interface FavoriteRepository extends  JpaRepository<Favorite, Long>{

}
