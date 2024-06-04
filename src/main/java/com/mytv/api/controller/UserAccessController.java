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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.dto.NewPwdDTO;
import com.mytv.api.dto.PwdResetPwdDTO;
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

import io.swagger.v3.oas.annotations.media.Schema;
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

	//Se connecter
	@PostMapping("/login")
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

	//Fonction authentiication
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
	

	//Route Ajout admin
	@PostMapping("admin-register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequestDTO request){


		System.out.println(passwordEncoder.encode(request.getPassword()));
		if(userService.findByUsername(request.getUsername()) != null){

			return EntityResponse.generateResponse("le nom "+request.getUsername()+" existe deja ", HttpStatus.CONFLICT, "username : ce nom existe déja ");
		}
		else if (userService.findByUserEmail(request.getEmail()) !=null) {

			return EntityResponse.generateResponse("Cette adresse email "+request.getEmail()+"existe deja ", HttpStatus.CONFLICT, "email : cette adresse email existe déja");
		}
		else if (userService.findByUserPhone(request.getPhone()) !=null) {

			return EntityResponse.generateResponse("Ce numéro de telephone "+request.getPhone()+" existe deja ", HttpStatus.CONFLICT, " phone : ce numéro de téléphone existe déja");
		}
		else {

			request.setPassword(passwordEncoder.encode(request.getPassword()));
			
			return EntityResponse.generateResponse("Admin enregistre avec succès  ", HttpStatus.CREATED, userService.createUser(request));
		}


	}
	
	
	//Route Ajout Abonné
	@PostMapping("abonne-register")
	public ResponseEntity<Object> registerA(@Valid @RequestBody UserRegisterRequestDTO request){

		if(userService.findByUsername(request.getUsername()) != null){

			return EntityResponse.generateResponse("le nom "+request.getUsername()+" existe deja ", HttpStatus.BAD_REQUEST, "username : ce nom existe déja ");
		}
		else if (userService.findByUserEmail(request.getEmail()) !=null) {

			return EntityResponse.generateResponse("Cette adresse email "+request.getEmail()+"existe deja ", HttpStatus.BAD_REQUEST, "email : cette adresse email existe déja");
		}
		else if (userService.findByUserPhone(request.getPhone()) !=null) {

			return EntityResponse.generateResponse("Ce numéro de telephone "+request.getPhone()+" existe deja ", HttpStatus.BAD_REQUEST, " phone : ce numéro de téléphone existe déja");
		}

		else {

			request.setPassword(passwordEncoder.encode(request.getPassword()));
			
			return EntityResponse.generateResponse("Abonée enregistre avec succès", HttpStatus.CREATED, userService.createAbonne(request));
		}


	}

	//Activation un uttilisateur apres envoi du code
	@PostMapping("activation/{email}")
    public ResponseEntity<Object> activation(
    		@Schema(
    			    description = "code recu", 
    			    name = "code", 
    			    type = "string", 
    			    example = " 'code' : '7896541' ")
            @RequestBody Map<String, String> activation, @PathVariable String email) {

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
    		
    		Validation validation = validationService.lireEnFonctionDuCode(activation.get("code"));

	        if(Instant.now().isAfter(validation.getExpiration())){
	        	
	        	return EntityResponse.generateResponse("Utilisateur Valide", HttpStatus.BAD_REQUEST, "Ce code a expiré, veuillez faire une nouvelle demande d envoi ");
	        }
	        
	        userService.activation(activation);
	        return EntityResponse.generateResponse("Activation", HttpStatus.OK, "Utilisateur activé avec succès");
    	}

    }
	
	@Transactional
	public void suppValidation(Long id) {
		
		validationRepository.deleteById(id) ;
	}


	/*
	 * 
	 * 	Renvoi du code d'activation après expiration
	 * 
	 */
	@PostMapping("newcode/{email}")
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


			        	//String cd = validationRepository.findByUtilisateurId(user.getId()).getCode();

			        	//validationRepository.delete(validationRepository.findByUtilisateurId(user.getId()));
			        	//validationRepository.deleteById(validationRepository.findByUtilisateurId(user.getId()).getId());
			        	
			        	//suppValidation(validationRepository.findByUtilisateurId(user.getId()).getId());
			        	validationService.updateByid(validationRepository.findByUtilisateurId(user.getId()).getId(), validation);
			        	notificationService.envoyer(validation);
			        	return EntityResponse.generateResponse("User Profile", HttpStatus.OK, "Nouveau Code Renvoyer a l'adresse "+user.getEmail());
			        }
			        
			        
			        //validationRepository.delete(validationRepository.findByUtilisateurId(user.getId()));
			        //System.out.println(" Envoi du mail ");
			        //notificationService.envoyer(validation);
			        
			        validationRepository.save(validation);
			        notificationService.envoyer(validation);
			        return EntityResponse.generateResponse("User Profile", HttpStatus.OK, "Nouveau Code Renvoyer a l'adresse "+user.getEmail());
		    	}
		    }

    }

	//Mot de passe oublié
	
	
	/*
	 * Envoi du code de reinitialisation du mod de passe 
	 * 
	 */
	
	@PostMapping("password/send/resetcode")
    public ResponseEntity<Object> sendResetPawd(@Valid @RequestBody PwdResetPwdDTO email) {


		    if(userService.findByUserEmail(email.getEmail()) == null ) {

		    	return EntityResponse.generateResponse("Utilisateur Introuvable", HttpStatus.CONFLICT, "Aucun utilisateur enregistré sous "+email.getEmail());

		    }

		    else {

		    	    User user = userService.findByUserEmail(email.getEmail());

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

			        	validationService.updateByid(validationRepository.findByUtilisateurId(user.getId()).getId(), validation);
			        	
			        	notificationService.envoyerPour(validation, "Code de validation");
			        	
			        	return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, "Un code à été envoyer à l'adresse "+user.getEmail());
			        }
			        validationRepository.save(validation);
			        
			        notificationService.envoyerPour(validation, "Code de validation");

			        return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, "Un code à été envoyer à l'adresse "+user.getEmail());
		    }

    }
	
	
	/*
	 * 
	 * Mot de passe perdu, utiliser une fois que l utilisateur aura cliqué sur le lien envoyer par mail
	 * 
	 */
	
	//MAJ du password
	@PostMapping("password/reset/{code}")
    public ResponseEntity<Object> resetpwd(@Valid @RequestBody NewPwdDTO pwd, @PathVariable Map<String, String> code) {

			if(code.isEmpty()) { 
				
				return EntityResponse.generateResponse("Code vide", HttpStatus.BAD_REQUEST, "le code ne puis être vide, il represente le code envoyer par email");
			}
		    else {
		    	
		    		
		    	    Validation validation = validationService.lireEnFonctionDuCode(code.get("code"));
		    	    System.out.println("Le code est "+code.get("code"));
		    	    String email = validationRepository.findByCode(code.get("code")).get().getUtilisateur().getEmail();
		    	    
		    	    
		    	    //test si l user existe vraiment
		    	    if(userService.findByUserEmail(email) ==null ) {

				    	return EntityResponse.generateResponse("Utilisateur Introuvable", HttpStatus.CONFLICT, "Aucun utilisateur enregistré sous "+email.toString());

				    }
		    	    else if(Instant.now().isAfter(validation.getExpiration())){
			        	
			        	return EntityResponse.generateResponse("Utilisateur Valide", HttpStatus.CONFLICT, "Ce code a expiré, veuillez faire une nouvelle demande d envoi ");
			        }
		    	    else  {
		    	    	
			    	    User user = userService.findByUserEmail(email);

			    	    
			    	    user.setPassword(passwordEncoder.encode(pwd.getMdp()));

			    	    userService.updateByid(user.getId(), user);

			    	    
				        return EntityResponse.generateResponse("SUCCES", HttpStatus.OK, "Le mot de passe a été changé avec succès pour " 
				                              +user.getEmail()+notificationService.sendConfirmationMdpSucces(validation, "Modification de votre mot de passe"));
				        

		    	    }
		    	 }

    }



	//Se deconnecter
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("logout")
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

	//Se deconnecter de tous les periphériques
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("logout/allDevice")
	@Transactional
	public ResponseEntity<Object> logoutAll() {

		User usr = userService.findCurrentUser();

		if(usr==null) {

			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, " Aucun utilisateur connecté ou aucune session en cour ");

		}

			 jwtRep.deleteAll();
			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, usr.getUsername()+" à été deconnecter avec succès" );
	    }


	/*
	 * GESTION DES COMMENTAIRES
	 */
	
	/*
	 * GESTION DES LIKES
	 */
	
	/*
	 * GESTION DES FAVORIS
	 */

}
