package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lang")
public class Language {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLang;
	
	@Column(length = 12, nullable = false)
	String name;
	
	String flag;
	

}
