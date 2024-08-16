package com.mytv.api.model.gestMedia;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@NotEmpty(message = "une description est requise")
	private String overview;
	
	@Column(columnDefinition = "TEXT")
	private String posterUrl;
	
	private String liveUrl;
	
	@Column(columnDefinition = "TEXT")
	private String trailler;
	
	@Column(columnDefinition = "boolean default false")
	Boolean accessFree;
	
	private List<Long> idCats;
	
	private String lieu;
	
	@NotNull(message = "une date de debut est requise")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateDebut;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateFin;
	
	@NotNull(message = "une heure de debut est requise")
	private LocalTime heureDebut;
	
	private LocalTime heureFin;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	boolean status;
	
	@CreatedDate
	Date addDate;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	boolean top10;

}
