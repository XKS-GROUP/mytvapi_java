package com.mytv.api.model.gestMedia;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDirector;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String fistName;

	@NotBlank(message = "Ce champ ne puis etre vide")
	@Column(nullable = false)
	String lastName;

	Date dob;
	
	@Column(columnDefinition = "TEXT")
	String imageUrl;

	@NotBlank(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	String biography;

	@NotNull(message = "Ce champ ne peut etre vide")
	@Column(nullable = false)
	List <Long> pays = new ArrayList<>();

}
