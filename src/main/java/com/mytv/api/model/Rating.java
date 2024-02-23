package com.mytv.api.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idRating;
	
	@NotNull(message = " une valeur est requise variant de 0-10")
	float score;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idFilm", insertable = true, updatable = true)
	private Film movie;

}
