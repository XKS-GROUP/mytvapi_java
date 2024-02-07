package com.mytv.api.model.gestUser;

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
public class Profil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idProfil;
	String profilName;
	String profilImage;
	String lang;

}
