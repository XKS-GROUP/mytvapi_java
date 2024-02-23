package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Radio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRadio")
	Long idRadio;
	
	/*
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "idcat", nullable = false, insertable = true, updatable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	CategoryRL category;*/
	
	@NotBlank(message = "ce champ ne peut etre vide, une radio doit obligatoirement avoir un nom")
	@Column(nullable = false)
	String name ;
	
	@Column(nullable = false)
	Long category;
	
	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour une radio")
	@Column(columnDefinition = "TEXT", nullable = false)
	String overview;

	String poster;
	
	@NotNull(message = "ce champ ne peut etre vide, une valeur par defaut doit etre attribuer")
	@Column(columnDefinition = "boolean default true")
	boolean status;
	
	@NotBlank(message = "ce champ ne peut etre vide, une radio doit avoir une source")
	@Column(nullable = false)
	String streamLink;
		
	/*@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "idPays", nullable = false, insertable = true, updatable = true)
	Pays country;*/
	
	@NotNull(message = "ce champ ne peut etre vide, une radio doit etre représenter par un pays")
	@Column(nullable = false)
	Long country;

	public Long getIdRadio() {
		return idRadio;
	}


	public void setIdRadio(Long idRadio) {
		this.idRadio = idRadio;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOverview() {
		return overview;
	}


	public void setOverview(String overview) {
		this.overview = overview;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getStreamLink() {
		return streamLink;
	}


	public void setStreamLink(String streamLink) {
		this.streamLink = streamLink;
	}


	public Long getCategory() {
		return category;
	}


	public void setCategory(Long category) {
		this.category = category;
	}


	public Long getCountry() {
		return country;
	}


	public void setCountry(Long country) {
		this.country = country;
	}

	
	
	

}