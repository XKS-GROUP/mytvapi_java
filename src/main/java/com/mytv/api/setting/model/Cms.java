package com.mytv.api.setting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cms {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(columnDefinition = "TEXT")
		String en_ce_moment_tv = "EN CE MOMENT A LA TV";
	    
	    @Column(columnDefinition = "TEXT")
		String notre_selection_chaine_tv = "NOTRE SELECTION DES CHAINES TV ";
	    
	    @Column(columnDefinition = "TEXT")
		String chaine_vedette = "CHAINE VEDETTE";
	    
	    @Column(columnDefinition = "TEXT")
		String thematique_film = "THEMATIQUE DES FILMS";
	    
	    @Column(columnDefinition = "TEXT")
		String notre_selection_film ="NOTRE SELECTION DES FILMS";
	    
	    @Column(columnDefinition = "TEXT")
		String film_vedette = "FILMS VEDETTE";
	    
	    @Column(columnDefinition = "TEXT")
		String films = "FILMS";
	    
	    @Column(columnDefinition = "TEXT")
		String thematique_serie = "THEMATIQUE SERIES";
	    
	    @Column(columnDefinition = "TEXT")
		String notre_selection_series = "NOTRE SELECTION DES SERIES";
	    
	    @Column(columnDefinition = "TEXT")
		String serie_vedette = "SERIE VEDETTE";
	    
	    @Column(columnDefinition = "TEXT")
		String series = "SERIES";
	    
	    @Column(columnDefinition = "TEXT")
		String thematique_radio = "THEMATIQUE RADIOS";
	    
	    @Column(columnDefinition = "TEXT")
		String notre_selection_radio = "NOTRE SELECTION DE RADIO" ;
	    
	    @Column(columnDefinition = "TEXT")
		String radio_vedette = "RADIO VEDETTE";
	    
	    @Column(columnDefinition = "TEXT")
		String radio ="RADIOS";
	    
	    @Column(columnDefinition = "TEXT")
		String article= "ARTICLES";
	    
	    @Column(columnDefinition = "TEXT")
		String acteurs = "ACTEURS";
	    
}
