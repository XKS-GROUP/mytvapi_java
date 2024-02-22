package com.mytv.api.model.gestMedia;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	String poster;
	
	/*@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idCatPod")*/
	@NotNull(message="Un podcast doit forcement avoir une categori")
	Long category;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;

	String streamType;
	
	String svr1;
	
	String svr2;
	
	
	
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
	public String getDescription() {
		return overview;
	}
	public void setDescription(String description) {
		this.overview = description;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
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
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getSvr1() {
		return svr1;
	}
	public void setSvr1(String svr1) {
		this.svr1 = svr1;
	}
	public String getSvr2() {
		return svr2;
	}
	public void setSvr2(String svr2) {
		this.svr2 = svr2;
	}
	
	
	
	


}