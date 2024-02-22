package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class ContributorType {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idcontType;
	
	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String name;
	
	@OneToMany(mappedBy = "idContributor")
	Set<Contributor> contributors;

	public Long getIdcontType() {
		return idcontType;
	}

	public void setIdcontType(Long idcontType) {
		this.idcontType = idcontType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors(Set<Contributor> contributors) {
		this.contributors = contributors;
	}
	
	
	

}
