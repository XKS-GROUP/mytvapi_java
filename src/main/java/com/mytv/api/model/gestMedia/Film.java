package com.mytv.api.model.gestMedia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
public class Film {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFilm")
	Long idFilm;

	Long IMDbId;

	@NotBlank(message = "ce champ ne peut etre vide, un film doit forcement posseder un nom")
	@Column(nullable = false)
	String name;

	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour un film")
	@Column(nullable = false)
	String overview;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean Upcoming;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	String IMDbRating;

	String ContentRating;

	float budget;


	String trailerUrl;
	String trailer;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean movieAccessFree;

	@NotNull(message = "une image miniature est requise pour un film")
	String backdrop_path;

	String poster;

	String Langue;

	String popularity;

	String vote_average;

	String vote_count;

	//@NotNull(message = "un fichier video initial est requis pour un film")
	String videoFile;

	String videoFile480pLocal;
	String videoFile480pUrl;

	String videoFile720pLocal;
	String videoFile720pUrl;

	String videoFile1080pLocal;
	String videoFile1080pUrl;

	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean download;

	String  downloadURL;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;


	List <String> acteurList = new ArrayList<>();

	List <String> directorList = new ArrayList<>();

	List<String> genreList = new ArrayList<>();

	public Long getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}

	public Long getIMDbId() {
		return IMDbId;
	}

	public void setIMDbId(Long iMDbId) {
		IMDbId = iMDbId;
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

	public boolean isUpcoming() {
		return Upcoming;
	}

	public void setUpcoming(boolean upcoming) {
		Upcoming = upcoming;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIMDbRating() {
		return IMDbRating;
	}


	public void setIMDbRating(String iMDbRating) {
		IMDbRating = iMDbRating;
	}

	public String getContentRating() {
		return ContentRating;
	}

	public void setContentRating(String contentRating) {
		ContentRating = contentRating;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getBackdrop_path() {
		return backdrop_path;
	}

	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}


	public String getLangue() {
		return Langue;
	}

	public void setLangue(String langue) {
		Langue = langue;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getVote_average() {
		return vote_average;
	}

	public void setVote_average(String vote_average) {
		this.vote_average = vote_average;
	}

	public String getVote_count() {
		return vote_count;
	}

	public void setVote_count(String vote_count) {
		this.vote_count = vote_count;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public List<String> getActeurList() {
		return acteurList;
	}

	public void setActeurList(List<String> acteurList) {
		this.acteurList = acteurList;
	}

	public List<String> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<String> genreList) {
		this.genreList = genreList;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public Boolean getMovieAccessFree() {
		return movieAccessFree;
	}

	public void setMovieAccessFree(Boolean movieAccessFree) {
		this.movieAccessFree = movieAccessFree;
	}

	public List<String> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<String> directorList) {
		this.directorList = directorList;
	}

}