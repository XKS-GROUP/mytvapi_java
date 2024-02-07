package com.mytv.api.model;

import java.sql.Date;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class View {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idVue;
	Date ViewDate;
	Long idMedia;
	Long idUser;

}
