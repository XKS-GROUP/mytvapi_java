package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class View {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idVue;
	
	@CreationTimestamp
	Date ViewDate;
	
	public Long getIdVue() {
		return idVue;
	}

	public void setIdVue(Long idVue) {
		this.idVue = idVue;
	}

	public Date getViewDate() {
		return ViewDate;
	}

	public void setViewDate(Date viewDate) {
		ViewDate = viewDate;
	}


}
