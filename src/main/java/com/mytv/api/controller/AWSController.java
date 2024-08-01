package com.mytv.api.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mytv.api.aws.AmazonS3ServiceImpl;
import com.mytv.api.aws.FileMeta;
import com.mytv.api.aws.FileMetaRepository;
import com.mytv.api.aws.Folder;
import com.mytv.api.aws.FolderService;
import com.mytv.api.aws.MetadataService;
import com.mytv.api.aws.MetadataServiceImpl;
import com.mytv.api.dto.FileMetaDTO;
import com.mytv.api.dto.FolderDTO;
import com.mytv.api.security.EntityResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")

@SecurityRequirement(name = "bearerAuth")
public class AWSController {
	
    @Autowired
    private MetadataService metadataService;

    @Autowired
    private AmazonS3ServiceImpl awsService;

	@Autowired
    private FileMetaRepository fileMetaRep;
	
	@Autowired
	private FolderService folderService;

	
    /*
     * 
     * 
     *CLOUDFLARE R2 CRUD 
     * 
     * 
     */
    
    

    /* AWS R2 FILE CRUD  */
    
    @Autowired
    AmazonS3ServiceImpl awsImplService;
    MetadataServiceImpl metaImplService;

    
    //Lister tous les fichiers
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/findall")
    public ResponseEntity<Object> all() {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.list());
    }
	
	
	//Lister tous les fichier avec pagination
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/file/all/")
    public ResponseEntity<Object> all(Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.listWithPage(p));
    }
	
	//Renommer un fichier
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/file/rename/{id}")
    public ResponseEntity<Object> renameFile(
    			@PathVariable Long id,
    			@Valid @RequestBody FileMetaDTO f) {

		FileMeta fm = metadataService.showById(id).get();
		fm.setFileName(f.getName());
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.create(fm));
    }
	
	

	//Telecharger un fichier
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/file/download/{idFile}")
    public ResponseEntity<Object> download( @PathVariable int idFile) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.download(idFile));
    }
	
	//Rechercher un fichier par son id
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/search/byId/")
    public ResponseEntity<Object> findbyid(
    		@RequestParam int id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findById(id));
    }
	
	//Afficher un fichier par son id
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/file/{idfile}")
    public ResponseEntity<Object> findFileByid(
    		@RequestParam int id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findById(id));
    }

	//rechercher un fichier par son nom
	@Tag(name = "R2-CLOUDFLARE")
    @GetMapping("r2/file/search/{name}")
    public ResponseEntity<Object> findbyname(@PathVariable String name, Pageable p) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, fileMetaRep.findByFileNameContaining(name, p));
    }

	//Uploader un fichier sans dossier
	@Tag(name = "R2-CLOUDFLARE")
    @PostMapping(path="r2/uploadfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metadataService.uploadR3(file, ""));

    }
	
	//Uploader un fichier en precisant l'id du dossier
	@Tag(name = "R2-CLOUDFLARE")
    @PostMapping(path="r2/file/upload/folder/{folderid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>  uploadWithFolderName(
    		@RequestParam("file") MultipartFile file, 
    		@PathVariable Long folderid) throws IOException {


		if(folderid==null) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.BAD_REQUEST, " le dossier ne puis être vide");

		}
		else if (file.isEmpty()) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.BAD_REQUEST, " un fichier est requis");

		}
		else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, metadataService.createFile(file, folderid));
		 }
    }

	//Uploader un fichier en precisant le nom du dossier
	@Tag(name = "R2-CLOUDFLARE")
    @PostMapping(path="r2/file/upload/folder/{folderName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>  uploadWithFolderName(@RequestParam("file") MultipartFile file, @PathVariable String folderName) throws IOException {


		if(folderName==null) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.BAD_REQUEST, " le dossier ne puis être vide");

		}
		else if (file.isEmpty()) {

			return EntityResponse.generateResponse("Erreur", HttpStatus.BAD_REQUEST, " un fichier est requis");

		}
		else {

			return EntityResponse.generateResponse("SUCCES", HttpStatus.CREATED, metadataService.uploadR3(file, folderName));
		 }
    }
	

	//Supprimer un fichier par son Id
	@Tag(name = "R2-CLOUDFLARE")
    @DeleteMapping("r2/file/delete/{id}")
    public ResponseEntity<Object>  deleteByName(@PathVariable  int id){


		if (fileMetaRep.findById(id)==null) {

			return EntityResponse.generateResponse("Erreur suppresion", HttpStatus.BAD_REQUEST, "Vous tentez de supprimez un objet qui n'existe pas ");

		}
		else {


			Optional<FileMeta> fm = fileMetaRep.findById(id);


			awsService.deleteR2( fm.get().getFilePath() , fm.get().getFileName());

			fileMetaRep.deleteById(id);

			return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, "fichier Supprimé");
		}

	}
	
	
	/*
	 * 
	 *  FOLDER
	 * 
	 * 
	 * 
	 */
	
	//Creer un dossier
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @PostMapping("r2/folder/create")
    public ResponseEntity<Object> createFolder(@Valid @RequestBody Folder folder) {

		if(folderService.showbyname(folder.getName()).isPresent() ) {
			
			return EntityResponse.generateResponse("ATTENTION ", HttpStatus.BAD_REQUEST, "Ce nom existe déja");
		}else {
			
			metaImplService.createFolder(folder.getName());
			
			return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,  folderService.create(folder));
		}
		
    }
	
	
	//MAJ FOLDER
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @PostMapping("r2/folder/update/{id}")
    public ResponseEntity<Object> updateFolder(
    		@Valid @RequestBody Folder folder,
    		@PathVariable long id) {

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,  folderService.update(id ,folder));
    }
	
	
	//lister tous les dossiers de AWS
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @GetMapping("r2/folder/all/cloud-r2")
    public ResponseEntity<Object> listFolder(
    		@RequestParam(required = false) String dossier,
    		Pageable p) {
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, metaImplService.listFolder(dossier, p) );
    }
	
	//Afficher dossier par id
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @GetMapping("r2/folder/{id}")
    public ResponseEntity<Object> listFolder(
    		@PathVariable Long id) {
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, folderService.showbyId(id));
    }
	
	
	//lister des dossier contenu dans la DB
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @GetMapping("r2/folder/all/")
    public ResponseEntity<Object> listFolders(Pageable p) {
		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, folderService.show(p));
    }
	
	
	
	//Supprimer un dossier
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @DeleteMapping("r2/folder/delete/{id}")
    public ResponseEntity<Object> deleteFolder( @PathVariable long id ) {
		
		
		
		metaImplService.deteteFolder(folderService.showbyId(id).getName());
		
		folderService.delete(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,"dossier à été supprimé avec succès");
    }
	
	
	//Supprimer un dossier
	@Tag(name = "R2-CLOUDFLARE-FOLDER")
    @DeleteMapping("r2/folder/delete/name/{name}")
    public ResponseEntity<Object> deleteFolder(
    		@PathVariable String dossier ) {
		
		metaImplService.deteteFolder(dossier);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, dossier+" a été supprimé avec succès");
    }
	

}
