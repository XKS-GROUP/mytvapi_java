package com.mytv.api.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestUser.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikePodcast {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idLike;

	@CreationTimestamp
	Date datePub;
	
	@UpdateTimestamp
	Date dateMaj;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;

	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "idPodcast", insertable = true, updatable = true)
	private Podcast podcast;
}
