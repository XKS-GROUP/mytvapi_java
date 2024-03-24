package com.mytv.api.model.gestMedia;

import java.sql.Date;
//import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
	
	@NotBlank(message = "Ce champ ne peut etre vide, un episode doit forcement faire reference a une serie")
	@Column(nullable = false)
	Long idSerie;
	
	@NotBlank(message = "Ce champ ne peut etre vide, un episode doit forcement faire reference a une saison")
	@Column(nullable = false)
	Long idSaison;
	
	@CreatedDate
	Date addDate;
	
	Date realeaseDate;
	
	@Column(nullable = false)
	int numero;
	
	String duration;
	
	@NotNull(message = "un satus par defaut doit etre attribuer")
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@NotNull(message = "une image miniature est requise pour un episode")
	String thumbnail;
	
	String posterUrl;
	
	String trailer;
	
	@NotNull(message = "un fichier fideo initial est requis pour un episode")
	String videoFile;
	
	String videoFile480pLocal;
	String videoFile480pUrl;
	
	String videoFile720pLocal;
	String videoFile720pUrl;
	
	String videoFile1080pLocal;
	String videoFile1080pUrl;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;
	
	String  downloadURL;

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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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

	
	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getVideoFile480pLocal() {
		return videoFile480pLocal;
	}

	public void setVideoFile480pLocal(String videoFile480pLocal) {
		this.videoFile480pLocal = videoFile480pLocal;
	}

	public String getVideoFile480pUrl() {
		return videoFile480pUrl;
	}

	public void setVideoFile480pUrl(String videoFile480pUrl) {
		this.videoFile480pUrl = videoFile480pUrl;
	}

	public String getVideoFile720pLocal() {
		return videoFile720pLocal;
	}

	public void setVideoFile720pLocal(String videoFile720pLocal) {
		this.videoFile720pLocal = videoFile720pLocal;
	}

	public String getVideoFile720pUrl() {
		return videoFile720pUrl;
	}

	public void setVideoFile720pUrl(String videoFile720pUrl) {
		this.videoFile720pUrl = videoFile720pUrl;
	}

	public String getVideoFile1080pLocal() {
		return videoFile1080pLocal;
	}

	public void setVideoFile1080pLocal(String videoFile1080pLocal) {
		this.videoFile1080pLocal = videoFile1080pLocal;
	}

	public String getVideoFile1080pUrl() {
		return videoFile1080pUrl;
	}

	public void setVideoFile1080pUrl(String videoFile1080pUrl) {
		this.videoFile1080pUrl = videoFile1080pUrl;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public Long getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Long idSerie) {
		this.idSerie = idSerie;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Long getIdSaison() {
		return idSaison;
	}

	public void setIdSaison(Long idSaison) {
		this.idSaison = idSaison;
	}
	
}
