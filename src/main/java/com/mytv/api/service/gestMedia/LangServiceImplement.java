package com.mytv.api.service.gestMedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestMedia.Language;
import com.mytv.api.repository.LangRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LangServiceImplement implements LangService {
	
	@Autowired
	private LangRepository langRep;

	@Override
	public Language create(Language u) {
		return langRep.save(u);
	}

	@Override
	public List<Language> show() {
		return langRep.findAll();
	}

	@Override
	public List<Language> showById(Long id) {
		return null;
	}

	@Override
	public Language upadte(Long id, Language p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		langRep.deleteById(id);
		return true;
	}

}
