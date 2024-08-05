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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private AmazonS3ServiceImpl awsImpService;
    
    @Autowired
    private AmazonS3ServiceImpl awsImp;
    
    @Autowired
    private FileMetaRepository fileMetaRepository;
    
    @Autowired
    private FolderService folderService;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    @Value("${aws.s3.accespoint}")
    private String accesspoint;

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
        String fileName = String.format("%s", file.getOriginalFilename()).replaceAll("[^a-zA-Z0-9]", "");

        // upload du fichier dans R2
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName.replaceAll("\\s+", ""), Optional.of(metadata), file.getInputStream());

        // Enregistrement de la trace dans la db
       
        //generé une une présigné unique pour un fichier, accessible pour une durée de 6 jours max
        String presign = awsImp.generatePresignedUrl(bucketName, fileName, 10080).toString();
        
        
       
        
        fileMetaRepository.save(new FileMeta(fileName.replaceAll("\\s+", ""), path, putObjectResult.getMetadata().getVersionId(), presign));

    }

    @Override
    public FileMeta uploadR3(MultipartFile file, String dossier) throws IOException {

    	
    	//nom du dossier par defaut
    	if(dossier.isEmpty()) {

    		dossier="SansNom";
    	}


    	//seulement au cas ou le fichier est vide 
        if (file.isEmpty()) {
			throw new IllegalStateException("Vous tentez d'uploader un fichier vide");
		}

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String pathFile = String.format("%s/%s",dossier, UUID.randomUUID());
        
        String path =  bucketName+"/"+ pathFile;
        String fileName = String.format("%s", file.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.]", "");

        // upload du fichier dans R2
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        // Enregistrement de la trace dans la db
        //String presign = awsImp.generatePresignedUrl(bucketName, pathFile+"/"+fileName.replaceAll("[^a-zA-Z0-9.]", ""), 10080).toString();
        
        //ici j ai placé une url normal basé sur le domaine d acces de l api + le chemin vers le fichier sur R2
        //Ceci servira de lien d acces pour le fichier
        String presign = accesspoint+"/"+ pathFile+"/"+fileName;
        
        FileMeta dataMeta = new FileMeta(fileName.replaceAll("\\s+", "-"), path, putObjectResult.getMetadata().getVersionId(), presign);

       
        
        //Recuperation de la taille et du format du fichier
        dataMeta.setSize(file.getSize());
        dataMeta.setFormat(file.getContentType());
        
        fileMetaRepository.save(dataMeta);
        
        //String filepath="";
        //filepath = dataMeta.getFilePath()+"/"+dataMeta.getFileName();

        return dataMeta;

        
    }
    
    @Override
    public FileMeta createFile(MultipartFile file, Long folderId) throws IOException {

    	Folder folder = folderService.showbyId(folderId) ;

    	//seulement au cas ou le fichier est vide 
        if (file.isEmpty()) {
			throw new IllegalStateException("Vous tentez d'uploader un fichier vide");
		}

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String pathFile = String.format("%s/%s",folder.name, UUID.randomUUID());
        
        String path =  bucketName+"/"+ pathFile;
        String fileName = String.format("%s", file.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.]", "");

        // upload du fichier dans R2
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        // Enregistrement de la trace dans la db
        //String presign = awsImp.generatePresignedUrl(bucketName, pathFile+"/"+fileName.replaceAll("[^a-zA-Z0-9.]", ""), 10080).toString();
        
        //ici j ai placé une url normal basé sur le domaine d acces de l api + le chemin vers le fichier sur R2
        //Ceci servira de lien d acces pour le fichier
        String presign = accesspoint+"/"+ pathFile+"/"+fileName;
        
        FileMeta dataMeta = new FileMeta(fileName.replaceAll("\\s+", "-"), path, putObjectResult.getMetadata().getVersionId(), presign);

        dataMeta.setFolder(folder);
        
        //Recuperation de la taille et du format du fichier
        dataMeta.setSize(file.getSize());
        dataMeta.setFormat(file.getContentType());
        
        fileMetaRepository.save(dataMeta);
        
        //String filepath="";
        //filepath = dataMeta.getFilePath()+"/"+dataMeta.getFileName();

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
    
    public Page<FileMeta> listWithPage(Pageable p) {
       
        return fileMetaRepository.findAll(p);
        
    }
    

    public List<FileMeta> lisByName(String nom){

    	return fileMetaRepository.findByFileName(nom);
    }

    
    public void createFolder(String nom) {
    	
    	awsImpService.createFolder(nom, bucketName);
    }
    
    
    public Page<String> listFolder(String prefix, Pageable p) {
    	
    	return awsImpService.listFolders(prefix, bucketName, p);
    	
    }
    
    @Override
    public void deteteFolder(String prefix) {
    	
    	awsImpService.deleteFolder(bucketName, prefix);
    	
    }
    
    
    
    /*
     * 
     * Ensemble des uploads specifiques
     * 
     * 
     */


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
    
    @Override
    public Optional<FileMeta> showById(Long id) {
    	
    	return fileMetaRepository.findById(null);
    }
    
    @Override
    public FileMeta create(FileMeta fm) {
    	
    	return fileMetaRepository.save(fm);
    }
}
