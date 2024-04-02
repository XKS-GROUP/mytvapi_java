package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.model.gestMedia.Radio;
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
public class FavRadio {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idFav;

	@CreationTimestamp
	Date dateAdd;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "idRadio", insertable = true, updatable = true)
	private Radio radio;
	
}
