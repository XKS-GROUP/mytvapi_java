package com.mytv.api.aws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private FileMetaRepository fileMetaRepository;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.s3.bucket.pathMovie}")
    private String pathMovie;

    @Value("${aws.s3.bucket.pathSerie}")
    private String pathSerie;

    @Value("${aws.s3.bucket.pathPodcast}")
    private String pathPodcast;

    @Value("${aws.s3.bucket.pathUserImg}")
    private String pathUserImg;

    @Value("${aws.s3.bucket.pathEpisode}")
    private String pathEpisode;

    @Override
    public void upload(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
			throw new IllegalStateException("Cannot upload empty file");
		}

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename()).replaceAll(" ", "_");

        // upload du fichier dans R2
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName.replaceAll("\\s+", ""), Optional.of(metadata), file.getInputStream());

        // Enregistrement de la trace dans la db

        fileMetaRepository.save(new FileMeta(fileName.replaceAll("\\s+", ""), path, putObjectResult.getMetadata().getVersionId()));

    }

    @Override
    public FileMeta uploadR3(MultipartFile file, String dossier) throws IOException {

    	file.getSize();
    	if(dossier.isEmpty()) {

    		dossier="SansNom";
    	}

    	String filepath="";

        if (file.isEmpty()) {
			throw new IllegalStateException("Vous tentez d'uploader un fichier vide");
		}

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName+"/"+dossier, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        // upload du fichier dans R2
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName.replaceAll("\\s+", "-"), Optional.of(metadata), file.getInputStream());

        // Enregistrement de la trace dans la db
        FileMeta dataMeta = new FileMeta(fileName.replaceAll("\\s+", "-"), path, putObjectResult.getMetadata().getVersionId());

        dataMeta.setSize(file.getSize());
        dataMeta.setFormat(file.getContentType());
        fileMetaRepository.save(dataMeta);

        filepath = dataMeta.getFilePath().replaceAll("\\s+", "-")+"/"+dataMeta.getFileName();

        return dataMeta;

    }

    @Override
    public S3Object download(int id) {
        FileMeta fileMeta = fileMetaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return amazonS3Service.download(fileMeta.getFilePath(),fileMeta.getFileName());
    }

    @Override
    public List<FileMeta> list() {
        List<FileMeta> metas = new ArrayList<>();
        fileMetaRepository.findAll().forEach(metas::add);
        return metas;
    }

    public List<FileMeta> lisByName(String nom){

    	return fileMetaRepository.findByFileName(nom);
    }



    public void uploadMovie(MultipartFile file) throws IOException{

    	this.uploadR3(file, pathMovie);

    }

    public void uploadSerie(MultipartFile file) throws IOException{

    	this.uploadR3(file, pathSerie);

    }

    public void uploadPodcast(MultipartFile file) throws IOException{

    	this.uploadR3(file, pathPodcast);

    }

    public void uploadEpisode(MultipartFile file) throws IOException{

    	this.uploadR3(file, pathEpisode);

    }

    public void uploadUserIMG(MultipartFile file) throws IOException{

    	this.uploadR3(file, pathUserImg);

    }

    public void deleteByName(String nom) {

    	fileMetaRepository.deleteByFileName(nom);
    }

    public void deleteByVersion(String version) {

    	fileMetaRepository.deleteByVersion(version);
    }

    public void updateFile(int id) {


    }
}
