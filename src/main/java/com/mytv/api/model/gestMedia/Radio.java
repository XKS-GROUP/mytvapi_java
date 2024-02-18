package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Radio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRadio")
	Long idRadio;
	

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idcat", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	CategoryRL category;
	String name ;
	
	String description;

	String poster;
	
	boolean status;

	String streamType;
	
	String svr1;
	String svr2;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPays", nullable = false)
	Pays country;


	public void setCountry(Pays country) {
		this.country = country;
	}


	public Long getIdRadio() {
		return idRadio;
	}


	public void setIdRadio(Long idRadio) {
		this.idRadio = idRadio;
	}


	public CategoryRL getCategory() {
		return category;
	}


	public void setCategory(CategoryRL category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public String getStreamType() {
		return streamType;
	}


	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}


	public String getSvr1() {
		return svr1;
	}


	public void setSvr1(String svr1) {
		this.svr1 = svr1;
	}


	public String getSvr2() {
		return svr2;
	}


	public void setSvr2(String svr2) {
		this.svr2 = svr2;
	}



}