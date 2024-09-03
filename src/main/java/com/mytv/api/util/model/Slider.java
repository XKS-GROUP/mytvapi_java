package com.mytv.api.util.model;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
	
	@Column(nullable = true  ,columnDefinition = "TEXT")
	private String target_url;
	
	@Column(nullable = true  ,columnDefinition = "TEXT")
	private String backdrop_path;
	
	@Column(nullable = true  ,columnDefinition = "TEXT")
	private String video_path;
	
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, Integer> position;
	
	//private List<String> targetPage;
	
	private String typeMedia;
	
	private Long idMediaref;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean status;

}
