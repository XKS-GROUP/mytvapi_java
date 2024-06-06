package com.mytv.api.model.gestUser;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idAbonnement;

	Long idUtilisateur;

	Long idSubType;

	@CreationTimestamp
	Date datebegin;

	Date dateEnd;
	
	@Column(columnDefinition = "boolean default false")
	boolean ads;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;

	@ManyToOne
	@JoinColumn(name = "idSubscriptionType", insertable = true, updatable = true)
	private SubscriptionType substypes;
	
	@NotNull(message = "actif ou pas, cet champ booléean est requi ")
	@Column(columnDefinition = "boolean default false")
	boolean Status;

}
