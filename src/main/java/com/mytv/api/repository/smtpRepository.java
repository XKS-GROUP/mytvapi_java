package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.util.SmtpSetting;

public interface smtpRepository  extends JpaRepository<SmtpSetting, Long>{

	
	
}
