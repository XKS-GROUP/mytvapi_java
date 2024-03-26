package com.mytv.api.service.gestUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.Profil;
import com.mytv.api.repository.ProfilRepository;

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
	public List<Profil> showById(Long id) {

		return null;
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
