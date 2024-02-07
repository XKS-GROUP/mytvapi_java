package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.MediaSource;

public interface MediaSourceRepository extends  JpaRepository<MediaSource, Long>{

}
