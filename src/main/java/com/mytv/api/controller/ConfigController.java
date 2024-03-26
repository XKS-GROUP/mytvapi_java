package com.mytv.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.gestUser.Role;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.gestUser.WRoleService;
import com.mytv.api.service.gestUser.WUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/util")
public class ConfigController {

	@Autowired
	WUserService userService;

	@Autowired
	WRoleService roleService;


	@Tag(name = "util")
	@Operation(summary = "Get All Rols", description = "Returne la liste de tous les Roles")
	@GetMapping("role/role-list")
	public ResponseEntity<Object> getAllRoleList(){
		return EntityResponse.generateResponse("Liste des roles disponible", HttpStatus.OK,
				roleService.findAllRole());
	}

	@Tag(name = "util")
	@Operation(summary = "Creer un role", description = "Returne la liste de tous les Roles")
	@PostMapping("role/create")
	public ResponseEntity<Object> createRole(@Valid @RequestBody Role role){

		return EntityResponse.generateResponse("Creation d'un nouveau Role", HttpStatus.OK,
				roleService.save(role));
	}

	@Tag(name = "util")
	@Operation(summary = "supp role", description = "Returne la liste de tous les Roles")
	@DeleteMapping("role/delete/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable Long id){
		return EntityResponse.generateResponse("role supp...........", HttpStatus.OK,
				roleService.delete(id));
	}

}
