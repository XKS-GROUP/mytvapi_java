package com.mytv.api.model.gestMedia;

import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
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
	
	@ManyToOne(cascade =CascadeType.REMOVE )
	@JoinColumn(name = "idcontType")
	ContributorType idTypecontt;

}
