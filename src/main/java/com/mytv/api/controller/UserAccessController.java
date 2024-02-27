package com.mytv.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.security.AuthenticationRequest;
import com.mytv.api.security.AuthenticationResponse;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.security.JWTTokenUtil;
import com.mytv.api.security.UserRegisterRequestDTO;
import com.mytv.api.service.gestUser.WUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class UserAccessController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	WUserService userService;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (Exception e) {
			return EntityResponse.generateResponse("Authentication", HttpStatus.UNAUTHORIZED,
					"Info non valide, le nom d utilsateur ou le mot de passe est incorrecte");
		}
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

		return EntityResponse.generateResponse("Authentication", HttpStatus.OK,
				new AuthenticationResponse(token, refreshToken));

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			
			throw new Exception("USER_DISABLED = UTILISATEUR DESACTIVE ou NON ACTIVE", e);
			
		} catch (BadCredentialsException e) {
			
			throw new Exception("Le nom d utilsateur ou le mt de passe est incorrecte", e);
			
		}catch(Exception e) {
			
			throw new Exception("Le nom d utilsateur ou le mt de passe est incorrecte", e.getCause());
			
		}
	}
	
	@PostMapping("register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequestDTO request){
		
		
		
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		
		return EntityResponse.generateResponse("Utilisateur enregistre en tant que "+request.getRoleList(), HttpStatus.OK, userService.createUser(request));
	}
	
	
	@GetMapping("profile")
	public ResponseEntity<Object> retrieveUserProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
	
	
}
