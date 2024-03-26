package com.mytv.api.aws;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaRepository extends CrudRepository<FileMeta, Integer> {

	List<FileMeta> findByFileName(String name);

	List<FileMeta> findByVersion(String name);

	List<FileMeta> findByFileNameContaining(String fileName);

	int deleteByFileName(String name);

	void deleteByVersion(String version);

}
