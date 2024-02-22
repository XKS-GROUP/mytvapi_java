package com.mytv.api.model.gestUser;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idProfil;
	
	@NotBlank(message = "ce champ ne peut etre vide, un profil doit avoir au moin un nom ")	
	@Column(nullable = false)
	
	String profilName;
	
	String profilImage;
	
	String lang;

	public Long getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Long idProfil) {
		this.idProfil = idProfil;
	}

	public String getProfilName() {
		return profilName;
	}

	public void setProfilName(String profilName) {
		this.profilName = profilName;
	}

	public String getProfilImage() {
		return profilImage;
	}

	public void setProfilImage(String profilImage) {
		this.profilImage = profilImage;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	

}
