package com.mytv.api.model.gestMedia;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CategoryRL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcat")
	Long idcat;

	@NotBlank(message = "ce champ ne peut pas etre vide")
	private String name;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean status;

	@Column(columnDefinition = "TEXT")
	private String img_path;

	
}
