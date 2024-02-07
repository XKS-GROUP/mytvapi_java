package com.mytv.api.model.gestMedia;

import java.util.Date;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Season {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSaison;
	
	String name;
	
	String overview;
	
	String posterUrl;
	
	int number;
	
	Date releaseDate;

}
