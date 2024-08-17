package com.mytv.api.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.util.model.PubliciteCat;


@Repository
public interface PubliciteCatRepository extends  JpaRepository<PubliciteCat, Long>{


}
