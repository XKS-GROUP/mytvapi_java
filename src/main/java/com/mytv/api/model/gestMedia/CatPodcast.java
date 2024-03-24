package com.mytv.api.model.gestMedia;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class CatPodcast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCatPod")
	Long idCatPod;
	
	@NotBlank(message="ce champ ne peut etre vide")
	@Column(nullable = false)
	String name;
	
	@NotBlank(message = "Une image est requise")
	@Column(nullable = false)
	private String img;
	/*
	@OneToMany(mappedBy = "idPodcast")
	List<Podcast> podcast;
	*/

	public Long getIdCatPod() {
		return idCatPod;
	}

	public void setIdCatPod(Long idCatPod) {
		this.idCatPod = idCatPod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
