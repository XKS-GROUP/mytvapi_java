package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pays {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPays")
	Long idPays;
	
	@NotBlank(message="ce champ ne peut etre vide, un Pays doit forcement avoir un nom")
	@Column(nullable = false)
	String name ;
	
	@NotBlank(message="ce champ ne peut etre vide et doit etre unique, ce champ represente le code unique d'un pays ")
	@Column(nullable = false)
	String slug ;
	
	@OneToMany(mappedBy = "idRadio")
	List<Radio> radio;
	
	@OneToMany(mappedBy = "idLiveTv")
	List<LiveTv> liveTv;

	public Long getIdPays() {
		return idPays;
	}

	public void setIdPays(Long idPays) {
		this.idPays = idPays;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	

	
	


}