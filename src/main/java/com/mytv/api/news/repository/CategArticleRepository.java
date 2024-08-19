package com.mytv.api.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.news.model.CategArticle;

@Repository
public interface CategArticleRepository extends JpaRepository<CategArticle, Long> {

	
	CategArticle findByName(String title);
}
