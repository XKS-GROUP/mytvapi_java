package com.mytv.api.aws.model;


import java.util.ArrayList;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idFolder;
	 
	 @NotEmpty(message = "un nom de dossier est requis")
	 String name;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "parent_directory_id")
     private Folder parentDirectory;

     @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Folder> subDirectories = new ArrayList<>();

     @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<FileMeta> files = new ArrayList<>();
	 
	 @Column(columnDefinition = "TEXT")
	 String folderpath;
	 
	 @CreationTimestamp
	 Date addDate;

}
