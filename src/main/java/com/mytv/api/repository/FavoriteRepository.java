package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.Favorite;

public interface FavoriteRepository extends  JpaRepository<Favorite, Long>{

}
