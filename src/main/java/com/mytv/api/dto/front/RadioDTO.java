package com.mytv.api.dto.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.mytv.api.ressource.model.CategoryRL;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.model.Pays;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RadioDTO {

	Long idRadio;

	@NotBlank(message = "ce champ ne peut etre vide, une radio doit obligatoirement avoir un nom")
	String name ;
	
	List <Long> categories = new ArrayList<>();
	
	List<CategoryRL> list_categories = new ArrayList<>();

	@NotBlank(message = "ce champ ne peut etre vide, une description est requise pour une radio")
	String overview;
	
	@NotNull(message = "un logo est requis pour une radio")
	String logo_path;

	@NotNull(message = "une image miniature est requise pour une radio")
	String backdrop_path;

	@NotNull(message = "ce champ ne peut etre vide, une valeur par defaut doit etre attribuer")
	boolean status;
	
	Boolean accessFree;

	@NotBlank(message = "ce champ ne peut etre vide, une radio doit avoir une source")
	String streamLink;
	
	@NotNull(message = "ce champ ne peut etre vide, une radio doit etre représenter par un pays")
	List<Long>  country = new ArrayList<>();
	
	List<Pays> list_country = new ArrayList<>();
	
	@NotNull(message = "ce champ ne peut etre vide, une radio doit avoir au moins une langue")
	List<Long>  langue = new ArrayList<>();
	
	List<Language> list_langues;
	
	@CreatedDate
	Date addDate;
	
	boolean top10;
	
	boolean top;
	
	boolean favorie;
}
