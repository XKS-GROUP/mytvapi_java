package com.mytv.api.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.news.model.CategArticle;


public interface CategArticleRepository extends JpaRepository<CategArticle, Long> {

}
