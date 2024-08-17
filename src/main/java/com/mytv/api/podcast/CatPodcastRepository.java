package com.mytv.api.podcast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatPodcastRepository extends JpaRepository<CatPodcast, Long> {

	CatPodcast findByName(String name);
}
