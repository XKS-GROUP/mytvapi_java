package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Media;
import com.mytv.api.repository.MediaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaServiceImplement implements MediaService {
	
	@Autowired
	private MediaRepository MediaRep;

	@Override
	public Media create(Media u) {
		
		return MediaRep.save(u);
	}

	@Override
	public List<Media> show() {

		return MediaRep.findAll();
	}

	@Override
	public List<Media> showById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Media upadte(Long id, Media p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {

		MediaRep.deleteById(id);
		return true;
	}

}
