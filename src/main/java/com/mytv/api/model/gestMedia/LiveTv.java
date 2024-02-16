package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
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
public class LiveTv {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLiveTv")
	Long idLiveTv;
	
	@Column(nullable = false)
	String name ;
	
	String description;

	String tvLogo;
	
	String category;
	
	boolean status;

	
	
	String streamType;
	
	String svr1;
	String svr2;
	String svr3;
	
	String tvEmbedCode;
	
	String country;
	
	


}