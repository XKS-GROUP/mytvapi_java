package com.mytv.api.saison.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.mytv.api.firebase.model.FirebaseUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavSaison {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idFavSaison;

	@CreationTimestamp
	Date datePub;
	
	@UpdateTimestamp
	Date dateMaj;
	
	String uid;
	
	@JdbcTypeCode(SqlTypes.JSON)
	private FirebaseUser user;

	@ManyToOne
	@JoinColumn(name = "idSaison", insertable = true, updatable = true)
	private Saison saison;
	
	@Transient
	boolean favorie;
}
