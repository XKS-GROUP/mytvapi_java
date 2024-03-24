package com.mytv.api.model.gestUser;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSubscriptionType;
	
	@NotBlank(message = "un nom est requi")
	@Column(nullable = false)
	String name;
	
	@NotNull(message = "un abonnement doit avoir un prix, marquer 0 pour les abonnement gratuit")
	@Column(nullable = false)
	float price;
	
	@NotNull(message = "un abonnement doit avoir une limite de nombre de periphérique ")
	@Column(nullable = false)
	int deviceLimit;
	
	@NotNull(message = "un abonnement doit avoir une limite de nombre de mois ")
	@Column(nullable = false)
	int nbMonth;
	
	
	@NotNull(message = "avec ou sans publicité, cet champ booléean est requi ")
	@Column(columnDefinition = "boolean default false")
	boolean Ads;
	
	@NotNull(message = "actif ou pas, cet champ booléean est requi ")
	@Column(columnDefinition = "boolean default false")
	boolean Status;
	
	/*
	@JsonIgnoreProperties
	@OneToMany(mappedBy = "id")
	List<User> users;
	*/

	public Long getIdSubscriptionType() {
		return idSubscriptionType;
	}

	public void setIdSubscriptionType(Long idSubscriptionType) {
		this.idSubscriptionType = idSubscriptionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDeviceLimit() {
		return deviceLimit;
	}

	public void setDeviceLimit(int deviceLimit) {
		this.deviceLimit = deviceLimit;
	}

	public boolean isAds() {
		return Ads;
	}

	public void setAds(boolean ads) {
		Ads = ads;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public int getNbMonth() {
		return nbMonth;
	}

	public void setNbMonth(int nbMonth) {
		this.nbMonth = nbMonth;
	}
	
}
