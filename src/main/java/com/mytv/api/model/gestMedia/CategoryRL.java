package com.mytv.api.model.gestMedia;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class CategoryRL {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcat")
	Long idcat;
	
	@NotBlank(message = "ce champ ne peut pas etre vide")
	private String name;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean status;
	
	@OneToMany(mappedBy = "idRadio")
	List<Radio> radio;
	
	@OneToMany(mappedBy = "idLiveTv")
	List<LiveTv> liveTv;
	




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


	public List<Radio> getRadio() {
		return radio;
	}


	public void setRadio(List<Radio> radio) {
		this.radio = radio;
	}


	public List<LiveTv> getLiveTv() {
		return liveTv;
	}


	public void setLiveTv(List<LiveTv> liveTv) {
		this.liveTv = liveTv;
	}
	
	
	
	

}
