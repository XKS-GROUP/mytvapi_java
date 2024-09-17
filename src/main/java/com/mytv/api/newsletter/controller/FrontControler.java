package com.mytv.api.newsletter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.dto.EmailDTO;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.newsletter.model.Subscriber;
import com.mytv.api.newsletter.service.NewsletterService;
import com.mytv.api.security.request.EntityResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/")
@SecurityRequirement(name = "bearerAuth")
public class FrontControler {

    @Autowired
    private NewsletterService newsletterService;

    
    public Authentication getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
        	
            return authentication;
            
        } else {
        	
            return authentication;
        }
    }
    
    @Tag(name = "Newsletter")
    @PostMapping("newsletter/subscipt")
    public ResponseEntity<Object> subscribe() {
    	
    	FirebaseUser u = (FirebaseUser) getCurrentUser().getPrincipal();
        
    	Subscriber sb = new Subscriber();
    	
    	sb.setUid(u.getUid());
    	sb.setEmail(sb.getEmail());
    	
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, newsletterService.subscribe(sb));
    }
    
    @Tag(name = "Newsletter")
    @PostMapping("newsletter/subscript/byEmail")
    public ResponseEntity<Object> subscribe(@RequestBody Subscriber sb) {
    	
    	if (newsletterService.dejainscrit(sb.getEmail())) {
    		
    		return EntityResponse.generateResponse("ATTENTION ", HttpStatus.OK, Map.of("message", "cet utilisateur est déja inscrit"));
    	}
    	else {
    		
    	
            return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, newsletterService.subscribe(sb));
        
    	}
    }

    @Tag(name = "Newsletter")
    @PostMapping("newsletter/unsubscript")
    public ResponseEntity<Object> unsubscribe(@RequestBody EmailDTO email) {
    	
        newsletterService.unsubscribe(email.getValue());
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("message", "Successfully unsubscribed"));
    }

    @Tag(name = "Newsletter")
    @GetMapping("newsletter/test/abonne")
    public ResponseEntity<Object> testAbonne(@RequestBody EmailDTO email) {
        
    	if(!newsletterService.dejainscrit(email.getValue())) {
    		
    		return EntityResponse.generateResponse("ERREUR ", HttpStatus.BAD_REQUEST, Map.of("message", "utilisateur non inscrit"));
    	}
    	else {
    		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("message", "utilisateur inscrit"));
    	}
    	
    }
}
