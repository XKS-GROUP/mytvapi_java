package com.mytv.api.aws;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;

public interface MetadataService {
    public void upload(MultipartFile file) throws IOException;

    public FileMeta uploadR3(MultipartFile file, String dossier) throws IOException;

    public S3Object download(int id);

    public List<FileMeta> list();
}
