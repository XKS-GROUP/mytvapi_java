package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "idcat", nullable = false, insertable = true, updatable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	CategoryRL category;
	
	@Column(nullable = false)
	String name ;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	String overview;

	String poster;
	
	boolean status;
	
	@Column(nullable = false)
	String streamLink;
		
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "idPays", nullable = false, insertable = true, updatable = true)
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


	public Pays getCountry() {
		return country;
	}
	
	

}