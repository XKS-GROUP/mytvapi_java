package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idComment;
	
	@NotBlank(message="un commentaire ne peut etre vide, il dois avoir du contenu")
	@Column(nullable = false, length = 700)
	String contenu;
	
	@CreationTimestamp
	Date datePub;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idFilm", insertable = true, updatable = true)
	private Film movie;
	
	 

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public Date getDatePub() {
		return datePub;
	}

	public void setDatePub(Date datePub) {
		this.datePub = datePub;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Film getMovie() {
		return movie;
	}

	public void setMovie(Film movie) {
		this.movie = movie;
	}
	
	
}
