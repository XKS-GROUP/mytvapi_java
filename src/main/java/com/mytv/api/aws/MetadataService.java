package com.mytv.api.aws;


import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MetadataService {
    public void upload(MultipartFile file) throws IOException;
    public String uploadR3(MultipartFile file, String dossier) throws IOException;
    public S3Object download(int id);
    public List<FileMeta> list();
}
