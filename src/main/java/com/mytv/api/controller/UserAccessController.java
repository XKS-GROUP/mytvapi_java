package com.mytv.api.controller;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.util.Map;
import java.util.Random;

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

import com.mytv.api.model.gestUser.Jwt;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.model.gestUser.Validation;
import com.mytv.api.repository.JwtRepository;
import com.mytv.api.repository.ValidationRepository;
import com.mytv.api.security.AuthenticationRequest;
import com.mytv.api.security.AuthenticationResponse;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.security.JWTTokenUtil;
import com.mytv.api.security.UserRegisterRequestDTO;
import com.mytv.api.service.gestUser.NotificationService;
import com.mytv.api.service.gestUser.WUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
	
	@Autowired
    private ValidationRepository validationRepository;
	
	@Autowired
    private NotificationService notificationService;
	
	@Autowired
	private JwtRepository jwtRep;

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
		
		Jwt jwtToken = new Jwt();
		
		jwtToken.setValue(token);
		jwtToken.setExpire(jwtTokenUtil.validateToken(token, userDetails));
		
		//jwtToken.setUser(userService.findCurrentUser());
	
		
		//jwtRep.save(jwtToken);

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

	@PostMapping("admin-register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequestDTO request){
		
		if(userService.findByUsername(request.getUsername()) != null){
			
			return EntityResponse.generateResponse("Ce nom existe deja existe deja "+request.getUsername(), HttpStatus.CONFLICT, "");
		}
		else if (userService.findByUserEmail(request.getEmail()) !=null) {
			
			return EntityResponse.generateResponse("Cette adresse email existe deja "+request.getEmail(), HttpStatus.CONFLICT, "");
		}
			
		else {
			
			request.setPassword(passwordEncoder.encode(request.getPassword()));
			return EntityResponse.generateResponse("Utilisateur enregistre en tant que "+request.getRoleList(), HttpStatus.OK, userService.createUser(request));
		}
		
		
	}
	
	
	@PostMapping("abonne-register")
	public ResponseEntity<Object> registerA(@Valid @RequestBody UserRegisterRequestDTO request){
		
		if(userService.findByUsername(request.getUsername()) != null){
			
			return EntityResponse.generateResponse("Ce nom existe deja existe deja "+request.getUsername(), HttpStatus.CONFLICT, "");
		}
		else if (userService.findByUserEmail(request.getEmail()) !=null) {
			
			return EntityResponse.generateResponse("Cette adresse email existe deja "+request.getEmail(), HttpStatus.CONFLICT, "");
		}
			
		else {
			
			request.setPassword(passwordEncoder.encode(request.getPassword()));
			return EntityResponse.generateResponse("Utilisateur enregistre en tant que "+request.getRoleList(), HttpStatus.OK, userService.createAbonne(request));
		}
		
		
	}
	
	@PostMapping("activation")
    public void activation(@RequestBody Map<String, String> activation) {
    	
        userService.activation(activation);
    }
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("newcode")
    public ResponseEntity<Object> newcode() {
    		
		    User user = userService.findCurrentUser();
		    
		    if(validationRepository.findByUtilisateurId(user.getId()) != null) {
		    	
		    	validationRepository.deleteById(validationRepository.findByUtilisateurId(user.getId()).getId());
		    	
		    	//
		    	return EntityResponse.generateResponse("Aucun utilisateur connecté, veuillez vérifier que vous ête bien authentiier", HttpStatus.BAD_REQUEST, validationRepository.findByUtilisateurId(user.getId()));
		    }
		    	    
		    
		    Validation validation = new Validation();
	        validation.setUtilisateur(user);
	        
	        Instant creation = Instant.now();
	        validation.setCreation(creation);
	        Instant expiration = creation.plus(10, MINUTES);
	        validation.setExpiration(expiration);
	        Random random = new Random();
	        int randomInteger = random.nextInt(999999);
	        String code = String.format("%06d", randomInteger);

	        validation.setCode(code);
	        validationRepository.save(validation);
	        notificationService.envoyer(validation);
		 
	        return EntityResponse.generateResponse("User Profile", HttpStatus.OK, "Nouveau Code Renvoyer a l'adresse "+user.getEmail());   
	        
    }
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("currentUser")
	public User currentUser() {
		
		return userService.findCurrentUser();
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("profile")
	public ResponseEntity<Object> retrieveUserProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}
	
		
}
