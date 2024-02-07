package com.mytv.api.model.gestMedia;

import java.sql.Date;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contributor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idContributor;
	
	String fistName;
	
	String lastName;
	
	Date dob;
	
	String imageUrl;
	
	String biography;
	
	String nationality;
	
	Long idTypeIntervenant;

}
