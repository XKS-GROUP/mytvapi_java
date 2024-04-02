
package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestUser.User;

import jakarta.persistence.CascadeType;
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
public class LikeSaison {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLike;

	@CreationTimestamp
	Date datePub;
	
	@UpdateTimestamp
	Date dateMaj;
	
	@JsonIgnore
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;

	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "idSaison", insertable = true, updatable = true)
	private Saison saison;
}
