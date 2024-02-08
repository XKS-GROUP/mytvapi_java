package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.View;
import com.mytv.api.repository.ViewRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ViewServiceImplement implements ViewService{

	@Autowired
	private ViewRepository viewRep;

	@Override
	public View create(View u) {

		return viewRep.save(u);
	}

	@Override
	public List<View> show() {
		return viewRep.findAll();
	}

	@Override
	public List<View> showById(Long id) {
		return null;
	}

	@Override
	public View upadte(Long id, View p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {

		viewRep.deleteById(id);
		return true;
	}
	
	
	
	
}
