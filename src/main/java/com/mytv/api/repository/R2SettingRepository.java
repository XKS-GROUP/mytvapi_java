package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.util.R2cloudSetting;

public interface R2SettingRepository extends JpaRepository<R2cloudSetting, Long>{

	
}
