package com.mytv.api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PwdResetPwdDTO {
	
	@Email(message = "Ce champ doit etre une adresse mail valide")
	@NotBlank(message = "ce champ ne peut pas etre vide, veuillez inserer l' adresse mail du compte à recuperer ")
	private String email;
	
	
}
