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
	String THEMOVIEDB_APIKEY;
	
	@Column(columnDefinition = "TEXT")
	String CLOUDFLARE_ACCES_APIKEY;
	
	@Column(columnDefinition = "TEXT")
	String CLOUDFLARE_R2_SECRET_ACCES_KEY;
	
	@Column(columnDefinition = "TEXT")
	String CLOUDFLARE_R2_REGION;
	
	@Column(columnDefinition = "TEXT")
	String CLOUDFLARE_R2_BUCKET;
	
	@Column(columnDefinition = "TEXT")
	String instagram_uri;
	
	@Column(columnDefinition = "TEXT")
	String facebook_uri;
	
	@Column(columnDefinition = "TEXT")
	String twitter_uri;
	
	@Column(columnDefinition = "TEXT")
	String firebase_fnc_server;
	
	
}
