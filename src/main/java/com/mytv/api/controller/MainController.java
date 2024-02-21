package com.mytv.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class MainController {
	
	
	@GetMapping
	public String acc() {
		
		return " API V 1.0 MY-TELEVISION";
	}
	
	@GetMapping("error")
	public String error() {
		
		return "Cette route n'existe pas !!!";
	}

}
