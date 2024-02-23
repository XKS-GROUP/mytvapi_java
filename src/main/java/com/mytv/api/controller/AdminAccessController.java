package com.mytv.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.gestUser.WRoleService;
import com.mytv.api.service.gestUser.WUserService;


@RestController
@RequestMapping("api/v1/admin/")
public class AdminAccessController {

	@Autowired
	WUserService userService;
	
	@Autowired
	WRoleService roleService;
	
	@GetMapping("profile")
	public ResponseEntity<Object> retrieveUserProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
	@PutMapping("updateProfile")
	public ResponseEntity<Object> updateProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
	@DeleteMapping("deletecurrentAcount")
	public ResponseEntity<Object> delCurrentProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
	@DeleteMapping("deleteAcountById")
	public ResponseEntity<Object> deleteProfileById(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
	
	@GetMapping("user-list")
	public ResponseEntity<Object> getAllUserList(){
		return EntityResponse.generateResponse("Admin Fetch User List", HttpStatus.OK,
				userService.retrieveAllUserList());
	}
	
	@GetMapping("role-list")
	public ResponseEntity<Object> getAllRoleList(){
		return EntityResponse.generateResponse("Admin Fetch Role List", HttpStatus.OK,
				roleService.findAllRole());
	}
	
	@GetMapping("subcriptionType")
	public ResponseEntity<Object> getAllSubscriptionType(){
		return EntityResponse.generateResponse("Admin Fetch User List", HttpStatus.OK,
				userService.retrieveAllUserList());
	}
	
	@PutMapping("updateSubcriptionType")
	public ResponseEntity<Object> updateSubscriptionType(){
		return EntityResponse.generateResponse("Admin Fetch User List", HttpStatus.OK,
				userService.retrieveAllUserList());
	}
	
	@DeleteMapping("deleteSubcriptionType")
	public ResponseEntity<Object> deleteSubscriptionType(){
		return EntityResponse.generateResponse("Admin Fetch User List", HttpStatus.OK,
				userService.retrieveAllUserList());
	}
	
	
	
	
}
