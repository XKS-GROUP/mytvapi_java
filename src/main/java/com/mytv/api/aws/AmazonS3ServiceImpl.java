package com.mytv.api.aws;

import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private FileMetaRepository fileMetaRepository;

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
