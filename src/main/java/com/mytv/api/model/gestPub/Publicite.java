package com.mytv.api.model.gestPub;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Publicite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPublicite;

	@NotBlank(message = "ce champ ne peut etre vide, une pub doit avoir une designation ")
	@Column(nullable = false)
	String name;
	
	
	@Column(nullable = true  ,columnDefinition = "TEXT")
	private String overview;
    
	@NotBlank(message = "ce champ ne peut etre vide, une publicité doit avoir une bannière ")
	@Column(nullable = false , columnDefinition = "TEXT")
	String bannerPath;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean typeVideo;
	
	@Column(columnDefinition = "TEXT")
	String videoPath;

	List<Long> pageTarget;
	
	List<Long> sectionTarget;

}
