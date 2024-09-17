package com.mytv.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mytv.api.setting.model.R2cloudSetting;
import com.mytv.api.setting.repository.R2SettingRepository;
import com.mytv.api.setting.service.SettingService;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

   	@Autowired
	R2SettingRepository R2Rep;
   	
   	@Autowired
   	SettingService setting;

    public DataInitializer(R2SettingRepository repository) {
        this.R2Rep = repository;
    }

    @Value("${aws.access.key.id}")
    String CLOUDFLARE_ACCES_APIKEY;
    
    @Value("${aws.secret.access.key}")
    String CLOUDFLARE_R2_SECRET_ACCES_KEY;
    
    @Value("${aws.s3.region}")
    String CLOUDFLARE_R2_REGION;
    
    @Value("${aws.s3.bucket.name}")
    String CLOUDFLARE_R2_BUCKET;
    
    
    @PostConstruct
    public void init() {
    	
        // Précharger des données
    	
    	R2cloudSetting r2 = new R2cloudSetting ();
    	
    	r2.setCLOUDFLARE_ACCES_APIKEY(CLOUDFLARE_ACCES_APIKEY);
    	r2.setCLOUDFLARE_R2_SECRET_ACCES_KEY(CLOUDFLARE_R2_SECRET_ACCES_KEY);
    	r2.setCLOUDFLARE_R2_REGION(CLOUDFLARE_R2_REGION);
    	r2.setCLOUDFLARE_R2_BUCKET(CLOUDFLARE_R2_BUCKET);
    	
    	setting.add_R2Setting(r2);
    	
    }
}