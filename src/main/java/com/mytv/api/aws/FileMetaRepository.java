package com.mytv.api.aws;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaRepository extends JpaRepository<FileMeta, Integer> {

	List<FileMeta> findByFileName(String name);

	List<FileMeta> findByVersion(String name);

	List<FileMeta> findByFileNameContaining(String fileName);

	int deleteByFileName(String name);

	void deleteByVersion(String version);

	Page<FileMeta> findAll(Pageable p);

}
