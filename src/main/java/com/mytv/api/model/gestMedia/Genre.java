package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idMedia;
	
	@Column(nullable = false, length = 80)
	String name;
	
	String imageUrl;
}
