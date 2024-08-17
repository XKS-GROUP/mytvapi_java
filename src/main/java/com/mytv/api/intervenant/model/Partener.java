package com.mytv.api.intervenant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Partener {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPartner;

	@NotBlank(message = "ce champ ne peut etre vide, un partenair doit avoir au moin un nom ")
	@Column(nullable = false)
	String name;

	String Email;

	String tel;

	public Long getIdPartner() {
		return idPartner;
	}

	public void setIdPartner(Long idPartner) {
		this.idPartner = idPartner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


}
