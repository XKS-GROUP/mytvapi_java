package com.mytv.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.service.gestMedia.PaysService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/user")
public class UserControlleur {
	PaysService PaysService;
	
	@GetMapping("userInfo")
	public List<Pays> userInfo(){
		
		return PaysService.show();
	}
	
	@PutMapping("updateUser")
	public List<Pays> updateUser(){
		
		return PaysService.show();
	}
	
	@DeleteMapping("deleteCurrentAcountUser")
	public List<Pays> deleteUser(){
		
		return PaysService.show();
	}
	
	@GetMapping("list-profile")
	public List<Pays> listProfile(){
		
		return PaysService.show();
	}
	
	@DeleteMapping("delete-profile/{id}")
	public List<Pays> deleteProfileById(){
		
		return PaysService.show();
	}
	
	//Multie Media
	
	@GetMapping("allFavorie")
	public List<Pays> allFavorite(){
		
		return PaysService.show();
	}
	
	@GetMapping("allFavorieByProfile")
	public List<Pays> allFavorieByProfile(){
		
		return PaysService.show();
	}
	
	@GetMapping("allDownload")
	public List<Pays> allDownload(){
		
		return PaysService.show();
	}
	
	@GetMapping("allDownloadByProfil")
	public List<Pays> allDownloadByProfil(){
		
		return PaysService.show();
	}
	
	
	//Payment et Facture
	

}
