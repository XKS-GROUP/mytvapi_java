package com.mytv.api.model.gestMedia;

import java.sql.Date;
//import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Episode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;
	
	String name;
	
	String overView;
	
	@ManyToOne
	@JoinColumn(name = "idSerie")
	Serie serie;
	
	Date realeaseDate;
	
	int numero;
	
	int duration;
	
	boolean status;
	
	String posterUrl;
	
	String typeFile;
	
	String videoFile;
	
	String urlvideo;
	String urlvideo2;
	String urlvideo3;
	
	

}
