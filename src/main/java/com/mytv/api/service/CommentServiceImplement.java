package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.Comment;
import com.mytv.api.repository.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImplement implements CommentaireService{
	
	@Autowired
	private CommentRepository comRep;

	@Override
	public Comment create(Comment u) {

		return comRep.save(u);
	}

	@Override
	public List<Comment> show() {
		
		return comRep.findAll();
	
	}

	@Override
	public List<Comment> showById(Long id) {

		return null;
	}

	@Override
	public Comment upadte(Long id, Comment p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {

		comRep.deleteById(id);
		
		return true;
	}

}
