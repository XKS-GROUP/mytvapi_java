package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.MediaSource;
import com.mytv.api.repository.MediaSourceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaSourceServiceImplement implements MediaSourceService{
	
	@Autowired
	MediaSourceRepository msRep;

	@Override
	public MediaSource create(MediaSource u) {
		return msRep.save(u);
	}

	@Override
	public List<MediaSource> show() {
		return msRep.findAll();
	}

	@Override
	public List<MediaSource> showById(Long id) {
		return null;
	}

	@Override
	public MediaSource upadte(Long id, MediaSource p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		msRep.deleteById(id);
		return true;
	}

}
