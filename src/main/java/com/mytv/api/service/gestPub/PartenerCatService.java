package com.mytv.api.service.gestPub;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.gestPub.PartenerCat;

@AutoConfiguration
public interface PartenerCatService {

	PartenerCat create(PartenerCat u);
	List<PartenerCat> show();
	List<PartenerCat> showById(Long id);
	PartenerCat upadte(Long id, PartenerCat p);
	Boolean delete(Long id);

}
