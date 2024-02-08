package com.mytv.api.service;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.View;

@AutoConfiguration
public interface ViewService {
	
	View create(View u);
	List<View> show();
	List<View> showById(Long id);
	View upadte(Long id, View p);
	Boolean delete(Long id);

}
