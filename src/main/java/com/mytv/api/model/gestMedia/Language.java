package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lang")
public class Language {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLang;
	
	@Column(length = 25, nullable = false)
	String name;
	
	String flag;
	
	@Column(nullable = false, unique = true)
	String slug;
	
	boolean status;

	public Long getIdLang() {
		return idLang;
	}

	public void setIdLang(Long idLang) {
		this.idLang = idLang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	
	
	

}
