package com.mytv.api.model.gestUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "validation")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant creation;
    private Instant expiration;
    private Instant activation;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private User utilisateur;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Instant getCreation() {
		return creation;
	}
	public void setCreation(Instant creation) {
		this.creation = creation;
	}
	public Instant getExpiration() {
		return expiration;
	}
	public void setExpiration(Instant expiration) {
		this.expiration = expiration;
	}
	public Instant getActivation() {
		return activation;
	}
	public void setActivation(Instant activation) {
		this.activation = activation;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public User getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}
    
    
    
}
