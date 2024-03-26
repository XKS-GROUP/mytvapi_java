package com.mytv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.Download;
import com.mytv.api.repository.DownloadRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DownloadServiceImp implements DownloadService {

	@Autowired
	private DownloadRepository downRep;

	@Override
	public Download create(Download u) {

		return downRep.save(u);
	}

	@Override
	public List<Download> show() {

		return downRep.findAll();
	}

	@Override
	public List<Download> showById(Long id) {

		return null;

	}

	@Override
	public Download upadte(Long id, Download p) {

		return null;
	}

	@Override
	public Boolean delete(Long id) {

		downRep.deleteById(id);

		return true;
	}




}
