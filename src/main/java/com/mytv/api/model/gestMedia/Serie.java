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
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerie;
	
	String name;
	
	String overview;
	
	String posterUrl;
	
	String trailerURL;
	
	boolean status;
	
	Date releaseDate;

}
