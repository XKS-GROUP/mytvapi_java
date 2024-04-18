package com.mytv.api.aws;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "FILE_META")
@AllArgsConstructor
@NoArgsConstructor
public class FileMeta {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "VERSION")
    private String version;
    
    @Column(columnDefinition = "TEXT" )
    private String urlPresign;
    
    String format;
    
    @CreationTimestamp
    Date addDate;
    
    private Long Size;

	public FileMeta(String fileName, String filePath, String version ) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.version = version;
    }

	
	public FileMeta(String fileName, String filePath, String version, String urlPresign ) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.version = version;
        this.urlPresign = urlPresign;
    }

}
