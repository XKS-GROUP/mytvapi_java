package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;

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
@Getter
@Setter
@NoArgsConstructor
public class Favorite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idComment;
	
	@CreationTimestamp
	Date dateAdd;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idFilm", insertable = true, updatable = true)
	private Film movie;

}
