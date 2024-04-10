package com.mytv.api.controller;

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

import com.mytv.api.model.gestUser.Role;
import com.mytv.api.model.gestUser.SubscriptionType;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.JwtRepository;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.security.UserRegisterRequestDTO;
import com.mytv.api.service.gestUser.SubscriptionServiceImplement;
import com.mytv.api.service.gestUser.SubscriptionTypeServiceImplement;
import com.mytv.api.service.gestUser.WRoleService;
import com.mytv.api.service.gestUser.WUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/v1/admin/")

@SecurityRequirement(name = "bearerAuth")
public class AdminAccessController {

	@Autowired
	WUserService userService;

	@Autowired
	WRoleService roleService;

	@Autowired
	SubscriptionTypeServiceImplement subTypService;

	@Autowired
	SubscriptionServiceImplement subSerice;

	@Autowired
	private JwtRepository jwtRep;


	//Gestion du profil admin

	@Tag(name = "ADMIN Profil")
	@GetMapping("profile/info")
	public ResponseEntity<Object> retrieveUserProfile(){
		return EntityResponse.generateResponse("Admin Profil Info : "+userService.findCurrentUser().getUsername(), HttpStatus.OK, userService.findCurrentUser());
	}

	//Affiche by id
	@Tag(name = "ADMIN Profil")
	@GetMapping("profile/byId/{id}")
	public ResponseEntity<Object> retrieveUserProfilebyId(@PathVariable Long id){
		if(id <= 0) {
			return EntityResponse.generateResponse("User ERREUR ", HttpStatus.BAD_REQUEST, " l'id utilisateur ne peut être vide ");
		}
		else {

			return EntityResponse.generateResponse("Admin Profil Info : "+userService.findUserById(id).get().getUsername(), HttpStatus.OK, userService.findUserById(id));
		}

	}
	
	//Update Current profil
	@Tag(name = "ADMIN Profil")
	@PutMapping("profil/update")
	public ResponseEntity<Object> updateProfile(@Valid @RequestBody UserRegisterRequestDTO user){

		return EntityResponse.generateResponse("User "+user.getUsername()+" a été mis a jour ", HttpStatus.OK, 
				userService.updateUser(user) );
	}
	
