package com.mytv.api.replay.collection;

import java.util.List;

import com.mytv.api.replay.intervenant.model.Intervenant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

public class ReplayCollection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;

	@Column(nullable = false, length = 80, unique = true)
	@NotBlank(message = "Ce champ ne puis etre vide")
	String name;
	
	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false, columnDefinition = "TEXT")
	String cover;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<Intervenant> intervenants;
	
}
