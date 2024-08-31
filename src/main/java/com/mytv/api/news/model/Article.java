package com.mytv.api.news.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	
	@NotBlank(message = "Un titre est requise")
	@Column(nullable = false, columnDefinition = "TEXT")
	String title;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String overview;
	
	@NotBlank(message = "Un cover est requise")
	@Column(nullable = false, columnDefinition = "TEXT")
	String cover;
	
	@NotBlank(message = "Un contenu est requise")
	@Column(nullable = false, columnDefinition = "TEXT")
	String content;
	
	@CreationTimestamp
    @Column(updatable = false)
	LocalDateTime  publishDate;
	
	@UpdateTimestamp
	LocalDateTime  updateDate;
	
	Long idUser;
	
	List<Long> categories;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id",  cascade = CascadeType.ALL)
	List<CategArticle> list_categories;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String media;
	
	@Transient
	boolean favorie;
	
}
