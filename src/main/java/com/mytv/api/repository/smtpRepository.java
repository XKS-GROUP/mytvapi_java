package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.util.SmtpSetting;


@Repository
public interface smtpRepository  extends JpaRepository<SmtpSetting, Long>{

	
	
}
