package com.mytv.api.controller;

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

import com.mytv.api.model.util.SmtpSetting;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.service.util.SmtpService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class SettingControlleur {

	@Autowired
	SmtpService smtpService;
	
	@Tag(name = "Setting smtp email")
	@GetMapping("smtp")
	public ResponseEntity<Object> showPub(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, smtpService.show());
	}

    
	@Tag(name = "Setting smtp email")
	@GetMapping("smtp/{id}")
	public ResponseEntity<Object> showPubById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, smtpService.showById(id));
	}
    
	
	@Tag(name = "Setting smtp email")
	@PostMapping("smtp/create")
	public ResponseEntity<Object> createPub(
			@Valid @RequestBody SmtpSetting p){

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, smtpService.create(p));
			
	}
    
	
	@Tag(name = "Setting smtp email")
	@PutMapping("smtp/update/{id}")
	public ResponseEntity<Object> updatePub(@PathVariable Long id, @Valid @RequestBody SmtpSetting p){

		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, smtpService.upadte(id, p));
		
	}
    
	@Tag(name = "Setting smtp email")
	@DeleteMapping("smtp/delete/{id}")
	public ResponseEntity<Object> deletePub(@PathVariable Long id){
    	smtpService.delete(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");
		
	}
}
