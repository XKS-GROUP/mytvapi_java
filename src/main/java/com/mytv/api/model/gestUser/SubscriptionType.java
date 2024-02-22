package com.mytv.api.model.gestUser;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@NoArgsConstructor

public class SubscriptionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSubscriptionType;
	
	@NotBlank(message = "un abonnement doit avoir un nom")
	@Column(nullable = false)
	String name;
	
	@NotBlank(message = "un abonnement doit avoir une description")
	@Column(nullable = false)
	String overview;
	
	@NotNull(message = "un abonnement doit avoir un prix, marquer 0 pour les abonnement gratuit")
	@Column(nullable = false)
	float price;
	
	@OneToMany(mappedBy = "id")
	List<User> users;

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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public List<User> getUsers() {
		return users;
	}
	
	@JsonIgnore
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	

}
