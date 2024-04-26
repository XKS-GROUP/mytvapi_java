package com.mytv.api.model.gestMedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Pays {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPays")
	Long idPays;

	@NotBlank(message="ce champ ne peut etre vide, un Pays doit forcement avoir un nom")
	@Column(nullable = false)
	String name ;

	@NotBlank(message="ce champ ne peut etre vide et doit etre unique, ce champ represente le code unique d'un pays ")
	@Column(nullable = false)
	String isoCode;
	
	@NotBlank(message="ce champ ne peut etre vide et doit etre unique, ce champ represente l indicatif téléphonique d'un pays ")
	@Column(nullable = false)
	String countryCode;
	
	String countryFlag;
}