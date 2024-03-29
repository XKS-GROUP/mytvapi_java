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

	//Fonction auth
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

		if(userService.findByUsername(request.getUsername()) != null){

			return EntityResponse.generateResponse("Ce nom existe deja existe deja "+request.getUsername(), HttpStatus.CONFLICT, "");
		}
		else if (userService.findByUserEmail(request.getEmail()) !=null) {

			return EntityResponse.generateResponse("Cette adresse email existe deja "+request.getEmail(), HttpStatus.CONFLICT, "");
		}
		else if (userService.findByUserPhone(request.getEmail()) !=null) {

			return EntityResponse.generateResponse("Ce numéro de telephone existe deja "+request.getEmail(), HttpStatus.CONFLICT, "");
		}
		else {

			request.setPassword(passwordEncoder.encode(request.getPassword()));
			return EntityResponse.generateResponse("Utilisateur enregistre  ", HttpStatus.OK, userService.createUser(request));
		}


	}

	//Route Ajout Abonné
	@PostMapping("abonne-register")
	public ResponseEntity<Object> registerA(@Valid @RequestBody UserRegisterRequestDTO request){

		if(userService.findByUsername(request.getUsername()) != null){

			return EntityResponse.generateResponse("Ce nom existe deja existe deja "+request.getUsername(), HttpStatus.CONFLICT, "");
		}
		else if (userService.findByUserEmail(request.getEmail()) !=null) {

			return EntityResponse.generateResponse("Cette adresse email existe deja "+request.getEmail(), HttpStatus.CONFLICT, "");
		}
		else if (userService.findByUserPhone(request.getEmail()) !=null) {

			return EntityResponse.generateResponse("Ce numéro de telephone existe deja "+request.getEmail(), HttpStatus.CONFLICT, "");
		}

		else {

			request.setPassword(passwordEncoder.encode(request.getPassword()));
			return EntityResponse.generateResponse("Utilisateur enregistre en tant que "+request.getRoleList(), HttpStatus.OK, userService.createAbonne(request));
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

	//Renvoi du code d'activation après expiration
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
	//Envoi du code
	@PostMapping("pwdLostSendCode/{email}")
    public ResponseEntity<Object> pwdnewcode(@PathVariable String email) {


		    if(email.isEmpty()) {

		    	return EntityResponse.generateResponse("Champ email vide", HttpStatus.BAD_REQUEST, "le champ email ne puis être vide");
		    }


		    else if(userService.findByUserEmail(email) ==null ) {

		    	return EntityResponse.generateResponse("Utilisateur Introuvable", HttpStatus.BAD_REQUEST, "Aucun utilisateur enregistré sous "+email.toString());

		    }

		    else {


		    	    User user = userService.findByUserEmail(email);

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
			        validationRepository.save(validation);
			        
			        notificationService.envoyerPour(validation, "Reinitialisation du mot de passe");

			        return EntityResponse.generateResponse("Mot de passe oublié", HttpStatus.OK, "Un code Renvoyer a l'adresse "+user.getEmail());
		    }

    }

	//MAJ du password
	@PostMapping("resetpassword/{code}")
    public ResponseEntity<Object> resetpwd(@RequestBody String pwd, @PathVariable Map<String, String> code) {

			if(code.isEmpty()) {
				System.out.println("je sui dedans biwware,ent 888");
				return EntityResponse.generateResponse("Code vide", HttpStatus.BAD_REQUEST, "le code ne puis être vide, il represente le code envoyer par email");
			}
			else if(pwd.isEmpty()) {
				System.out.println("je sui dedans biwware,ent 999");
		    	return EntityResponse.generateResponse("Champ mot de passe vide", HttpStatus.BAD_REQUEST, "le nouveau mot de passe ne puis être vide");
		    }
		    else {
		    	
		    		
		    	    Validation validation = validationService.lireEnFonctionDuCode(code.get("code"));
		    	    System.out.println("Le code est "+code.get("code"));
		    	    String email = validationRepository.findByCode(code.get("code")).get().getUtilisateur().getEmail();
		    	    
		    	    
		    	    //test si l user existe vraiment
		    	    if(userService.findByUserEmail(email) ==null ) {

				    	return EntityResponse.generateResponse("Utilisateur Introuvable", HttpStatus.BAD_REQUEST, "Aucun utilisateur enregistré sous "+email.toString());

				    }
		    	    else if(Instant.now().isAfter(validation.getExpiration())){
			        	
			        	return EntityResponse.generateResponse("Utilisateur Valide", HttpStatus.BAD_REQUEST, "Ce code a expiré, veuillez faire une nouvelle demande d envoi ");
			        }
		    	    else  {
			    	    User user = userService.findByUserEmail(email);

			    	    user.setPassword(passwordEncoder.encode(pwd));

			    	    userService.updateByid(user.getId(), user);

				        //notificationService.envoyerPour(validation, "Demande de changement de mot de passe");

				        return EntityResponse.generateResponse("User password", HttpStatus.OK, "Le mot de passe a été changé avec succès pour " +user.getEmail());

		    	    }
		    	 }

    }


	//Affiche l utilisateur actuelle et ses info profile
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("profile")
	public ResponseEntity<Object> retrieveUserProfile(){
		return EntityResponse.generateResponse("User Profile", HttpStatus.OK, userService.findCurrentUser());
	}

	//Se deconnecter
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

	//Se deconnecter de tous les periphériques
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("logoutAllDevice")
	@Transactional
	public ResponseEntity<Object> logoutAll() {

		User usr = userService.findCurrentUser();

		if(usr==null) {

			 return EntityResponse.generateResponse("Deconexion", HttpStatus.OK, " Aucun utilisateur connecté ou aucune session en cour ");

		}

			 jwtRep.deleteAll();
			 System.out.println("Supp ");
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
