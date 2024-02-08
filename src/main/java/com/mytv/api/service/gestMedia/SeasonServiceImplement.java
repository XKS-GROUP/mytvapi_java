package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Season;
import com.mytv.api.repository.SeasonRepository;

@Service
public class SeasonServiceImplement implements SeasonService{
	
	@Autowired
	private SeasonRepository seasRep;

	@Override
	public Season create(Season u) {
		
		return seasRep.save(u);
	}

	@Override
	public List<Season> show() {
		// TODO Auto-generated method stub
		return seasRep.findAll();
	}

	@Override
	public List<Season> showById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Season upadte(Long id, Season p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		seasRep.deleteById(id);
		return null;
	}

}
