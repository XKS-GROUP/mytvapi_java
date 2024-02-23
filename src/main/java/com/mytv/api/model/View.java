package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class View {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idVue;
	
	@CreationTimestamp
	Date ViewDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idFilm", insertable = true, updatable = true)
	private Film movie;

	public Long getIdVue() {
		return idVue;
	}

	public void setIdVue(Long idVue) {
		this.idVue = idVue;
	}

	public Date getViewDate() {
		return ViewDate;
	}

	public void setViewDate(Date viewDate) {
		ViewDate = viewDate;
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
