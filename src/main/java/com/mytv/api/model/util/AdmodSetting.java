package com.mytv.api.model.util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class AdmodSetting {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	String ANDROID_AD_Banner;
	
	String ANDROID_Ad_unit_banner_ID;
	
	String ANDROID_Ad_unit_interstitial_ID;
	
	String IOS_AD_Banner;
	
	String IOS_Ad_unit_banner_ID;
	
	String IOS_Ad_unit_interstitial_ID;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean Ad_interstitial;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean Ad_banner;

}
