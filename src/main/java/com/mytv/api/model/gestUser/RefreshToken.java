package com.mytv.api.model.gestUser;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "refresh-token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean expire;
    private String value;
    private Instant creation;
    private Instant expiration;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isExpire() {
		return expire;
	}
	public void setExpire(boolean expire) {
		this.expire = expire;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Instant getCreation() {
		return creation;
	}
	public void setCreation(Instant creation) {
		this.creation = creation;
	}
	public Instant getExpiration() {
		return expiration;
	}
	public void setExpiration(Instant expiration) {
		this.expiration = expiration;
	}



}
