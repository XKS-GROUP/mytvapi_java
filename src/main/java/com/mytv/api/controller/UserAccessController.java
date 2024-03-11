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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.gestUser.Jwt;
import com.mytv.api.model.gestUser.User;
import com.mytv.api.model.gestUser.Validation;
import com.mytv.api.repository.JwtRepository;
import com.mytv.api.repository.UserRepository;
import com.mytv.api.repository.ValidationRepository;
import com.mytv.api.security.AuthenticationRequest;
import com.mytv.api.security.AuthenticationResponse;
import com.mytv.api.security.EntityResponse;
import com.mytv.api.security.JWTTokenUtil;
import com.mytv.api.security.UserRegisterRequestDTO;
import com.mytv.api.service.gestUser.NotificationService;
import com.mytv.api.service.gestUser.ValidationService;
import com.mytv.api.service.gestUser.WUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class UserAccessController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	WUserService userService;
	
	@Autowired
	ValidationService validationService;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
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
		
		User usr = userService.findByUsername(authenticationRequest.getUsername());
		
		if(usr.isValide()) {
			
				usr.setRemember_token(token);	
				
				Jwt jwtToken = new Jwt();
				
				jwtToken.setValue(token);
				jwtToken.setValide(jwtTokenUtil.validateToken(token, userDetails));
				
				jwtToken.setRefresh_token(refreshToken);
				
				jwtToken.setUser(userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token)));
				
				jwtRep.save(jwtToken);
				
				return EntityResponse.generateResponse("Authentication", HttpStatus.OK,
				
						new AuthenticationResponse(token, refreshToken, usr));
				}else {
					
					return EntityResponse.generateResponse("Authentication", HttpStatus.BAD_REQUEST, "Ce compte n'est pas active, veuillez activez ce compte ");
				}

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			
		} catch (DisabledException e) {
			
			throw new Exception("USER_DISABLED = UTILISATEUR DESACTIVE ou NON ACTIVE", e);
			
		} catch (BadCredentialsException e) {
			
			throw new Exception("Le nom d utilsateur ou le mot de passe est incorrecte", e);
			
		}catch(Exception e) {
			
			throw new Exception("Le nom d utilsateur ou le mot de passe est incorrecte", e.getCause());
			
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
	
	@PostMapping("activation/{email}")
    public ResponseEntity<Object> activation(@RequestBody Map<String, String> activation, @PathVariable String email) {
		
		//String email = userService.
		User user = userService.findByUserEmail(email);
		
	    if(email.isEmpty()) {
	    	
	    	return EntityResponse.generateResponse("Champ email vide", HttpStatus.BAD_REQUEST, "le champ email ne puis être vide");
	    }
	    
	    
	    else if(userService.findByUserEmail(email) ==null ) {
	    	
	    	return EntityResponse.generateResponse("Utilisateur Introuvable", HttpStatus.BAD_REQUEST, "Aucun utilisateur enregistré sous "+email.toString());
	    	
	    }
    	
	    else if (user.isValide()) {
	    	
	    	return EntityResponse.generateResponse("Utilisateur Valide", HttpStatus.BAD_REQUEST, "Ce compte a déja été validé");
	    	
    	}
    	else {
	        userService.activation(activation);
	        return EntityResponse.generateResponse("Activation", HttpStatus.OK, "Utilisateur activé avec succès");
    	}
	    
    }
	
	@GetMapping("newcode/{email}")
	@Transactional
    public ResponseEntity<Object> newcode(@PathVariable String email) {
    		
		    
		    if(email.isEmpty()) {
		    	
		    	return EntityResponse.generateResponse("Champ email vide", HttpStatus.BAD_REQUEST, "le champ email ne puis être vide");
		    }
		    
		    
		    else if(userService.findByUserEmail(email) ==null ) {
		    	
		    	return EntityResponse.generateResponse("Utilisateur Introuvable", HttpStatus.BAD_REQUEST, "Aucun utilisateur enregistré sous "+email.toString());
		    	
		    }
		    
		    else {
		    	
		    	
		    	User user = userService.findByUserEmail(email);
		    	
		    	if (user.isValide()) {
			    	
			    	return EntityResponse.generateResponse("Utilisateur Valide", HttpStatus.BAD_REQUEST, "Ce compte a déja été validé");
			    	
		    	}
		    	else {
		    		
		    		
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
			        
			        
			        if(validationRepository.findByUtilisateurId(user.getId()) != null ){
			        	
			        	System.out.println(" Je sui ici");
			        	System.out.println(user.getId());
			        	System.out.println("Code "+validationRepository.findByUtilisateurId(user.getId()).getCode());
			        	
			        	//String cd = validationRepository.findByUtilisateurId(user.getId()).getCode();
			        	System.out.println("Supresionnnnn");
			        	
			        	validationRepository.delete(validationRepository.findByUtilisateurId(user.getId()));
			        	 
			        	//return EntityResponse.generateResponse("User Profile", HttpStatus.OK, "Nouveau Code Renvoyer a l'adresse "+user.get
			        }
			        //validationRepository.deleteById(validationRepository.findByUtilisateurId(user.getId()).getId());
			        System.out.println(" Je sui passer");
			        validationRepository.save(validation);
			        //validationRepository.delete(validationRepository.findByUtilisateurId(user.getId()));
			        System.out.println(" Envoi du mail ");
			        notificationService.envoyer(validation); 
			        
			        return EntityResponse.generateResponse("User Profile", HttpStatus.OK, "Nouveau Code Renvoyer a l'adresse "+user.getEmail()); 
		    	}
		    } 
	        
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
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("logout")
	@Transactional
	public ResponseEntity<Object> logout() {
		
		User usr = userService.findCurrentUser();
		
		if(usr==null) {
				
			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, " Aucun utilisateur connecté ou aucune session en cour ");
			
		}
			 
			 jwtRep.deleteByUser(usr); //jwtRep.deleteAll();//
			 System.out.println("Supp ");
			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, usr.getUsername()+" à été deconnecter avec succès" );
	    }
		
}
