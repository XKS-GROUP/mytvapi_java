package com.mytv.api.model.gestMedia;

//import java.sql.Date;
//import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ColPodcast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;
	
	String name;
	
	String overView;
	
	//@ManyToOne
	//@JoinColumn(name = "idSerie")
	
	boolean status;
	
	String posterUrl;
	
	
	

}
