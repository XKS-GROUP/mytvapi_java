package com.mytv.api.model.gestMedia;

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
public class LiveTvGenre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLiveTvGenre")
	Long idFilmGenre;
	
	
	@ManyToOne
	@JoinColumn(name = "idLiveTv", insertable = true, updatable = true)
	private LiveTv livetv;
	
	@ManyToOne
	@JoinColumn(name = "idGenre", insertable = true, updatable = true)
	private Genre genre;

}
