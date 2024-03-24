package com.mytv.api.model.gestMedia;

import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDirector;
	
	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String fistName;
	
	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String lastName;
	
	Date dob;
	
	String imageUrl;
	
	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String biography;
	
	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	Long pays;
	
	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	Long idTypecontt;

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Long getNationality() {
		return pays;
	}

	public void setNationality(Long nationality) {
		this.pays = nationality;
	}

	public Long getIdTypecontt() {
		return idTypecontt;
	}

	public void setIdTypecontt(Long idTypecontt) {
		this.idTypecontt = idTypecontt;
	}

	public Long getPays() {
		return pays;
	}

	public void setPays(Long pays) {
		this.pays = pays;
	}

	public Long getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(Long idDirector) {
		this.idDirector = idDirector;
	}
	
	

}
