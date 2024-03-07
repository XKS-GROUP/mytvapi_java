package com.mytv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.gestUser.Role;
import com.mytv.api.model.gestUser.SubscriptionType;
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
	
	
	//Gestion du profil admin
	
	@Tag(name = "Profile")
	@GetMapping("profile")
	public ResponseEntity<Object> retrieveUserProfile(){
		return EntityResponse.generateResponse("Admin Profil Info : "+userService.findCurrentUser().getUsername(), HttpStatus.OK, userService.findCurrentUser());
	}
	@Tag(name = "Profile")
	@GetMapping("profile/createAccount")
	public ResponseEntity<Object> createAdmin(@Valid @RequestBody UserRegisterRequestDTO u){
		return EntityResponse.generateResponse("Creation d'un nouvel admin : "+ userService.findCurrentUser().getUsername(), HttpStatus.OK, userService.createUser(u));
	}
	@Tag(name = "Profile")
	@PutMapping("updateProfile")
	public ResponseEntity<Object> updateProfile(@Valid @RequestBody UserRegisterRequestDTO user){
		
		
		userService.updateUser(user);

		return EntityResponse.generateResponse("User "+user.getUsername()+" a été mis a jour ", HttpStatus.OK, userService.findCurrentUser());
	}
	
	
	
	@Tag(name = "Profile")
	@DeleteMapping("deletecurrentAcount")
	public ResponseEntity<Object> delCurrentProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
	@Tag(name = "Profile")
	@GetMapping("logout")
	public ResponseEntity<Object> userLogout(){
		
		
		return EntityResponse.generateResponse("Admin Fetch Role List", HttpStatus.OK,
				"Table Session non creer du au dialect Mysql utilsé avec ce serveur");
	}
	
	@Tag(name = "Profile")
	@DeleteMapping("deleteAcountById/{id}")
	public ResponseEntity<Object> deleteProfileById(Long id){
		
		
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.deleteUserFromId(id));
	}
	
	@Tag(name = "Profile")
	@Operation(summary = "Get All Users", description = "Returne la liste de tous les utilisateurs")
	@GetMapping("user-list")
	public ResponseEntity<Object> getAllUserList(){
		return EntityResponse.generateResponse("Liste de tous les utilisateur confondu", HttpStatus.OK,
				userService.retrieveAllUserList());
	}
	
	@Tag(name = "Profile")
	@GetMapping("userNotValide")
	public ResponseEntity<Object> getAllUserValideList(){
		return EntityResponse.generateResponse("Liste de tous les utilisateur avec un compte non valide", HttpStatus.OK,
				userService.AllUserNotValide());
	}
	
	@Tag(name = "Profile")
	@GetMapping("userValide")
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
	
	@Tag(name = "Role")
	@Operation(summary = "Creer un role", description = "Returne la liste de tous les Roles")
	@GetMapping("role/create")
	public ResponseEntity<Object> createRole(Role role){
		
		return EntityResponse.generateResponse("Creation d'un nouveau Role", HttpStatus.OK,
				roleService.save(role));
	}
	
	
	@Tag(name = "Role")
	@Operation(summary = "supp role", description = "Returne la liste de tous les Roles")
	@GetMapping("role/delete/{id}")
	public ResponseEntity<Object> deleteRole(Long id){
		return EntityResponse.generateResponse("role supp...........", HttpStatus.OK,
				roleService.delete(id));
	}
	

	
	//Abonnement et type d'abonnement
	
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
	
	@Tag(name = "subscriptionType")
	@PostMapping("subscriptionType/create")
	public ResponseEntity<Object> createSubscriptionType(SubscriptionType sub){
		return EntityResponse.generateResponse("creation d'un nouveau type d abonnement", HttpStatus.OK,
				
				subTypService.create(sub));
	}
	
	@Tag(name = "subscriptionType")
	@PutMapping("subscriptionType/update/{id}")
	public ResponseEntity<Object> updateSubscriptionTypeById(Long id, SubscriptionType sub){
		return EntityResponse.generateResponse("MAJ Sub Type by id ...", HttpStatus.OK,
				subTypService.upadte(id , sub));
	}
	
	@Tag(name = "subscriptionType")
	@DeleteMapping("subcriptionType/delete/{id}")
	public ResponseEntity<Object> deleteSubscriptionType(Long id){
		return EntityResponse.generateResponse("Suppression d un abonnement", HttpStatus.OK,
				subTypService.delete(id));
	}
	
	
	//Configuration
	@Tag(name = "Configuration")
	@GetMapping("config")
	public ResponseEntity<Object> ConfigApp(){
		return EntityResponse.generateResponse("Configuration de l'app : "+userService.findCurrentUser().getUsername(), HttpStatus.OK, userService.findCurrentUser());
	}
	
	
	
}
