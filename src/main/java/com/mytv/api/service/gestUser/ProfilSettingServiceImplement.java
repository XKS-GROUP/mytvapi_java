package com.mytv.api.service.gestUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.ProfilSetting;
import com.mytv.api.repository.ProfilSettingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfilSettingServiceImplement implements ProfilSettingService {
	
	@Autowired
	private ProfilSettingRepository prosRep;

	@Override
	public ProfilSetting create(ProfilSetting p) {

		return prosRep.save(p);
	}

	@Override
	public List<ProfilSetting> show() {
		return prosRep.findAll();
	}

	@Override
	public List<ProfilSetting> showById(Long id) {
		
		return null;//prosRep.findById(id);
	}

	@Override
	public ProfilSetting upadte(Long id, ProfilSetting p) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		
		prosRep.deleteById(id);
		return true;
	}

}
