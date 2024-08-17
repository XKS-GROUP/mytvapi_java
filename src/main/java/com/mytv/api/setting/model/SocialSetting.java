package com.mytv.api.setting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialSetting {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(columnDefinition = "TEXT")
	String youtube_url;
	
	@Column(columnDefinition = "TEXT")
	String instagram_uri;
	
	@Column(columnDefinition = "TEXT")
	String facebook_uri;
	
	@Column(columnDefinition = "TEXT")
	String twitter_uri;

}
