package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Lang")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLang;

	@NotBlank(message = "ce champ ne peut pas etre vide")
	@Column(length = 50, nullable = false)
	String name;

	String flag_file;

	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique, ce champ represente le code unique d'une langue")
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
		return flag_file;
	}

	public void setFlag(String flag) {
		this.flag_file = flag;
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
