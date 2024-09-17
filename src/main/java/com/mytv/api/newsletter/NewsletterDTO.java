package com.mytv.api.newsletter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsletterDTO {

	@NotBlank(message = "un objet est requis")
	String objet;
	
	@NotBlank(message = "un contenu est requi")
	String content;
	
}
