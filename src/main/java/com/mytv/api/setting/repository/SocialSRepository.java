package com.mytv.api.setting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.setting.model.SocialSetting;

public interface SocialSRepository extends JpaRepository<SocialSetting, Long>{

}
