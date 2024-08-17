package com.mytv.api.user.service;

import java.util.List;
import java.util.Optional;

import com.mytv.api.user.model.Profil;

public interface ProfilService {

	Profil create(Profil u);
	List<Profil> show();
	Optional<Profil> showById(Long id);
	Profil upadte(Long id, Profil p);
	Boolean delete(Long id);

}
