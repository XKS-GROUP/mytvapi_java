package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Media;

public interface MediaRepository extends  JpaRepository<Media, Long> {

}
