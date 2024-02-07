package com.mytv.api.model.gestPub;

import jakarta.persistence.Id;

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
public class Publicite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPublicite;
	String name;
	String bannerUrl;
	String movieUrl;
	String pageTarget;

}
