package com.mytv.api.util;

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
public class PubliciteCat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCatPub;

	@NotBlank(message = "ce champ ne peut etre vide ")
	@Column(nullable = false)
	String name;

	public Long getIdCatPub() {
		return idCatPub;
	}

	public void setIdCatPub(Long idCatPub) {
		this.idCatPub = idCatPub;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
