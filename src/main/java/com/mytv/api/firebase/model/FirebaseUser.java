package com.mytv.api.firebase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseUser {
	
	String uid;
	String username;
	String email;
	String picture;
	boolean isEmailVerified;

}
