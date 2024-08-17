package com.mytv.api.radio.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.user.model.User;

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
public class LikeRadio {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLike;

	@CreationTimestamp
	Date datePub;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idRadio", insertable = true, updatable = true)
	private Radio radio;
}
