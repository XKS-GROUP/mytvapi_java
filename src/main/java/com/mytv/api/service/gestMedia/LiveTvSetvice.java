package com.mytv.api.service.gestMedia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<LiveTv> showPage(Pageable p) {

		return rep.findAll(p);
	}

	public List<LiveTv> search(String val, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p);
	}

	public LiveTv update(final Long id, LiveTv u) {

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
	
	public List<LiveTv> showByLangue(Long id, Pageable p){
		
		return rep.findAll().stream()
                     .filter(f -> f.getLangue().contains(id))
                     .collect(Collectors.toList());
		
	};
	
	public List<LiveTv> showByGenre(Long id, Pageable p){
		
		return rep.findAll().stream()
                     .filter(f -> f.getIdcategories().contains(id))
                     .collect(Collectors.toList());
		
	};

	public List<LiveTv> searchbyLangue(String val, Long langue, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getLangue().contains(langue))
                .collect(Collectors.toList());
	}
	
	public List<LiveTv> searchByGenre(String val,Long genre, Pageable p) {

		return rep.findByNameOrOverviewContaining(val, val, p).stream()
                .filter(f -> f.getIdcategories().contains(genre))
                .collect(Collectors.toList());
	}
	
	
	public LiveTv findByName(String name) {
		
		return rep.findByName(name);
	}

}
