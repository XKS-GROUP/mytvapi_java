package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.LiveTv;
import com.mytv.api.repository.LiveTvRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class LiveTvSetvice {
	
	
	@Autowired
	private LiveTvRepository rep;

	
	public LiveTv create(LiveTv g) {
		
		return rep.save(g);
		
	}
	
	public List<LiveTv> show() {
		
		return rep.findAll();
	}
	
	public List<LiveTv> showByNameContaining(String name) {
		
		return rep.findByNameContaining(name);
	}
	
	public LiveTv upadte(final Long id, LiveTv u) {
		
		LiveTv old = rep.findById(id).get();
		
		old = u;

		old.setIdLiveTv(id);
		
		return rep.save(old);
	}
	
	
	
	public Boolean delete(Long id) {
			
		rep.deleteById(id);
		
		return null;
		
	}

	public Optional<LiveTv> showById(final Long id) {
		
		return rep.findById(id);
		
	}

}