	//Sup compte actuel
	@Tag(name = "ADMIN Profil")
	@DeleteMapping("profil/delete/currentAcount")
	public ResponseEntity<Object> delCurrentProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, 
				userService.deleteUserFromId(userService.findCurrentUser().getId()));
	}

	//Deconexion
	@Tag(name = "ADMIN Profil")
	@PostMapping("profil/logout")
	public ResponseEntity<Object> userLogout(){

		User usr = userService.findCurrentUser();

		if(usr==null) {

			 return EntityResponse.generateResponse("Deconexion", HttpStatus.BAD_REQUEST, " Aucun utilisateur connecté ou aucune session en cour ");

		}

			 jwtRep.deleteByUser(usr); //jwtRep.deleteAll();//
			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, usr.getUsername()+" à été deconnecter avec succès" );
	  }

	
	@Tag(name = "User")
	@PostMapping("user/createAccount/admin")
	public ResponseEntity<Object> createAdmin(@Valid @RequestBody UserRegisterRequestDTO u){
		return EntityResponse.generateResponse("Creation d'un nouvel admin : "+ userService.findCurrentUser().getUsername(), HttpStatus.OK, 
				userService.createUser(u));
		
	}
	
	
	//USER ABONNE 

	//Supp by id
	@Tag(name = "ADMIN Profil")
	@DeleteMapping("users/delete/ById/{id}")
	public ResponseEntity<Object> delCurrentProfileByid(@PathVariable Long id){

		if(id <= 0) {
			return EntityResponse.generateResponse("User SUPP ERREUR ", HttpStatus.BAD_REQUEST, " l'id utilisateur ne peut être vide ");
		}
		else {

			return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.deleteUserFromId(id));
		}

	}


	//Supp by id
	@Tag(name = "User")
	@DeleteMapping("user/deleteAcountById/{id}")
	public ResponseEntity<Object> deleteProfileById(@PathVariable Long id){


		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.deleteUserFromId(id));
	}

	//List User
	@Tag(name = "User")
	@Operation(summary = "Get All Users", description = "Returne la liste de tous les utilisateurs")
	@GetMapping("users/all")
	public ResponseEntity<Object> getAllUserList(){
		return EntityResponse.generateResponse("Liste de tous les utilisateur confondu", HttpStatus.OK,
				userService.retrieveAllUserList());
	}

	//List des utilisateur non valide
	@Tag(name = "User")
	@GetMapping("users/notValide")
	public ResponseEntity<Object> getAllUserValideList(){
		return EntityResponse.generateResponse("Liste de tous les utilisateur avec un compte non valide", HttpStatus.OK,
				userService.AllUserNotValide());
	}

	//List des utilisateur valide
	@Tag(name = "User")
	@GetMapping("users/valide")
	public ResponseEntity<Object> getAllUserNotValideList(){
		return EntityResponse.generateResponse("Liste de tous les utilisateur avec un compte valide", HttpStatus.OK,
				userService.AllUserValide());
	}

	//Role
	@Tag(name = "Role")
	@Operation(summary = "Get All Rols", description = "Returne la liste de tous les Roles")
	@GetMapping("role/role-list")
	public ResponseEntity<Object> getAllRoleList(){
		return EntityResponse.generateResponse("Liste des roles disponible", HttpStatus.OK,
				roleService.findAllRole());
	}

	//Creer un nouveau role
	@Tag(name = "Role")
	@Operation(summary = "Creer un role", description = "Returne la liste de tous les Roles")
	@PostMapping("user/role/create")
	public ResponseEntity<Object> createRole(@Valid @RequestBody Role role){

		return EntityResponse.generateResponse("Creation d'un nouveau Role", HttpStatus.OK,
				roleService.save(role));
	}

	//Supp un role
	@Tag(name = "Role")
	@Operation(summary = "supp role", description = "Returne la liste de tous les Roles")
	@DeleteMapping("user/role/delete/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable Long id){
		return EntityResponse.generateResponse("role supp...........", HttpStatus.OK,
				roleService.delete(id));
	}


	//Liste Abonnement et type d'abonnement
	@Tag(name = "subscriptionType")
	@GetMapping("subscriptionType")
	@Operation(summary = "Get All Subscription", description = "Returne la liste de tous les abonnements")
	List<SubscriptionType> subscriptionTypeAll(){

		return subTypService.show();

	}

	@Tag(name = "subscriptionType")
	@GetMapping("subscriptionTypeAllGroupByName")
	List<SubscriptionType> subscriptionAllGroupByName(){

		return subTypService.show();

	}

	//Creer un Abonnement
	@Tag(name = "subscriptionType")
	@PostMapping("subscriptionType/create")
	public ResponseEntity<Object> createSubscriptionType(@Valid @RequestBody SubscriptionType sub){

		return EntityResponse.generateResponse("creation d'un nouveau type d abonnement", HttpStatus.OK, subTypService.create(sub));

	}


	//Maj Abbonement
	@Tag(name = "subscriptionType")
	@PutMapping("subscriptionType/update/{id}")
	public ResponseEntity<Object> updateSubscriptionTypeById(@PathVariable Long id, @Valid @RequestBody SubscriptionType sub){
		return EntityResponse.generateResponse("MAJ Sub Type by id ...", HttpStatus.OK,
				subTypService.upadte(id , sub));
	}

	//Supp Abonnement
	@Tag(name = "subscriptionType")
	@DeleteMapping("subcriptionType/delete/{id}")
	public ResponseEntity<Object> deleteSubscriptionType(@PathVariable Long id){
		return EntityResponse.generateResponse("Suppression d un abonnement", HttpStatus.OK,
				subTypService.delete(id));
	}

	
	@Tag(name = "User")
	@PutMapping("user/update/{idUser}")
	public ResponseEntity<Object> userUpdate(@PathVariable Long idUser,@Valid @RequestBody User u){
		return EntityResponse.generateResponse("Suppression d un abonnement", HttpStatus.OK,
				userService.updateByid(idUser, u));
	}

	//Configuration
	@Tag(name = "Configuration")
	@GetMapping("config")
	public ResponseEntity<Object> ConfigApp(){
		return EntityResponse.generateResponse("Configuration de l'app : "+userService.findCurrentUser().getUsername(), HttpStatus.OK, userService.findCurrentUser());
	}

}
