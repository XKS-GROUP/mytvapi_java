package com.mytv.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class FolderDTO {

	@NotEmpty(message = "un nom est requis pour un dossier")
	String name;
	
	@Column(columnDefinition = "TEXT")
	String parentDirectory;
	 
	@Column(columnDefinition = "TEXT")
	String folderpath;
}
