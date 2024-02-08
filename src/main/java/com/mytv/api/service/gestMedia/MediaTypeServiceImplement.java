package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.MediaType;
import com.mytv.api.repository.MediaTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaTypeServiceImplement implements MediaTypeService {
	
	@Autowired
	private MediaTypeRepository MediaTypeRep;

	@Override
	public MediaType create(MediaType u) {

		return MediaTypeRep.save(u);
	}

	@Override
	public List<MediaType> show() {
		return MediaTypeRep.findAll();
	}

	@Override
	public List<MediaType> showById(Long id) {
		return null;
	}

	@Override
	public MediaType upadte(Long id, MediaType p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		
		MediaTypeRep.deleteById(id);
		return true;
	}
	
	
	

}
