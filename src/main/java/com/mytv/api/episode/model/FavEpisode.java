package com.mytv.api.episode.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.firebase.model.FirebaseUser;

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
public class FavEpisode {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idFav;

	@CreationTimestamp
	Date dateAdd;
	
	String uid;
	
	@JdbcTypeCode(SqlTypes.JSON)
	private FirebaseUser user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idEpisode", insertable = true, updatable = true)
	private Episode episode;
	
}
