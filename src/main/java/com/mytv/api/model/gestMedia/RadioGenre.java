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
public class RadioGenre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRadioGenre")
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "idRadio", insertable = true, updatable = true)
	private Radio radio;
	
	@ManyToOne
	@JoinColumn(name = "idGenre", insertable = true, updatable = true)
	private Genre genre;
}
