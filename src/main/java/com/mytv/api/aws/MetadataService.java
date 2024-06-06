package com.mytv.api.aws;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;

public interface MetadataService {
    public void upload(MultipartFile file) throws IOException;

    public FileMeta uploadR3(MultipartFile file, String dossier) throws IOException;

    public S3Object download(int id);
    
    public void deteteFolder(String prefix);
    
    public List<FileMeta> list();
    
    Page<FileMeta> listWithPage(Pageable p);

	Optional<FileMeta> showById(Long id);

	FileMeta create(FileMeta fm);
}
