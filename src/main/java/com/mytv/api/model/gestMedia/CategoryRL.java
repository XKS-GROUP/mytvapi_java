package com.mytv.api.model.gestMedia;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor

public class CategoryRL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcat")
	Long idcat;

	@NotBlank(message = "ce champ ne peut pas etre vide")
	private String name;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean status;

	@NotBlank(message = "Une image est requise")
	@Column(nullable = false)
	private String img_path;

	public Long getIdcat() {
		return idcat;
	}


	public void setIdcat(Long idcat) {
		this.idcat = idcat;
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


	public String getImg_path() {
		return img_path;
	}


	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

}
