package com.mytv.api.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.setting.model.AdmodSetting;
import com.mytv.api.setting.model.FirebaseSetting;
import com.mytv.api.setting.model.R2cloudSetting;
import com.mytv.api.setting.model.Setting;
import com.mytv.api.setting.model.SocialSetting;
import com.mytv.api.setting.model.TmdbSetting;
import com.mytv.api.setting.repository.AdmodSettingRepository;
import com.mytv.api.setting.repository.FirebaseRepository;
import com.mytv.api.setting.repository.R2SettingRepository;
import com.mytv.api.setting.repository.SettingRepository;
import com.mytv.api.setting.repository.SocialSRepository;
import com.mytv.api.setting.repository.TmbRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SettingService {
	
	@Autowired
	SocialSRepository socialRep;
	
	@Autowired
	TmbRepository tmdbRep;
	
	@Autowired
	AdmodSettingRepository admodRep;
	
	@Autowired
	FirebaseRepository firebaseRep;
	
	@Autowired
	R2SettingRepository R2Rep;
	
	@Autowired
	SettingRepository settingRep;
	
	
	
	/*
	 * 
	 * paramettre ADMOD
	 * 
	 * 
	 */
	public Setting add_setting(Setting r) {
		
		if(settingRep.findAll().isEmpty()) {
			return settingRep.save(r);
		}
		else {
			//Je recupere l id u paramettre existant 
			Long id = settingRep.findAll().get(0).getId();
			r.setId(id);
			return settingRep.save(r);
		}
	}
	
	public Setting show_byId_setting(Long id) {
		
		return settingRep.findById(id).get();
	}
	
	public Setting update_setting(Long id, Setting r) {
		
		r.setId(id);
		return settingRep.save(r);
	}
	
	public void delete_setting(Long id) {
		
		settingRep.deleteById(id);
	
	}
	
	public List<Setting> list_setting() {
		
		return settingRep.findAll();
	}
	
	
	
	/*
	 * 
	 * 
	 * paramettre ADMOD
	 * 
	 * 
	 */
	public AdmodSetting add_admod(AdmodSetting r) {
		
		if(admodRep.findAll().isEmpty()) {
			return admodRep.save(r);
		}
		else {
			//Je recupere l id u paramettre existant 
			Long id = admodRep.findAll().get(0).getId();
			r.setId(id);
			return admodRep.save(r);
		}
		
	}
	
	public AdmodSetting show_byId_admod(Long id) {
		
		return admodRep.findById(id).get();
	}
	
	public AdmodSetting update_admod(Long id, AdmodSetting r) {
		
		r.setId(id);
		return admodRep.save(r);
	}
	
	public void delete_admod(Long id) {
		
		admodRep.deleteById(id);
	
	}
	
	public List<AdmodSetting> list_admod() {
		
		return admodRep.findAll();
	}
	
	
	
	
	/*
	 * 
	 * paramettre R2
	 * 
	 * 
	 */
	public R2cloudSetting add_R2Setting(R2cloudSetting r) {
		
		if(R2Rep.findAll().isEmpty()) {
			return R2Rep.save(r);
		}
		else {
			//Je recupere l id u paramettre existant 
			Long id = R2Rep.findAll().get(0).getId();
			r.setId(id);
			return R2Rep.save(r);
		}
	}
	
	public R2cloudSetting show_byId_R2Setting(Long id) {
		
		return R2Rep.findById(id).get();
	}
	
	public R2cloudSetting update_R2Setting(Long id, R2cloudSetting r) {
		
		r.setId(id);
		return R2Rep.save(r);
	}
	
	public void delete_R2Setting(Long id) {
		
		R2Rep.deleteById(id);
	
	}
	
	public List<R2cloudSetting> list_R2Setting() {
		
		return R2Rep.findAll();
	}
	
	
	
	
	/*
	 * 
	 * paramettre FireBase
	 * 
	 * 
	 */
	public FirebaseSetting add_firebase(FirebaseSetting r) {
		
		if(firebaseRep.findAll().isEmpty()) {
			return firebaseRep.save(r);
		}
		else {
			//Je recupere l id u paramettre existant 
			Long id = firebaseRep.findAll().get(0).getId();
			r.setId(id);
			return firebaseRep.save(r);
		}
	}
	
	public FirebaseSetting show_byId_firebase(Long id) {
		
		return firebaseRep.findById(id).get();
	}
	
	public FirebaseSetting update_firebase(Long id, FirebaseSetting r) {
		
		r.setId(id);
		return firebaseRep.save(r);
	}
	
	public void delete_firebase(Long id) {
		
		firebaseRep.deleteById(id);
	
	}
	
	public List<FirebaseSetting> list_firebase() {
		
		return firebaseRep.findAll();
	}
	
	
	
	/*
	 * 
	 * Social Setting
	 * 
	 * 
	 */
	
	public SocialSetting add_Social(SocialSetting r) {
		
		if(socialRep.findAll().isEmpty()) {
			return socialRep.save(r);
		}
		else {
			//Je recupere l id u paramettre existant 
			Long id = socialRep.findAll().get(0).getId();
			r.setId(id);
			return socialRep.save(r);
		}
		
	}
	
	
	public SocialSetting show_byId_Social(Long id) {
		
		return socialRep.findById(id).get();
	}
	
	public SocialSetting update_Social(Long id, SocialSetting r) {
		
		r.setId(id);
		return socialRep.save(r);
	}
	
	public void delete_Social(Long id) {
		
		socialRep.deleteById(id);
	
	}
	
	public List<SocialSetting> list_Social() {
		
		return socialRep.findAll();
	}
	
	
	
	
	/*
	 * 
	 * 
	 * TMDB 
	 * 
	 * 
	 */
	
	public TmdbSetting add_TMDB(TmdbSetting r) {
		
		if(tmdbRep.findAll().isEmpty()) {
			return tmdbRep.save(r);
		}
		else {
			//Je recupere l id u paramettre existant 
			Long id = tmdbRep.findAll().get(0).getId();
			r.setId(id);
			return tmdbRep.save(r);
		}
	}
	
	
	public TmdbSetting show_byId_TMDB(Long id) {
		
		return tmdbRep.findById(id).get();
	}
	
	
	public TmdbSetting update_TMDB(Long id, TmdbSetting r) {
		
		r.setId(id);
		return tmdbRep.save(r);
	}
	
	public void delete_TMDB(Long id) {
		
		tmdbRep.deleteById(id);
	
	}
	
	public List<TmdbSetting> list_TMDB() {
		
		return tmdbRep.findAll();
	}
	

}
