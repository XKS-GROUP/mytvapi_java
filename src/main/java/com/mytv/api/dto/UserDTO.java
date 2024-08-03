package com.mytv.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@Size(min = 5, message = "le nom d utilisateur doit contenir au moins 5 caractère" )
	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique")
	@Column(name = "username", unique = true)
	String username;

	@Email(message = "Ce champ doit etre une adresse mail valide")
	@NotBlank(message = "ce champ ne peut pas etre vide et doit etre unique")
	@Column(nullable = false, unique = true)
	String email;
	
	String phone;
	
	@Column(columnDefinition = "TEXT")
	String imageUrl;

	@Column(columnDefinition = "boolean default false")
	@NotNull
	boolean valide;
}
