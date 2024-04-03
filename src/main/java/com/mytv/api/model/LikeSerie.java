package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.model.gestMedia.Serie;
import com.mytv.api.model.gestUser.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeSerie {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLike;

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
