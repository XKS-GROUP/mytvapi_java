package com.mytv.api.ressource.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class SerieGenre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerieGenre;

	/*
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idSerie") */
	private Long Serie;

	//@ManyToOne
	//@JoinColumn(name = "idGenre", insertable = true, updatable = true)
	private Long genre;

}
