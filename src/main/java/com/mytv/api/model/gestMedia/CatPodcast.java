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
public class CatPodcast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCatPod")
	Long idCatPod;
	
	@Column(nullable = false)
	String name;
	
	@OneToMany(mappedBy = "idPodcast")
	List<Podcast> podcast;

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

	public List<Podcast> getPodcast() {
		return podcast;
	}

	public void setPodcast(List<Podcast> podcast) {
		this.podcast = podcast;
	}
	
	
	
	

}
