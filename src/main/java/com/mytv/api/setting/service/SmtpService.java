package com.mytv.api.setting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.setting.model.SmtpSetting;
import com.mytv.api.setting.repository.smtpRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SmtpService {

	
	@Autowired
	private smtpRepository rep;


	public SmtpSetting create(SmtpSetting s) {

		return rep.save(s);

	}

	public List<SmtpSetting> show() {

		return rep.findAll();
	}

	public SmtpSetting upadte(final Long id, SmtpSetting s) {

		s.setId(id);
		return rep.save(s);
	}

	public void delete(Long id) {

		rep.deleteById(id);
	}

	public Optional<SmtpSetting> showById(Long id) {

		return rep.findById(id);

	}

}
