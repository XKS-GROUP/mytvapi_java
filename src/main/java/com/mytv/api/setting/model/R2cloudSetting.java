package com.mytv.api.setting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R2cloudSetting {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "une clé API est requise")
	String CLOUDFLARE_ACCES_APIKEY;
	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "une secret acces key clé FCM est requise")
	String CLOUDFLARE_R2_SECRET_ACCES_KEY;
	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "une région est requise")
	String CLOUDFLARE_R2_REGION;
	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "un bucket est requis")
	String CLOUDFLARE_R2_BUCKET;
}
