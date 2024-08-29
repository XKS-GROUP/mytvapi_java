package com.mytv.api.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.film.service.ServiceFilm;
import com.mytv.api.livetv.service.LiveTvSetvice;
import com.mytv.api.podcast.service.PodcastService;
import com.mytv.api.radio.service.RadioService;
import com.mytv.api.security.request.EntityResponse;
import com.mytv.api.serie.service.SerieService;
import com.mytv.api.setting.service.SettingService;
import com.mytv.api.user.service.WUserService;
import com.mytv.api.util.service.PubliciteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/front/")
@SecurityRequirement(name = "bearerAuth")
public class HomePage {
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	private RadioService radioService;
	
	@Autowired
	private LiveTvSetvice liveService;
	
	@Autowired
	private PodcastService podcastservice;
	
	@Autowired
	private ServiceFilm filmService;
	
	@Autowired
	private SerieService serieService;

	@Autowired
	PubliciteService pubService;
	
	@Autowired
	WUserService userService;
	
	
	  /*
     * 
     * 
     * 
     * SECTION HOME PAGE TOP 10
     * 
     * 
     *  
     */
    
    @Tag(name = "HomePage")
	@GetMapping("podcast/top10")
	public ResponseEntity<Object> podcastTop10(){

    	
    	
	  return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,podcastservice.top10());
	}
    
    @Tag(name = "HomePage")
	@GetMapping("film/top10")
	public ResponseEntity<Object> filmTop10(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.top10());
	}
    
    @Tag(name = "HomePage")
	@GetMapping("serie/top10")
	public ResponseEntity<Object> serieTop10(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.top10() );
	}
    
    @Tag(name = "HomePage")
	@GetMapping("radio/top10")
	public ResponseEntity<Object> radioTop10(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.top10());
		
	}
    
    @Tag(name = "HomePage")
	@GetMapping("tv/top10")
	public ResponseEntity<Object> livetvTop10(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.top10());
	}
    
    
    /*
     * 
     * 
     * 
     * 
     * SECTION HOME PAGE EN VEDETTE
     * 
     * 
     * 
     *  
     */
    
    @Tag(name = "HomePage")
	@GetMapping("podcast/top")
	public ResponseEntity<Object> podcastTop(){

	  return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK,podcastservice.top());
	}
    
    @Tag(name = "HomePage")
	@GetMapping("film/top")
	public ResponseEntity<Object> filmTop(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, filmService.top());
	}
    
    @Tag(name = "HomePage")
	@GetMapping("serie/top")
	public ResponseEntity<Object> serieTop(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, serieService.top() );
	}
    
    @Tag(name = "HomePage")
	@GetMapping("radio/top")
	public ResponseEntity<Object> radioTop(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, radioService.top());
		
	}
    
    @Tag(name = "HomePage")
	@GetMapping("tv/top")
	public ResponseEntity<Object> livetvTop(){

    	return EntityResponse.generateResponse("SUCCES ", HttpStatus.OK, liveService.top());
		
	}
}
