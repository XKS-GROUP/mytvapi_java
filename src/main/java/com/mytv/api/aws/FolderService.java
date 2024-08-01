package com.mytv.api.aws;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FolderService {
	
	@Autowired
	FolderRopository rep;
	
    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    @Value("${aws.s3.accespoint}")
    private String accesspoint;
	
	
	public Folder create(Folder folder) {
		
		if(folder.getFolderpath().isEmpty()) {
			
			folder.setFolderpath(accesspoint);
		}
		else {
			folder.setFolderpath(accesspoint+"/"+folder.name);
		}
		
		return rep.save(folder);
		
	}
	
	
	public Folder showbyId(Long id) {
		
		return rep.findById(id).get();
		
	}
	
	public Optional<Folder> showbyname(String nom) {
		
		return rep.findByName(nom);
		
	}
	
	public Folder update(Long id, Folder folder) {
		
		return rep.save(folder);
	}
	

    public Folder createFolder(Folder Folder) {
        return rep.save(Folder);
    }
	
	public Page<Folder> show(Pageable p){
		
		return rep.findAll(p);
	}
	
	public void delete(Long id) {
		
		rep.deleteById(id);
	}
}
