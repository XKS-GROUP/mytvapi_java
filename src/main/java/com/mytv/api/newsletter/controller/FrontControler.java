package com.mytv.api.newsletter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        
    	
    	
    	if (newsletterService.dejainscrit(u.getEmail())) {
    		
    		return EntityResponse.generateResponse("ATTENTION ", HttpStatus.OK, Map.of("message", "cet utilisateur est déja inscrit"));
    	}
    	else {
    		
	    	Subscriber sb = new Subscriber();
	    	
	    	sb.setUid(u.getUid());
	    	
	    	sb.setEmail(u.getEmail());
	    	
	    	newsletterService.subscribe(sb);
	    	
            return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("message", "vous etes abonné a la newsletter"));
        
    	}
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
    public ResponseEntity<Object> unsubscribe(@PathVariable String email) {
    	
    	if (newsletterService.dejainscrit(email)) {
    		
    		
    		return EntityResponse.generateResponse("ATTENTION ", HttpStatus.OK, 
    				Map.of("message", "vous n ete pas encore inscrit"));
    		
    		
    	}
    	else {
    		
    		
    		
    		
	        newsletterService.unsubscribe(email);
	        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, 
	        		Map.of("message", "desaboné avec succès"));
	        
    	}
    }

    @Tag(name = "Newsletter")
    @GetMapping("newsletter/test/abonne/{email}")
    public ResponseEntity<Object> testAbonne(@PathVariable String email) {
        
    	if(!newsletterService.dejainscrit(email)) {
    		
    		return EntityResponse.generateResponse("ERREUR ", HttpStatus.BAD_REQUEST, Map.of("message", "utilisateur non inscrit"));
    	}
    	else {
    		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("message", "utilisateur inscrit"));
    	}
    	
    }
}
