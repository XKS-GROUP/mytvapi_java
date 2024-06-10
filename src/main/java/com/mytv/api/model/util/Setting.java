package com.mytv.api.model.util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Setting {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
	String app_name;
    
	@Column(columnDefinition = "TEXT")
	String app_logo;
	
	@Column(columnDefinition = "TEXT")
	String app_favicon;
	
	@Column(columnDefinition = "TEXT")
	String Copyright;
	
	@Column(columnDefinition = "TEXT")
	String default_devise;
	
	@Column(columnDefinition = "TEXT")
	String playStore_url;
	
	@Column(columnDefinition = "TEXT")
	String appleStore_uri;
	
	@Column(columnDefinition = "TEXT")
	Long app_default_language;
	
	@Column(columnDefinition = "TEXT")
	String terms_of_service;
	
	@Column(columnDefinition = "TEXT")
	String politique_conf;
	
}
