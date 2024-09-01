package com.mytv.api.contact.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.mytv.api.contact.model.Contact;
import com.mytv.api.contact.model.Proposition;
import com.mytv.api.contact.model.Suggestion;
import com.mytv.api.contact.service.ContactService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.util.service.NotificationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/")
@SecurityRequirement(name = "bearerAuth")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private NotificationService notification;
    
    public Authentication getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
        	
            return authentication;
            
        } else {
        	
            return authentication;
        }
    }
    
	@Tag(name = "Contact")
    @PostMapping("contact/send")
	ResponseEntity<Object> contact_send(@Valid @RequestBody Contact contact) {
		
			contactService.saveContact(contact);
	        
	        if(contact.getEmail().isEmpty()) {
	        	 
	        	 return EntityResponse.generateResponse("ERREUR ", HttpStatus.CONFLICT, 
	        			 Map.of("message", "ce compte ne dispose pas d'adresse email") );
	        }
	        else {
	        
		        notification.envoyerContact( contact.getEmail() , contact);
		        
		        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("message", "envoyé avec succes"));
	        }
			
    }
	
	@Tag(name = "Contact")
    @PostMapping("proposition/send")
	ResponseEntity<Object> proposition_send(@Valid @RequestBody Proposition proposition) {
		
        contactService.saveProposition(proposition);
        
        if(proposition.getEmail().isEmpty()) {
       	 
       	  return EntityResponse.generateResponse("ERREUR ", HttpStatus.CONFLICT, 
       			 Map.of("message", "ce compte ne dispose pas d'adresse email") );
        }
        else {
    	   
          notification.envoyerProposition(proposition.getEmail(), proposition);
        
          return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        		 Map.of("message", "envoyé avec succès") );
       }
    }
	
	@Tag(name = "Contact")
    @PostMapping("suggestion/send")
	ResponseEntity<Object> suggestion_send(@Valid @RequestBody Suggestion suggestion) {
		
        contactService.saveSugg(suggestion);
        
        if(suggestion.getEmail().isEmpty()) {
       	 
        	return EntityResponse.generateResponse("ERREUR ", HttpStatus.CONFLICT, 
        			Map.of("message", " ce compte ne dispose pas d'adresse email "));
        }
        else{
        	
        	notification.envoyerSuggestion(suggestion.getEmail(), suggestion);
        
        	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
        			Map.of("message", "envoyé avec succes"));
        }
    }
	
}
