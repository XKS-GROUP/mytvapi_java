package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGenre", nullable = false)
	Long idGenre;
	
	@Column(nullable = false, length = 80, unique = true)
	@NotBlank(message = "Ce champ ne puis etre vide")
	String name;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@NotBlank(message = "Une image est requise")
	@Column(nullable = false)
	private String img_path;
	
	public Long getIdGenre() {
		return idGenre;
	}

	
	public void setIdGenre(Long idGenre) {
		this.idGenre = idGenre;
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

	public String getImg() {
		return img_path;
	}

	public void setImg(String img) {
		this.img_path = img;
	}
	
}
