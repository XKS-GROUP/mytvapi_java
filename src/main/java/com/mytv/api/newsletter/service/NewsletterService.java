package com.mytv.api.newsletter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.newsletter.model.Subscriber;
import com.mytv.api.newsletter.repository.SubscriberRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class NewsletterService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    
    @Autowired
    private EmailService emailService;

    
    public Subscriber subscribe(Subscriber subs) {
    	
        return subscriberRepository.save(subs);
        
    }

    public boolean unsubscribe(String email) {
    	
    	if(subscriberRepository.findByEmail(email).isPresent()) {
    		
    		Subscriber sb = subscriberRepository.findByEmail(email).get();
    		
    		subscriberRepository.deleteById(sb.getId());
    		return true;
    		
    	}else {
    		
    		return false;
    		
    	}
    }

    public List<Subscriber> getAllSubscribers() {
    	
        return subscriberRepository.findAll();
    }
    
    public List<Subscriber> search(String s, Pageable p) {
    	
        return subscriberRepository.findByEmailContaining(s, p);
    }
    
    public Page<Subscriber> getAllSubscribers(Pageable p) {
    	
        return subscriberRepository.findAll(p);
    }
    
    public boolean dejainscrit(String email) {
    	
    	if(subscriberRepository.findByEmail(email).isPresent()) {
    		return true;
    	}
    	else {
    		
    		return false;
    	}
    }
    

    public void sendNewsletterToAllSubscribers(String subject, String content) {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        
        for (Subscriber subscriber : subscribers) {
            emailService.sendNewsletter(subscriber.getEmail(), subject, content);
        }
    }
    
    public void deleteAbonne(Long id) {
    	
    	subscriberRepository.deleteById(id);
    	
    }
}
