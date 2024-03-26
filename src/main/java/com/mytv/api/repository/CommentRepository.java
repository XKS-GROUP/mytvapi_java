package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	Comment findByContenu(String name);

}
