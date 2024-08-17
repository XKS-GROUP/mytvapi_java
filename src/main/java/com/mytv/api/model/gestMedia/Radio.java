package com.mytv.api.model.gestMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.mytv.api.model.ressource.Language;
import com.mytv.api.model.ressource.Pays;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Radio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRadio")
	Long idRadio;

	@NotBlank(message = "ce champ ne peut etre vide, une radio doit obligatoirement avoir un nom")
	@Column(nullable = false)
	String name ;
	
	@Column(nullable = false)
	List <Long> categories = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idcat",  cascade = CascadeType.ALL)
	List<CategoryRL> list_categories = new ArrayList<>();

	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour une radio")
	@Column(columnDefinition = "TEXT", nullable = false)
	String overview;
	
	@NotNull(message = "un logo est requis pour une radio")
	@Column(columnDefinition = "TEXT")
	String logo_path;

	@NotNull(message = "une image miniature est requise pour une radio")
	@Column(columnDefinition = "TEXT")
	String backdrop_path;

	@NotNull(message = "ce champ ne peut etre vide, une valeur par defaut doit etre attribuer")
	@Column(columnDefinition = "boolean default true")
	boolean status;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean accessFree;

	@NotBlank(message = "ce champ ne peut etre vide, une radio doit avoir une source")
	@Column(nullable = false, columnDefinition = "TEXT")
	String streamLink;
	
	@NotNull(message = "ce champ ne peut etre vide, une radio doit etre représenter par un pays")
	@Column(nullable = false)
	List<Long>  country = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idPays",  cascade = CascadeType.ALL)
	List<Pays> list_country = new ArrayList<>();
	
	@NotNull(message = "ce champ ne peut etre vide, une radio doit avoir au moins une langue")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> list_langues;
	
	@CreatedDate
	Date addDate;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top;
	
}