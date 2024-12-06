package com.mytv.api.firebase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.firebase.repository.FirebaseUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FirebaseUserService {

	@Autowired
	FirebaseUserRepository rep;
	
	public FirebaseUser create(FirebaseUser user) {
		
		return rep.save(user);
	}
	
	public FirebaseUser update(FirebaseUser user, String uid) {
		
		user.setUid(uid);
		
		return rep.save(user);
	}
	
	public void delete(String uid) {
		
		rep.deleteById(uid);
	}
	
	public FirebaseUser findbyUid(String uid) {
		
		return rep.findByUid(uid);
	}
	
	public FirebaseUser findbyEmail(String email) {
		
		return rep.findByEmail(email);
	}
	
	public FirebaseUser findbyUsername(String usr) {
		
		return rep.findByUsername(usr);
	}
}
