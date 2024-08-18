package com.mytv.api.replay.categorie.model;

import java.util.List;

import com.mytv.api.replay.replay.model.Replay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplayCateg {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;

	@Column(nullable = false, length = 80, unique = true)
	@NotBlank(message = "Ce champ ne puis etre vide")
	String name;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;

	@NotBlank(message = "Une image est requise")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String img_path;
	
	@ManyToMany(mappedBy = "categs")
	private List<Replay> replays ;
}
