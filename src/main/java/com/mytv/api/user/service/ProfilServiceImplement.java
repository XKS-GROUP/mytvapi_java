package com.mytv.api.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.user.model.Profil;
import com.mytv.api.user.repository.ProfilRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfilServiceImplement implements ProfilService {

	@Autowired

	private ProfilRepository proRep;

	@Override
	public Profil create(Profil p) {

		return proRep.save(p);
	}

	@Override
	public List<Profil> show() {

		return proRep.findAll();
	}

	@Override
	public Optional<Profil>  showById(Long id) {

		return proRep.findById(id);
	}

	@Override
	public Profil upadte(Long id, Profil p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {

		proRep.deleteById(id);

		return true;
	}

}
