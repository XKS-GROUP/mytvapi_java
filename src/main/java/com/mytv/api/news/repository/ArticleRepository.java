package com.mytv.api.news.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.news.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	Page<Article> findByTitleContainingOrContentContaining(String titre, String cont, Pageable p);
}
