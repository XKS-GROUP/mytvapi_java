package com.mytv.api.model.gestMedia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Saison {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSaison;
	
	

}
