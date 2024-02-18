package com.mytv.api.model.gestMedia;


import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
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
public class Podcast {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPodcast")
	Long idPodcast;
	
	@Column(nullable = false)
	String name ;
	
	String description;

	String poster;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idCatPod")
	CatPodcast category;
	
	boolean status;

	String streamType;
	
	String svr1;
	String svr2;
	


}