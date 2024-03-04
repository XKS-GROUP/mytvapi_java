package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

	String tvLogo;
	
	@NotNull(message="ce champ ne peut pas etre vide, un live tv doit avoir une categorie")
	@Column(nullable = false)
	Long idcategory;
	
	@NotNull(message="ce champ ne peut pas etre vide, un live tv doit avoir un status qui permet de le rendre visible ou pas")
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	
	String svr1;
	
	String svr2;
	
	String svr3;
	
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

	public String getTvLogo() {
		return tvLogo;
	}

	public void setTvLogo(String tvLogo) {
		this.tvLogo = tvLogo;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getSvr3() {
		return svr3;
	}

	public void setSvr3(String svr3) {
		this.svr3 = svr3;
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

}