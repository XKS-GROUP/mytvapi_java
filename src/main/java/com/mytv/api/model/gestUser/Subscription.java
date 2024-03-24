package com.mytv.api.model.gestUser;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

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
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idAbonnement;
	
	Long idUtilisateur;
	
	Long idSubType;
	
	@CreationTimestamp
	Date datebegin;
	
	@CreationTimestamp
	Date dateEnd;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idSubscriptionType", insertable = true, updatable = true)
	private SubscriptionType substypes;
	
	

	public Long getIdAbonnement() {
		return idAbonnement;
	}

	public void setIdAbonnement(Long idAbonnement) {
		this.idAbonnement = idAbonnement;
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Long getIdSubType() {
		return idSubType;
	}

	public void setIdSubType(Long idSubType) {
		this.idSubType = idSubType;
	}

	public Date getDatebegin() {
		return datebegin;
	}

	public void setDatebegin(Date datebegin) {
		this.datebegin = datebegin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SubscriptionType getSubstypes() {
		return substypes;
	}

	public void setSubstypes(SubscriptionType substypes) {
		this.substypes = substypes;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	
}
