package com.mytv.api.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.mytv.api.config.UserSessionTracker;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.firebase.service.FirebaseService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.user.model.Profil;
import com.mytv.api.user.repository.JwtRepository;
import com.mytv.api.user.repository.ProfilRepository;
import com.mytv.api.user.repository.UserRepository;
import com.mytv.api.user.service.ProfilService;
import com.mytv.api.user.service.WUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/v1/front/user")
public class UserControlleur {
	
	@Autowired
	WUserService userService;
	@Autowired
	FirebaseService firbaseService;
	
	//@Autowired
    //private UserSessionTracker sessionTracker;
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	ProfilService profilService;
	
	@Autowired
	ProfilRepository profilRep;
	
	@Autowired
	JwtRepository jwtRep;
	
	
	
	//Affiche info utilisateur
	@Tag(name = "Profil Abonne")
	@GetMapping("info")
	public ResponseEntity<Object> retrieveUserProfile(){
		
		FirebaseUser u = (FirebaseUser) firbaseService.getUser();
		
		return EntityResponse.generateResponse("Abonne Profil Info : "+u.getUsername(), HttpStatus.OK, 
				(FirebaseUser) firbaseService.getUser() );
	}

	//Gestion des profil
	
	//List des profils
	@Tag(name = "Profil Abonne DOWN (gere par firebase maintenant )")
	@GetMapping("list-profile")
	public List<Profil> listProfile(){
		FirebaseUser u = (FirebaseUser) firbaseService.getUser();
		return profilRep.findByUser(u);
	}
	
	//Creation de pro
	@Tag(name = "Profil Abonne")
	@PostMapping("profile/create")
	public ResponseEntity<Object> createProfil(@Valid @RequestBody Profil p){
		
		int lim = 2;
		FirebaseUser u = (FirebaseUser) firbaseService.getUser();
		if(lim == 4) {
			
			return EntityResponse.generateResponse("Profil ERREUR ", HttpStatus.BAD_REQUEST, " le nombre limite de profile est atteint ");
			
		}
		else if(profilRep.findByProfilName(p.getProfilName()) != null) {
			
			return EntityResponse.generateResponse("Profil ERREUR ", HttpStatus.BAD_REQUEST, " ce nom de profil existe déja");
			
		}
		else if ( p.getProfilName().toString() == "kid" || p.getProfilName().toString() == "kids"  ){
			
			System.out.println("JE SUIS  NOM ++ "+p.getProfilName());
			return EntityResponse.generateResponse("Profil ERREUR ", HttpStatus.BAD_REQUEST, " vous ne pouvez creer un profil avec ce nom, celui ci est reservé ");
		}
		else {
			p.setUser(u);
			return EntityResponse.generateResponse("User Profile", HttpStatus.OK, profilService.create(p));
		}
		
	}


	//Payment et Facture


}
