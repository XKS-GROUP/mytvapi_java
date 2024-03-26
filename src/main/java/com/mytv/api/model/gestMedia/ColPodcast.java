package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;

//import java.sql.Date;
//import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor

public class ColPodcast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idColPd;

	@NotBlank(message="ce champ ne peut etre nulle")
	@Column(nullable = false)
	String name;

	@NotBlank(message="ce champ ne peut etre nulle")
	@Column(nullable = false)
	String overView;

	@NotBlank(message = "Une image est requise")
	@Column(nullable = false)

	private String cover;

	@NotNull(message = "un satus par defaut doit etre attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOverView() {
		return overView;
	}

	public void setOverView(String overView) {
		this.overView = overView;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getIdColPd() {
		return idColPd;
	}

	public void setIdColPd(Long idColPd) {
		this.idColPd = idColPd;
	}

}
