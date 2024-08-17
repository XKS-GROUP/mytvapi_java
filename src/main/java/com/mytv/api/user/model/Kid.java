package com.mytv.api.user.model;


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


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idProfil;

	@NotBlank(message = "ce champ ne peut etre vide, un profil doit avoir au moin un nom ")
	@Column(nullable = false)
	String profilName;
	
	@Column(columnDefinition = "TEXT")
	String img_path;

	String lang;
}
