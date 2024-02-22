package com.mytv.api.model.gestMedia;

import java.sql.Date;
//import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Episode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;
	
	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String name;
	
	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String overView;
	
	/*@ManyToOne
	@JoinColumn(name = "idSerie")
	Serie serie; */
	
	@NotBlank(message = "Ce champ ne peut etre vide, un episode doit forcement faire reference a une serie")
	@Column(nullable = false)
	Long serie;
	
	Date realeaseDate;
	
	@Column(nullable = false)
	int numero;
	
	String duration;
	
	@NotNull(message = "un satus par defaut doit etre attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	String posterUrl;
	
	String videoFile;
	
	String videoFileR1;
	
	String videoFileR2;
	
	String videoFileR3;
	
	public Long getIdEpisode() {
		return idEpisode;
	}
	public void setIdEpisode(Long idEpisode) {
		this.idEpisode = idEpisode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOverView() {
		return overView;
	}
	public void setOverView(String overView) {
		this.overView = overView;
	}

	public Long getSerie() {
		return serie;
	}
	public void setSerie(Long serie) {
		this.serie = serie;
	}
	public Date getRealeaseDate() {
		return realeaseDate;
	}
	public void setRealeaseDate(Date realeaseDate) {
		this.realeaseDate = realeaseDate;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getPosterUrl() {
		return posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	
	public String getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}
	public String getVideoFileR1() {
		return videoFileR1;
	}
	public void setVideoFileR1(String videoFileR1) {
		this.videoFileR1 = videoFileR1;
	}
	public String getVideoFileR2() {
		return videoFileR2;
	}
	public void setVideoFileR2(String videoFileR2) {
		this.videoFileR2 = videoFileR2;
	}
	public String getVideoFileR3() {
		return videoFileR3;
	}
	public void setVideoFileR3(String videoFileR3) {
		this.videoFileR3 = videoFileR3;
	}
	
	
	

}
