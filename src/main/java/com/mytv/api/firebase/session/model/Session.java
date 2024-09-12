package com.mytv.api.firebase.session.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	   
	    String userId;
	    
	    @Lob
	    @Column(columnDefinition = "TEXT")
	    String sessionToken;
	    
	    String ipAddress;
	    
	    Date loginTime;
	    
	    Boolean isActive;
}
