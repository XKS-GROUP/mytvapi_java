package com.mytv.api.model.gestMedia;

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

public class ContributorType {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idActeur;
	
	String name;


}
