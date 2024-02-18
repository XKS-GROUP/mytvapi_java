package com.mytv.api.model.gestMedia;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class LiveTv {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLiveTv")
	Long idLiveTv;
	
	@Column(nullable = false)
	String name ;
	
	String description;

	String tvLogo;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idcat", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	CategoryRL category;
	
	boolean status;

	
	
	String streamType;
	
	String svr1;
	String svr2;
	String svr3;
	
	String tvEmbedCode;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPays", nullable = false)
	Pays country;

	public Long getIdLiveTv() {
		return idLiveTv;
	}

	public void setIdLiveTv(Long idLiveTv) {
		this.idLiveTv = idLiveTv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTvLogo() {
		return tvLogo;
	}

	public void setTvLogo(String tvLogo) {
		this.tvLogo = tvLogo;
	}

	public CategoryRL getCategory() {
		return category;
	}

	public void setCategory(CategoryRL category) {
		this.category = category;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	public String getSvr1() {
		return svr1;
	}

	public void setSvr1(String svr1) {
		this.svr1 = svr1;
	}

	public String getSvr2() {
		return svr2;
	}

	public void setSvr2(String svr2) {
		this.svr2 = svr2;
	}

	public String getSvr3() {
		return svr3;
	}

	public void setSvr3(String svr3) {
		this.svr3 = svr3;
	}

	public String getTvEmbedCode() {
		return tvEmbedCode;
	}

	public void setTvEmbedCode(String tvEmbedCode) {
		this.tvEmbedCode = tvEmbedCode;
	}
	
	
	
	
	


}