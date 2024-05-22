package com.mytv.api.model.gestMedia;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Live {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLive;
	
	@NotEmpty(message = "un nom est requis")
	@Column(nullable = false)
	private String name;
	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "un nom est requis")
	private String overview;
	
	@Column(columnDefinition = "TEXT")
	private String posterUrl;
	
	private String liveUrl;
	
	@Column(columnDefinition = "boolean default false")
	Boolean accessFree;
	
	private List<Long> idCats;
	
	private String lieu;
	
	@NotNull(message = "une date de debut est requise")
	private Date dateDebut;
	
	private Date dateFin;
	
	@NotNull(message = "une heurs de debut est requise")
	private LocalTime heureDebut;
	
	private LocalTime heureFin;

}
