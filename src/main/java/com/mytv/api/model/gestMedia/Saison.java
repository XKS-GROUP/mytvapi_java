package com.mytv.api.model.gestMedia;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Saison {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSaison;
	
	@NotBlank(message = "ce champs ne peut pas etre null")
	@Column(nullable = false)
	String name;
	
	@NotBlank(message = "ce champs ne peut pas etre null")
	@Column(nullable = false)
	String overview;
	
	@NotNull(message ="Ce champs ne peut pas etre null ou vide, une saison doit forcement faire reference a une serie")
	@Column(nullable = false)
	Long idSerie;
	
	@NotNull(message = "une image miniature est requise pour une serie")
	String backdrop_path;
	
	String posterUrl;
	
	String trailerURL;
	String trailer;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@CreatedDate
	Date addDate;
	
	Date releaseDate;
	
	@OneToMany(mappedBy = "idEpisode",cascade = CascadeType.REMOVE)
	List<Episode> episode;

	public Long getIdSaison() {
		return idSaison;
	}

	public void setIdSaison(Long idSaison) {
		this.idSaison = idSaison;
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

	public Long getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Long idSerie) {
		this.idSerie = idSerie;
	}

	public String getBackdrop_path() {
		return backdrop_path;
	}

	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getTrailerURL() {
		return trailerURL;
	}

	public void setTrailerURL(String trailerURL) {
		this.trailerURL = trailerURL;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public List<Episode> getEpisode() {
		return episode;
	}

	public void setEpisode(List<Episode> episode) {
		this.episode = episode;
	}
	
	
	
	

}
