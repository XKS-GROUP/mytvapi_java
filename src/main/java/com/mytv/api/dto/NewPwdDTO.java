package com.mytv.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class NewPwdDTO {
	
	@NotBlank(message = "ce champ ne peut pas etre vide, veuillez inserer l' adresse mail du compte à recuperer ")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", 
    message = "le mot de passe doit contenir au moins 8"
	+ " caractères et doit contenir au Moins une lette Majiscule, un chiffre et un caractère spécial")
	private String mdp;

}
