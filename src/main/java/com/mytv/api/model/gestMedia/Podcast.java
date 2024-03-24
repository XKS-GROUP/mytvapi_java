package com.mytv.api.model.gestMedia;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Podcast {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPodcast")
	Long idPodcast;
	
	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir un nom")
	@Column(nullable = false)
	String name ;
	
	@NotBlank(message="ce champ ne peut etre vide, un podcast doit forcement avoir une description")
	String overview;
	
	@NotNull(message = "une image miniature est requise pour un podcast")
	String backdrop_path;
	
	String poster_path;
	
	@NotNull(message="Un podcast doit forcement avoir une categori")
	Long category;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	String fileLink;
	
	String streamlink;
	
	
	public Long getIdPodcast() {
		return idPodcast;
	}
	public void setIdPodcast(Long idPodcast) {
		this.idPodcast = idPodcast;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}

	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStreamlink() {
		return streamlink;
	}
	public void setStreamlink(String streamlink) {
		this.streamlink = streamlink;
	}
	public String getFileLink() {
		return fileLink;
	}
	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

}