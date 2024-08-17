package com.mytv.api.ressource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.repository.LangRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LangueService {

	@Autowired
	private LangRepository langRep;


	public Language create(Language u) {
		
		return langRep.save(u) ;
	}


	public List<Language> show() {
		
		return langRep.findAll();
	}
	
	
	public Page<Language> showPage(Pageable p) {
		return langRep.findAll(p);
	}

	public List<Language> showByName(String name, Pageable p) {

		return langRep.findByName(name, p);

	}
	
	public Language showByName(String name) {

		return langRep.findByName(name);

	}
	
	public Language showBySlug(String slug) {

		return langRep.findBySlug(slug);

	}
	
	public Optional<Language> showById(final Long id) {

		return langRep.findById(id);

	}

	public Language upadte(final Long id, Language u) {

		Language old = langRep.findById(id).get();

		old = u;

		old.setIdLang(id);

		return langRep.save(old);
	}


	public Boolean delete(Long id) {
		langRep.deleteById(id);
		return true;
	}

}
