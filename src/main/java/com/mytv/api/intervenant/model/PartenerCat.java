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
@AllArgsConstructor
@NoArgsConstructor
public class PartenerCat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCatPart;

	@NotBlank(message = "ce champ ne peut etre vide, un partenair doit avoir au moin un nom ")
	@Column(nullable = false)
	String name;

	public Long getIdCatPart() {
		return idCatPart;
	}

	public void setIdCatPart(Long idCatPart) {
		this.idCatPart = idCatPart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
