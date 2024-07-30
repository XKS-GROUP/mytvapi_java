package com.mytv.api.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FolderService {
	
	@Autowired
	FolderRopository rep;
	
	
	public Folder create(Folder folder) {
		
		folder.setFolderpath(null);
		return rep.save(folder);
		
	}
	
	public Folder update(Long id, Folder folder) {
		
		return rep.save(folder);
	}

	
	public Page<Folder> show(Pageable p){
		
		return rep.findAll(p);
	}
	
	public void delete(Long id) {
		
		rep.deleteById(id);
	}
}
