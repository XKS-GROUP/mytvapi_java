package com.mytv.api.news.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.news.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Page<Article> findByTitleContainingOrContentContaining(String titre, String cont, Pageable p);
	
	Article findByTitle(String title);
}
