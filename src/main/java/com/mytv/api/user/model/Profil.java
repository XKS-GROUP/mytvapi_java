package com.mytv.api.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Profil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idProfil;

	@NotBlank(message = "ce champ ne peut etre vide, un profil doit avoir au moin un nom ")
	@Column(nullable = false)
	String profilName;

	@Column(columnDefinition = "TEXT")
	String img_path;

	String lang;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="id")
    private User utilisateur;
	
}
