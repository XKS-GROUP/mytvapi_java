package com.mytv.api.livetv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.mytv.api.ressource.model.CategoryRL;
import com.mytv.api.ressource.model.Language;
import com.mytv.api.ressource.model.Pays;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class LiveTv implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 850357098919235230L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLiveTv")
	Long idLiveTv;

	@NotBlank(message="ce champ ne peut pas etre vide")
	@Column(nullable = false)
	String name ;

	@NotBlank(message="ce champ ne peut pas etre vide")
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;

	@Column(columnDefinition = "TEXT")
	String tvLogo_path;

	@NotNull(message="ce champ ne peut pas etre vide, un liveTv doit avoir une categorie")
	@Column(nullable = false)
	List <Long>  idcategories= new ArrayList<>();

	@NotNull(message="ce champ ne peut pas etre vide, un liveTv doit avoir un status qui permet de le rendre visible ou pas")
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;

	@Column(columnDefinition = "boolean default false")
	Boolean accessFree;

	@Column(columnDefinition = "TEXT")
	String svr1_url;

	@Column(columnDefinition = "TEXT")
	String svr2_url;

	@Column(columnDefinition = "TEXT")
	String svr3_url;

	@Column(columnDefinition = "TEXT")
	String tvEmbedCode;

	@NotNull(message="Ce champ ne peut pas etre vide")
	@Column(nullable = false)
	List<Long>  country = new ArrayList<>();
	
	@NotNull(message = "ce champ ne peut etre vide, au moins une langue est requise")
	@Column(nullable = false)
	List<Long>  langue = new ArrayList<>();
	
	@CreatedDate
	Date addDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idLang",  cascade = CascadeType.ALL)
	List<Language> langues = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idPays",  cascade = CascadeType.ALL)
	List<Pays> pays = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idcat",  cascade = CascadeType.ALL)
	List<CategoryRL> listCateg = new ArrayList<>();
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top;
	
	@Transient
	boolean favorie;
	
}