package com.mytv.api.model.gestMedia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MediaSource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idMediaSource;
	
	String streaminLink;
	
	String playLink;
	
	String quality;
	
	String imdb;
	
	String language;

}
