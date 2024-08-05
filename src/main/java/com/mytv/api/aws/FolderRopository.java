package com.mytv.api.aws;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FolderRopository extends JpaRepository<Folder, Long> {

	
	Optional<Folder> findByName(String name);
	List<Folder> findByNameContaining(String name);
}
