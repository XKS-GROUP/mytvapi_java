package com.mytv.api.model.gestPub;

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
public class Publicite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPublicite;

	@NotBlank(message = "ce champ ne peut etre vide, une pub doit avoir une designation ")
	@Column(nullable = false)
	String name;

	@Column(columnDefinition = "TEXT")
	String bannerUrl;

	@Column(columnDefinition = "TEXT")
	String movieUrl;

	@Column(columnDefinition = "TEXT")
	String pageTarget;

	public Long getIdPublicite() {
		return idPublicite;
	}

	public void setIdPublicite(Long idPublicite) {
		this.idPublicite = idPublicite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	public String getPageTarget() {
		return pageTarget;
	}

	public void setPageTarget(String pageTarget) {
		this.pageTarget = pageTarget;
	}



}
