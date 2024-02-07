package com.mytv.api.service.gestUser;

import java.util.List;

import com.mytv.api.model.gestUser.Profil;

public interface ProfilService {
	
	Profil create(Profil u);
	List<Profil> show();
	List<Profil> showById(Long id);
	Profil upadte(Long id, Profil p);
	Boolean delete(Long id);

}
