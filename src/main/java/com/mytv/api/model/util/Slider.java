package com.mytv.api.model.util;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Slider {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "un nom est requis pour le slider")
	private String name;
	
	@Column(nullable = true  ,columnDefinition = "TEXT")
	private String overview;
	
	@NotEmpty(message = "Une image est requise")
	@Column(nullable = false  ,columnDefinition = "TEXT")
	private String backdrop_path;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean typeVideo;
	
	@Column(nullable = true  ,columnDefinition = "TEXT")
	private String video_path;
	
	private Long position;
	
	private List<Long> targetPage;
	
	private Long idMediaref;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean status;

}
