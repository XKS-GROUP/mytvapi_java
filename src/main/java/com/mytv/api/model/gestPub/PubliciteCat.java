package com.mytv.api.model.gestPub;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PubliciteCat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCatPub;
	
	@NotBlank(message = "ce champ ne peut etre vide ")	
	@Column(nullable = false)
	String name;

}
