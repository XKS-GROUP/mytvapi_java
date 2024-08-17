package com.mytv.api.setting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.setting.model.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long>{

}
