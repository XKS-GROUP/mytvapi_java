package com.mytv.api.firebase.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.auth.UserRecord;

import com.mytv.api.user.repository.UserRepository;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseService {

	
	@Autowired
	UserRepository userRepository;
	
	
	public void getUser() {

		System.out.println("Info firebase .................. : " + UserRecord.class);
	}
	
}
