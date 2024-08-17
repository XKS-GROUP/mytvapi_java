package com.mytv.api.aws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.aws.model.Folder;


@Repository
public interface FolderRopository extends JpaRepository<Folder, Long> {

	
	Optional<Folder> findByName(String name);
	List<Folder> findByNameContaining(String name);
}
