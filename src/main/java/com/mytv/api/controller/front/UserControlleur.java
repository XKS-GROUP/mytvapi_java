package com.mytv.api.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.config.UserSessionTracker;
import com.mytv.api.ressource.model.Pays;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.user.model.Profil;
import com.mytv.api.user.model.User;
import com.mytv.api.user.repository.JwtRepository;
import com.mytv.api.user.repository.ProfilRepository;
import com.mytv.api.user.repository.UserRepository;
import com.mytv.api.user.service.ProfilService;
import com.mytv.api.user.service.WUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
    private UserSessionTracker sessionTracker;
	
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
		return EntityResponse.generateResponse("Abonne Profil Info : "+userService.findCurrentUser().getUsername(), HttpStatus.OK, userService.findCurrentUser());
	}

	//Update User
	@Tag(name = "Profil Abonne")
	@PutMapping("update/{id}")
	public ResponseEntity<Object> updateProfilebyId(@PathVariable Long id, @Valid @RequestBody User u){

		if(id <= 0) {
			return EntityResponse.generateResponse("User MAJ ERREUR ", HttpStatus.BAD_REQUEST, " l'id utilisateur ne peut être vide ");
		}
		else {

			userService.updateByid(id, u) ;

			return EntityResponse.generateResponse("User "+u.getUsername()+" a été mis a jour ", HttpStatus.OK, userService.updateByid(id, u));
		}

	}
    
	//Delete le compte actuel
	@Tag(name = "Profil Abonne")
	@DeleteMapping("delete/currentAcount")
	public ResponseEntity<Object> delCurrentProfil(){

		if(!userService.findCurrentUser().isValide()) {
			return EntityResponse.generateResponse("User SUPP ERREUR ", HttpStatus.BAD_REQUEST, " cet utilisateur n est pas valide ");
		}
		else {
			
			return EntityResponse.generateResponse("User Profile", HttpStatus.OK, 
					userService.deleteUserFromId(userService.findCurrentUser().getId()));
		}

	}
	
	//Delete by id
	@Tag(name = "Profil Abonne")
	@DeleteMapping("delete/currentAcount/{id}")
	public ResponseEntity<Object> delCurrentProfileByid(@PathVariable Long id){

		if(id <= 0) {
			return EntityResponse.generateResponse("User SUPP ERREUR ", HttpStatus.BAD_REQUEST, " l'id utilisateur ne peut être vide ");
		}
		else {

			return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.deleteUserFromId(id));
		}

	}

	//Gestion des profil
	
	//List des profils
	@Tag(name = "Profil Abonne")
	@GetMapping("list-profile")
	public List<Profil> listProfile(){

		return profilRep.findByUtilisateur(userService.findCurrentUser());
	}
	
	//Creation de pro
	@Tag(name = "Profil Abonne")
	@PostMapping("profile/create")
	public ResponseEntity<Object> createProfil(@Valid @RequestBody Profil p){
		
		int lim = profilRep.findByUtilisateur(userService.findCurrentUser()).size();
		
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
			p.setUtilisateur(userService.findCurrentUser());
			return EntityResponse.generateResponse("User Profile", HttpStatus.OK, profilService.create(p));
		}
		
	}
	
	
	//Update pro
	@Tag(name = "Profil Abonne")
	@PutMapping("profile/update")
	public ResponseEntity<Object> updateProfil(@PathVariable Long id,  @Valid @RequestBody Profil p){
		
		if(id <= 0) {
			return EntityResponse.generateResponse("User SUPP ERREUR ", HttpStatus.BAD_REQUEST, " l'id utilisateur ne peut être vide ");
		}
		else {

			return EntityResponse.generateResponse("User Profile", HttpStatus.OK,  profilService.upadte(id, p));
		
		}
		
	}
	
	//Delete pro
	@Tag(name = "Profil Abonne")
	@DeleteMapping("profile/delete/{id}")
	public ResponseEntity<Object> deleteProfil(@PathVariable Long id){

		if(id <= 0) {
			
			return EntityResponse.generateResponse("User SUPP ERREUR ", HttpStatus.BAD_REQUEST, " l'id utilisateur ne peut être vide ");
		}
		else {

			return EntityResponse.generateResponse("User Profile", HttpStatus.OK,  profilService.delete(id));
		
		}
	}

	@Tag(name = "Profil Abonne")
	@GetMapping("downloads/all")
	public List<Pays> allDownload(){

		return null;
	}

	//Deconexion
	@Tag(name = "Profil Abonne")
	@PostMapping("logout")
	public ResponseEntity<Object> userLogout(HttpServletRequest request){

		User usr = userService.findCurrentUser();

		if(usr==null) {

			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, " Aucun utilisateur connecté ou aucune session en cour ");

		}
			 String ipAddress = request.getRemoteAddr();
		     sessionTracker.removeSession(usr.getEmail(), ipAddress);
		     //
			 jwtRep.deleteByUser(usr); 
			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, usr.getUsername()+" à été deconnecter avec succès" );
	  }
	
	@Tag(name = "Profil Abonne")
	@PostMapping("logout/AllDevice")
	public ResponseEntity<Object> userLogoutAllDevice(){

		User usr = userService.findCurrentUser();

		if(usr==null) {

			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, " Aucun utilisateur connecté ou aucune session en cour ");

		}

		     jwtRep.deleteAll();
			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, usr.getUsername()+" à été deconnecter avec succès" );
	  }
	
	
	
	//Payment et Facture


}
