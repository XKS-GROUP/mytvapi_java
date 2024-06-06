package com.mytv.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatusDTO {

	
	@NotNull(message = "Le champ ne peut pas être nul")
	Boolean status;
}
