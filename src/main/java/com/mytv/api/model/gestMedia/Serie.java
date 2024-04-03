package com.mytv.api.model.gestMedia;

import java.util.ArrayList;
import java.util.Date;
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
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerie;

	@NotBlank(message = "une serie doit forcement avoir un nom ")
	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	String overview;

	/*
	@OneToMany(mappedBy = "idSaison",cascade = CascadeType.REMOVE)
	List<Saison> saison;
	*/

	boolean Upcoming;

	@CreatedDate
	Date addDate;

	Date releaseDate;

	String IMDbRating;

	String ContentRating;

	Long budget;


	String trailerUrl;
	String trailer;

	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean AccessFree;

	@NotNull(message = "une image miniature est requise pour une serie")
	String backdrop_path;

	String poster_path;

	String Langue;

	String popularity;

	String vote_average;

	String vote_count;


	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;


	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean adult;


	List <String> acteurList = new ArrayList<>();

	List <String> directorList = new ArrayList<>();

	List<String> genreList = new ArrayList<>();

	public Long getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Long idSerie) {
		this.idSerie = idSerie;
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

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public Boolean getAccessFree() {
		return AccessFree;
	}

	public void setAccessFree(Boolean accessFree) {
		AccessFree = accessFree;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public List<String> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<String> directorList) {
		this.directorList = directorList;
	}



}
