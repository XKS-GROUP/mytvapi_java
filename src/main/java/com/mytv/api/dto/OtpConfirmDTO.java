package com.mytv.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpConfirmDTO {

	@Email(message = "Ce champ doit etre une adresse mail valide")
	@NotBlank(message = "ce champ ne peut pas etre vide")
	@Column(nullable = false, unique = true)
	String Email;
	
	@NotBlank(message = "ce champ ne peut pas etre vide")
	@Column(nullable = false, unique = true)
	String otp;
}
