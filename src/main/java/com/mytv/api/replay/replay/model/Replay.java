package com.mytv.api.replay.replay.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

import com.mytv.api.replay.categorie.model.ReplayCateg;
import com.mytv.api.replay.collection.model.ReplayCollection;
import com.mytv.api.replay.intervenant.model.Intervenant;
import com.mytv.api.ressource.model.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Replay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	
	String overview;
	
	
	@ManyToMany
	@JoinTable(
		        name = "replaycateg_replay",
		        joinColumns = @JoinColumn(name = "replay_id"),
		        inverseJoinColumns = @JoinColumn(name = "categ_id")
		    )
	List<ReplayCateg> categories;
	
	List<Long> idCategorie;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<ReplayCollection> collections;
	
	List<Long> idCollections;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> langues;
	List<Long> idLangues;
	
	String cover;
	
	String video_url;
	
	String audio_url;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<Intervenant> intervenants;
	List<Long> idIntervenants;
	
	Date add_date;
	Date expired_date;
}
