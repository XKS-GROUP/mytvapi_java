package com.mytv.api.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.setting.model.AdmodSetting;
import com.mytv.api.setting.model.FirebaseSetting;
import com.mytv.api.setting.model.R2cloudSetting;
import com.mytv.api.setting.model.Setting;
import com.mytv.api.setting.model.SmtpSetting;
import com.mytv.api.setting.model.SocialSetting;
import com.mytv.api.setting.model.TmdbSetting;
import com.mytv.api.setting.service.SettingService;
import com.mytv.api.setting.service.SmtpService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class SettingControlleur {

	@Autowired
	SmtpService smtpService;
	
	@Autowired
	SettingService settingService;
	
	/*
	 * 
	 * Paramettre
	 * 
	 * 
	 */
	
	//AJOUT DES PARAMETTRE
	@Tag(name = "Setting")
	@PostMapping("setting/general/add")
	public ResponseEntity<Object> Add_general( @Valid @RequestBody Setting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_setting(s));
	}
	
	@Tag(name = "Setting")
	@PostMapping("setting/firebase/add")
	public ResponseEntity<Object> Add_firebase( @Valid @RequestBody FirebaseSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_firebase(s));
	}
	
	@Tag(name = "Setting")
	@PostMapping("setting/admob/add")
	public ResponseEntity<Object> Add_admob( @Valid @RequestBody AdmodSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_admod(s));
	}
	
	@Tag(name = "Setting")
	@PostMapping("setting/tmdb/add")
	public ResponseEntity<Object> Add_tmdb( @Valid @RequestBody TmdbSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_TMDB(s));
	}
	
	@Tag(name = "Setting")
	@PostMapping("setting/r2cloudflare/add")
	public ResponseEntity<Object> Add_r2cloudflare( @Valid @RequestBody R2cloudSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_R2Setting(s));
	}

	@Tag(name = "Setting")
	@PostMapping("setting/social/add")
	public ResponseEntity<Object> Add_social( @Valid @RequestBody SocialSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_Social(s));
	}
	
	//Update
	
	@Tag(name = "Setting")
	@PutMapping("setting/general/update/{id}")
	public ResponseEntity<Object> update_general(@PathVariable Long id, @Valid @RequestBody Setting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_setting(s));
	}
	
	@Tag(name = "Setting")
	@PutMapping("setting/firebase/update/{id}")
	public ResponseEntity<Object> update_firebase(@PathVariable Long id, @Valid @RequestBody FirebaseSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_firebase(s));
	}
	
	@Tag(name = "Setting")
	@PutMapping("setting/admob/update/{id}")
	public ResponseEntity<Object> update_admob(@PathVariable Long id, @Valid @RequestBody AdmodSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_admod(s));
	}
	
	@Tag(name = "Setting")
	@PutMapping("setting/tmdb/update/{id}")
	public ResponseEntity<Object> update_tmdb(@PathVariable Long id, @Valid @RequestBody TmdbSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_TMDB(s));
	}
	
	@Tag(name = "Setting")
	@PutMapping("setting/r2cloudflare/update/{id}")
	public ResponseEntity<Object> update_r2cloudflare(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_R2Setting(id));
	}

	@Tag(name = "Setting")
	@PutMapping("setting/social/update/{id}")
	public ResponseEntity<Object> update_social(@PathVariable Long id, @Valid @RequestBody SocialSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.add_Social(s));
	}
	
	//Show by id 
	
	@Tag(name = "Setting")
	@GetMapping("setting/general/{id}")
	public ResponseEntity<Object> show_general(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_setting(id));
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/firebase/{id}")
	public ResponseEntity<Object> show_firebase(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_firebase(id));
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/admob/{id}")
	public ResponseEntity<Object> show_admob(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_admod(id));
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/tmdb/{id}")
	public ResponseEntity<Object> show_tmdb(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_TMDB(id));
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/r2cloudflare/{id}")
	public ResponseEntity<Object> show_r2cloudflare(@PathVariable Long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_R2Setting(id));
	}

	@Tag(name = "Setting")
	@GetMapping("setting/social/{id}")
	public ResponseEntity<Object> show_social(@PathVariable Long id, @Valid @RequestBody SocialSetting s){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.show_byId_Social(id));
	}
	
	//Affichage
	
	@Tag(name = "Setting")
	@GetMapping("setting/general/show")
	public ResponseEntity<Object> show_general(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_setting());
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/firebase/show")
	public ResponseEntity<Object> show_firebase(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_firebase());
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/admob/show")
	public ResponseEntity<Object> show_admob(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_admod());
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/tmdb/show")
	public ResponseEntity<Object> show_tmdb(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_TMDB());
	}
	
	@Tag(name = "Setting")
	@GetMapping("setting/r2cloudflare/show")
	public ResponseEntity<Object> show_r2cloudflare(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_R2Setting());
	}

	@Tag(name = "Setting")
	@GetMapping("setting/social/show")
	public ResponseEntity<Object> show_social(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, settingService.list_Social());
	}

	
	
	
	/*
	 * 
	 * Paramettre de SMTP
	 * 
	 * 
	 */
	
	@Tag(name = "Setting smtp email")
	@GetMapping("smtp")
	public ResponseEntity<Object> showPub(){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, smtpService.show());
	}

    
	@Tag(name = "Setting smtp email")
	@GetMapping("smtp/{id}")
	public ResponseEntity<Object> showPubById(@PathVariable long id){

		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, smtpService.showById(id));
	}
    
	
	@Tag(name = "Setting smtp email")
	@PostMapping("smtp/create")
	public ResponseEntity<Object> createPub(
			@Valid @RequestBody SmtpSetting p){

			return EntityResponse.generateResponse("SUCCES ", HttpStatus.CREATED, smtpService.create(p));
			
	}
    
	
	@Tag(name = "Setting smtp email")
	@PutMapping("smtp/update/{id}")
	public ResponseEntity<Object> updatePub(@PathVariable Long id, @Valid @RequestBody SmtpSetting p){

		
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, smtpService.upadte(id, p));
		
	}
    
	@Tag(name = "Setting smtp email")
	@DeleteMapping("smtp/delete/{id}")
	public ResponseEntity<Object> deletePub(@PathVariable Long id){
    	smtpService.delete(id);
		return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, "");
		
	}
}
