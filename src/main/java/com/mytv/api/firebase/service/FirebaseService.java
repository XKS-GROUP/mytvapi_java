package com.mytv.api.firebase.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.mytv.api.user.repository.UserRepository;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseService {

	
	@Autowired
	UserRepository userRepository;
	
	public Authentication getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        
        
        if (authentication != null && authentication.isAuthenticated()) {
        	
            return authentication;
            
        } else {
        	
            return authentication;
        }
    }
	
}
