package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikePodcast;

public interface LikePodcastRepository extends JpaRepository<LikePodcast, Long>{

}
