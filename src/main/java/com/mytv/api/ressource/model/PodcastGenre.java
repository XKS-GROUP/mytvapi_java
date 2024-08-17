package com.mytv.api.ressource.model;

import com.mytv.api.podcast.model.Podcast;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PodcastGenre {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPodcastGenre")
	Long id;

	@ManyToOne
	@JoinColumn(name = "idPodcast", insertable = true, updatable = true)
	private Podcast podcast;

	@ManyToOne
	@JoinColumn(name = "idGenre", insertable = true, updatable = true)
	private Genre genre;

}
