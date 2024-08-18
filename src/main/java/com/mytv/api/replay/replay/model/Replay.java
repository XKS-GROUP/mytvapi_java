package com.mytv.api.replay.replay.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.mytv.api.replay.categorie.model.ReplayCateg;
import com.mytv.api.replay.intervenant.model.Intervenant;
import com.mytv.api.ressource.model.Language;

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
	
	@NotBlank(message = "ce champ ne peut etre vide, un film doit forcement posseder un nom")
	String name;
	
	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour un film")
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;
	
	@ManyToMany
	@JoinTable(
        name = "replaycateg_replay",
        joinColumns = @JoinColumn(name = "replay_id"),
        inverseJoinColumns = @JoinColumn(name = "categ_id")
		    )
	List<ReplayCateg> categories;
	
	List<Long> idCategories;
	
	Long collections;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> langues;
	List<Long> idLangues;
	
	String cover;
	
	String video_url;
	
	String audio_url;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<Intervenant> intervenants;
	
	List<Long> idIntervenants;
	
	@CreatedDate
	Date add_date;
	
	Date expired_date;
	
}
