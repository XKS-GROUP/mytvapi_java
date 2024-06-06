package com.mytv.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FileMetaDTO {
	
	@NotEmpty(message = "un nom est requis pour un dossier")
	String name;

}
