package com.mytv.api.model.gestMedia;

import java.sql.Date;
//import java.util.Set;

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
public class Episode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idEpisode;
	
	String name;
	
	String overView;
	
	@ManyToOne
	@JoinColumn(name = "idSerie")
	Serie serie;
	
	Date realeaseDate;
	
	int numero;
	
	int duration;
	
	boolean status;
	
	String posterUrl;
	
	String typeFile;
	
	String videoFile;
	
	String urlvideo;
	String urlvideo2;
	String urlvideo3;
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
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
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
	public String getTypeFile() {
		return typeFile;
	}
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}
	public String getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}
	public String getUrlvideo() {
		return urlvideo;
	}
	public void setUrlvideo(String urlvideo) {
		this.urlvideo = urlvideo;
	}
	public String getUrlvideo2() {
		return urlvideo2;
	}
	public void setUrlvideo2(String urlvideo2) {
		this.urlvideo2 = urlvideo2;
	}
	public String getUrlvideo3() {
		return urlvideo3;
	}
	public void setUrlvideo3(String urlvideo3) {
		this.urlvideo3 = urlvideo3;
	}
	
	
	

}
