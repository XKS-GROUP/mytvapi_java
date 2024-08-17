package com.mytv.api.subscription.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mytv.api.payment.model.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSubscriptionType;

	@NotBlank(message = "un nom est requis")
	@Column(nullable = false)
	String name;

	@NotNull(message = "gratuit ou payant, cet champ booléean est requis ")
	@Column(columnDefinition = "boolean default false")
	boolean free;
	
	
	@NotNull(message = "un abonnement doit avoir un prix, marquer 0 pour les abonnement gratuit")
	@Column(nullable = false)
	float price;

	@NotNull(message = "un abonnement doit avoir une limite de nombre de periphérique ")
	@Column(nullable = false)
	int deviceLimit;

	@NotNull(message = "un abonnement doit avoir une limite de nombre de jour limite ")
	@Column(nullable = false)
	int nbr;


	@NotNull(message = "avec ou sans publicité, cet champ booléean est requi ")
	@Column(columnDefinition = "boolean default false")
	boolean Ads;
	

	@NotNull(message = "actif ou pas, cet champ booléean est requi ")
	@Column(columnDefinition = "boolean default false")
	boolean Status;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "id")
	List<Transaction> transaction;
	
}
