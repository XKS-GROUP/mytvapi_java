package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestMedia.ContributorType;

@AutoConfiguration
public interface ContributorTypeService {
	
	ContributorType create(ContributorType u);
	List<ContributorType> show();
	List<ContributorType> showById(Long id);
	ContributorType upadte(Long id, ContributorType p);
	Boolean delete(Long id);
	
}
