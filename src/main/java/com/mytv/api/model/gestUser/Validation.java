package com.mytv.api.model.gestUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "validation")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties
    private Instant creation;
    @JsonIgnoreProperties
    private Instant expiration;
    @JsonIgnoreProperties
    private Instant activation;
    
    private String code;
    
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.LAZY , orphanRemoval = true)
    private User utilisateur;

    
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
    
    
}
