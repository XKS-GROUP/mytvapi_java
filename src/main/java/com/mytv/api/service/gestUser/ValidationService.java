package com.mytv.api.service.gestUser;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.User;
import com.mytv.api.model.gestUser.Validation;
import com.mytv.api.repository.ValidationRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ValidationService {

	@Autowired
    private ValidationRepository validationRepository;
	
	@Autowired
    private NotificationService notificationService;

    public void enregistrer(User utilisateur) {
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
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
    }
    
	public Validation updateByid(Long id, Validation v) {

		Validation old = validationRepository.findById(id).get();
		old = v;
		old.setId(id);

		return validationRepository.save(old);
	}
    
    public Validation lireEnFonctionDuCode(String code) {
        return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }


}
