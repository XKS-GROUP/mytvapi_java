package com.mytv.api.service.gestMedia;

import java.util.List;

import com.mytv.api.model.gestMedia.Contributor;

public interface ContributorService {
	
	
	Contributor create(Contributor u);
	List<Contributor> show();
	List<Contributor> showById(Long id);
	Contributor upadte(Long id, Contributor p);
	Boolean delete(Long id);
	

}
