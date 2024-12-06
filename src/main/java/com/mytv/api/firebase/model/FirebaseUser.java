package com.mytv.api.firebase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FirebaseUser {
	
	@Id
	@Column(name = "uid")
	String uid;
	String username;
	String email;
	String picture;
	boolean emailVerified;

}
