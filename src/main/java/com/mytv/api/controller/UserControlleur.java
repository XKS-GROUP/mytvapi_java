package com.mytv.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mytv.api.model.gestMedia.Pays;
import com.mytv.api.service.gestMedia.PaysService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/user")
public class UserControlleur {
	PaysService PaysService;
	
	@Tag(name = "Profil Abonne")
	@GetMapping("userInfo")
	public List<Pays> userInfo(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@PutMapping("updateUser")
	public List<Pays> updateUser(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@DeleteMapping("deleteCurrentAcountUser")
	public List<Pays> deleteUser(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@GetMapping("list-profile")
	public List<Pays> listProfile(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@DeleteMapping("delete-profile/{id}")
	public List<Pays> deleteProfileById(){
		
		return PaysService.show();
	}
	
	//Multie Media
	@Tag(name = "Profil Abonne")
	@GetMapping("allFavorie")
	public List<Pays> allFavorite(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@GetMapping("allFavorieByProfile")
	public List<Pays> allFavorieByProfile(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@GetMapping("allDownload")
	public List<Pays> allDownload(){
		
		return PaysService.show();
	}
	
	@Tag(name = "Profil Abonne")
	@GetMapping("allDownloadByProfil")
	public List<Pays> allDownloadByProfil(){
		
		return PaysService.show();
	}
	
	
	//Payment et Facture
	

}
