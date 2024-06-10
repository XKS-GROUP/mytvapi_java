package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.util.SocialSetting;

public interface SocialSRepository extends JpaRepository<SocialSetting, Long>{

}
