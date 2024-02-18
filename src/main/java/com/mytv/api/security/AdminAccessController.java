package com.mytv.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.service.gestUser.WRoleService;
import com.mytv.api.service.gestUser.WUserService;


@RestController
@RequestMapping("api/v1/admin")
public class AdminAccessController {

	@Autowired
	WUserService userService;
	
	@Autowired
	WRoleService roleService;
	
	
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
}
