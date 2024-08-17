package com.mytv.api.serie.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComSerie {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idComment;
	
	
	@NotBlank(message=" un commentaire ne peut etre vide, il dois avoir du contenu ")
	@Column(nullable = false, length = 700)
	String contenu;

	@CreationTimestamp
	Date datePub;
	
	@UpdateTimestamp
	Date dateMaj;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;

	@ManyToOne
	@JoinColumn(name = "idSerie", insertable = true, updatable = true)
	private Serie serie;
}
