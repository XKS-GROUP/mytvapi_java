package com.mytv.api.aws;


import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idFolder;
	 
	 @NotEmpty(message = "un nom de dossier est requis")
	 String name;
	 
	 @Column(columnDefinition = "TEXT")
	 String parentDirectory;
	 
	 @Column(columnDefinition = "TEXT")
	 String folderpath;
	 
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "folder",  cascade = CascadeType.ALL)
	 List<FileMeta> files;
	 
	 @CreationTimestamp
	 Date addDate;

}
