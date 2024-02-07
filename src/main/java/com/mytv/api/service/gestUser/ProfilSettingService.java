package com.mytv.api.service.gestUser;

import java.util.List;

import com.mytv.api.model.gestUser.ProfilSetting;

public interface ProfilSettingService {
	
	ProfilSetting create(ProfilSetting u);
	List<ProfilSetting> show();
	List<ProfilSetting> showById(Long id);
	ProfilSetting upadte(Long id, ProfilSetting p);
	Boolean delete(Long id);

}
