package com.mytv.api.model.gestMedia;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerie;
	
	@NotBlank(message = "une serie doit forcement avoir un nom ")
	@Column(nullable = false)
	String name;
	
	@Column(nullable = false)
	String overview;
	
	String posterUrl;
	
	String trailerURL;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	Date releaseDate;
	
	@OneToMany(mappedBy = "idSaison",cascade = CascadeType.REMOVE)
	List<Saison> saison;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	

}
