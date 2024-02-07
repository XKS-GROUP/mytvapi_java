package com.mytv.api.service;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.Comment;



@AutoConfiguration
public interface CommentaireService {

	Comment create(Comment u);
	List<Comment> show();
	List<Comment> showById(Long id);
	Comment upadte(Long id, Comment p);
	Boolean delete(Long id);
}
