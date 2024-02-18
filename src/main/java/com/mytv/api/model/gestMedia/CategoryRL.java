package com.mytv.api.model.gestMedia;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "idRadio")
	List<Radio> radio;
	
	@OneToMany(mappedBy = "idLiveTv")
	List<LiveTv> liveTv;
	

	private String designation;


	public Long getIdcat() {
		return idcat;
	}


	public void setIdcat(Long idcat) {
		this.idcat = idcat;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
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
