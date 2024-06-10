package com.mytv.api.aws;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FolderRopository extends JpaRepository<Folder, Long> {

}
