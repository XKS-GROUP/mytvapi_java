package com.mytv.api.setting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.setting.model.SmtpSetting;


@Repository
public interface smtpRepository  extends JpaRepository<SmtpSetting, Long>{

	
	
}
