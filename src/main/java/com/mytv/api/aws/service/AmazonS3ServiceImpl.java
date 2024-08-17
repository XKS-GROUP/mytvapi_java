package com.mytv.api.aws.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

//import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Autowired
    private AmazonS3 amazonS3;


    @Override
    public PutObjectResult upload(
            String path,
            String fileName,
            Optional<Map<String, String>> optionalMetaData,
            InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        //log.debug("Path: " + path + ", FileName:" + fileName);
        return amazonS3.putObject(path, fileName, inputStream, objectMetadata);
    }

    public void deleteR2(String path, String fileName)
    {
    	amazonS3.deleteObject(path, fileName);
    }
    
    
    @Override
    public void createFolder(String folderName, String bucketName) {
        // S3 folders are created by adding a trailing slash
        String folderKey = folderName.endsWith("/") ? folderName : folderName + "/";

        // Metadata for the folder
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        // Create an empty content
        amazonS3.putObject(bucketName, folderKey, new ByteArrayInputStream(new byte[0]), metadata);
    }
    
    
    public Page<String> listFolders(String prefix, String bucketName, Pageable p) {
        Set<String> folders = new HashSet <>();

        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(prefix)
                .withDelimiter("/");

        ListObjectsV2Result result = amazonS3.listObjectsV2(request);

        for (String commonPrefix : result.getCommonPrefixes()) {
            folders.add(commonPrefix);
        }

        //return folders;
        
        PageImpl<String> res = new PageImpl<String>(
        		     folders.stream().toList()
				   , p
				   , folders.size());
			
		return res;
    }

    
    public void deleteFolder(String bucketName, String name) {
    	
    	amazonS3.deleteObject(bucketName, name);
    	
    }

    @Override
	public S3Object download(String path, String fileName) {
    	
    	
        return amazonS3.getObject(path, fileName);
    }

    public List<Bucket> allFolder() {
    	
    	
    	return amazonS3.listBuckets();
    }
    
    public URL urlFichier(String path, String fileName) {
    	
    	return amazonS3.getUrl(path, fileName);
    }
    
    //GENERER DES URLS PRESIGNES
    
    
    public URL generatePresignedUrl(String bucketName, String objectKey, int expirationInMinutes) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * expirationInMinutes;
        
        
        //Expire en 2038
        //expTimeMillis = new Date().getTime() + (1000L * 60 * 60 * 24 * 365 * 20);//Commenter cette ligne si vous vouler que le paramettre expirationInMinutes soit pris en compte
        
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    }
    
}
