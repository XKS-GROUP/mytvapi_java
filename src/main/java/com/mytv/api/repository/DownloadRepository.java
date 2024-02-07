package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.Download;

public interface DownloadRepository extends  JpaRepository<Download, Long>{

}
