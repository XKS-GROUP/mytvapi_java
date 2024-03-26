package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class LiveTv {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLiveTv")
	Long idLiveTv;

	@NotBlank(message="ce champ ne peut pas etre vide")
	@Column(nullable = false)
	String name ;

	@NotBlank(message="ce champ ne peut pas etre vide")
	@Column(nullable = false)
	String overview;

	String tvLogo_path;

	@NotNull(message="ce champ ne peut pas etre vide, un live tv doit avoir une categorie")
	@Column(nullable = false)
	Long idcategory;

	@NotNull(message="ce champ ne peut pas etre vide, un live tv doit avoir un status qui permet de le rendre visible ou pas")
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;


	String svr1_url;

	String svr2_url;

	String svr3_url;

	String tvEmbedCode;

	/*@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPays", nullable = false)*/

	@NotNull(message="Ce champ ne peut pas etre vide")
	@Column(nullable = false)
	Long country;

	public Long getIdLiveTv() {
		return idLiveTv;
	}

	public void setIdLiveTv(Long idLiveTv) {
		this.idLiveTv = idLiveTv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTvEmbedCode() {
		return tvEmbedCode;
	}

	public void setTvEmbedCode(String tvEmbedCode) {
		this.tvEmbedCode = tvEmbedCode;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public Long getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(Long idcategory) {
		this.idcategory = idcategory;
	}

	public String getTvLogo_path() {
		return tvLogo_path;
	}

	public void setTvLogo_path(String tvLogo_path) {
		this.tvLogo_path = tvLogo_path;
	}

	public String getSvr1_url() {
		return svr1_url;
	}

	public void setSvr1_url(String svr1_url) {
		this.svr1_url = svr1_url;
	}

	public String getSvr2_url() {
		return svr2_url;
	}

	public void setSvr2_url(String svr2_url) {
		this.svr2_url = svr2_url;
	}

	public String getSvr3_url() {
		return svr3_url;
	}

	public void setSvr3_url(String svr3_url) {
		this.svr3_url = svr3_url;
	}


}