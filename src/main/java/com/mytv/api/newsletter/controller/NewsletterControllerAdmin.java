package com.mytv.api.newsletter.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.newsletter.NewsletterDTO;
import com.mytv.api.newsletter.service.NewsletterService;
import com.mytv.api.security.request.EntityResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
public class NewsletterControllerAdmin {
	
	@Autowired
	private NewsletterService newsletterService;
	
	@Tag(name = "Newsletter")
    @GetMapping("newletters/abonne")
    public ResponseEntity<Object> getAllSubscribers() {
    	
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, newsletterService.getAllSubscribers());
    }
    
	@Tag(name = "Newsletter")
    @GetMapping("newletters/all/abonne")
    public ResponseEntity<Object> getAllSubscribers(Pageable p) {
    	
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, newsletterService.getAllSubscribers(p));
    }
    
	@Tag(name = "Newsletter")
    @GetMapping("newletters/abonne/search/")
    public ResponseEntity<Object> getAllSubscribers(
    		@RequestParam (required = false) String  s, 
    		Pageable p) {
    	
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, newsletterService.search(s, p));
    }

	@Tag(name = "Newsletter")
    @PostMapping("newletters/send")
    public ResponseEntity<Object> sendNewsletter(@RequestBody NewsletterDTO news) {
    	
        newsletterService.sendNewsletterToAllSubscribers(news.getObjet(), news.getContent());
        
        return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, Map.of("message", "Envoyer avec succes") ); 
        
    }
    
    
}
