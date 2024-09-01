package com.mytv.api.news.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.news.model.Article;
import com.mytv.api.news.model.FavArticle;

@Repository
public interface FavArticleRepository extends JpaRepository<FavArticle, Long> {

	List<FavArticle> findByUser(FirebaseUser u);
	List<FavArticle> findByArticle(Article art);
	Optional<FavArticle> findByUserAndArticle(FirebaseUser u, Article p);
	List<FavArticle> findByUid(String uid);
}
