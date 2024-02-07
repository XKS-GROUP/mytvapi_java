package com.mytv.api.service;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mytv.api.model.Download;


@AutoConfiguration
public interface DownloadService {
	
	Download create(Download u);
	List<Download> show();
	List<Download> showById(Long id);
	Download upadte(Long id, Download p);
	Boolean delete(Long id);

}
