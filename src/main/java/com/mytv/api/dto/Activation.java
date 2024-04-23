package com.mytv.api.dto;

import java.util.Map;

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
public class Activation {

	@NotBlank(message = "ce champ ne peut pas etre vide, veuillez inserer le code recu par mail ")
	private Map<String, String> code;
}
