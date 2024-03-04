package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class SerieGenre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSerieGenre;
	

	@ManyToOne
	@JoinColumn(name = "idSerie", insertable = true, updatable = true)
	private Serie Serie;
	
	@ManyToOne
	@JoinColumn(name = "idGenre", insertable = true, updatable = true)
	private Genre genre;

}
