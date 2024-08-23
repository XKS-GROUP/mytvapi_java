package com.mytv.api.firebase.model;

import java.time.Instant;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Setter
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Email(message = "Ce champ doit etre une adresse mail valide")
	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique")
	@Column(nullable = false)
	String email;
	
	@Column(columnDefinition = "TEXT")
	String otp;
	
    @JsonIgnoreProperties
    private Instant creation;
    @JsonIgnoreProperties
    private Instant expiration;
    @JsonIgnoreProperties
    private Instant activation;

}
