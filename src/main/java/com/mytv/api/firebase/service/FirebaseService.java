package com.mytv.api.firebase.service;

import org.springframework.stereotype.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import com.mytv.api.user.repository.UserRepository;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseService {

	
	@Autowired
	UserRepository userRepository;
	
	
	public void getUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uid = authentication.getName();
		
		try {
			UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
			
			
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Info firebase .................. : " + UserRecord.class);
	}
	
}
