package com.mytv.api.firebase.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.dto.EmailDTO;
import com.mytv.api.dto.OtpConfirmDTO;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.firebase.model.Otp;
import com.mytv.api.firebase.service.FirebaseUserService;
import com.mytv.api.firebase.service.OtpService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.util.service.NotificationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

@RequestMapping("api/v1/auth/")
public class FirebaseController {

	@Autowired
	OtpService fire_service;
	
	@Autowired
	NotificationService notification;
	
	@Autowired
	FirebaseUserService fbuser_service;
	
	
	@PostMapping("firebase/user/create")
    public ResponseEntity<Object> user_create(@Valid @RequestBody FirebaseUser user) {
		
		if(fbuser_service.findbyUid(user.getUid()) == null ) {
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK,
					fbuser_service.create(user));
			
		}else {
		  
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK,
					Map.of("message", " utilisateur a été mis a jour "));
		
		}
		
	}
	
	
	@PostMapping("otp/send/")
    public ResponseEntity<Object> otp_send(@Valid @RequestBody EmailDTO email) {
		
		Otp otp = fire_service.createOTP(email);
		
		notification.envoyerOTP(otp);
		return EntityResponse.generateResponse("SUCCES", HttpStatus.OK,
				Map.of("message", "Un code à été envoyer à l'adresse "+email.getValue()));
		
		
	}
	
	@PostMapping("otp/confirm/")
    public ResponseEntity<Object> otp_comfirm(@Valid @RequestBody OtpConfirmDTO otp) {
		
		
		if(fire_service.findByODT(otp.getOtp()) ==null) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					
					Map.of("message", "Aucun code ne correspond au code otp saisi"));
			
		}
		
		else if(fire_service.valideOtp(otp.getOtp()) || fire_service.findByEmail(otp.getEmail()) == null ) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					
					Map.of("message", "Votre addresse email "+otp.getEmail()+" ne correspond pas à ce code otp  "));
			
		}
		
		else if(!fire_service.Compare_Email_ODT(otp)) {
			
			return EntityResponse.generateResponse("ATTENTION", HttpStatus.BAD_REQUEST,
					
					Map.of("message", "Votre addresse email "+otp.getEmail()+" ne correspond pas à ce code otp  "));
			
		}
		else {
			
			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK,
					Map.of("message", "Ce code est valide "));
		}
		
		
		
		
	}
}
