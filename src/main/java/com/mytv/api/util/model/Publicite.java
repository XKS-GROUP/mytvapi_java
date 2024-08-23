package com.mytv.api.util.model;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@Column(nullable = true , columnDefinition = "TEXT")
	String bannerPath;

	@Column(nullable = true, columnDefinition = "boolean default false")
	private boolean typeVideo;
	
	@Column(columnDefinition = "TEXT")
	String videoPath;
	
	
	@NotBlank(message = "ce champ ne peut etre vide, une pub doit avoir une cible ")
	@Column(nullable = false  ,columnDefinition = "TEXT")
	private String targetUrl;
	
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, Integer> position;
	
	@NotNull(message = "un satus par defaut doit être attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

}
