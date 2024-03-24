package com.mytv.api.service.gestUser;


import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.Validation;

@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void envoyer(Validation validation) {
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("test@smeth-dev.site");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("Votre code d'activation");

        String texte = String.format(
                "Bonjour %s, Votre code d'action est %s; ce code expirera dans 10 minute",
                validation.getUtilisateur().getUsername(),
                validation.getCode()
                
                );
        
        message.setText(texte);

        javaMailSender.send(message);
    }
    
    public void envoyerPour(Validation validation, String obj) {
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("test@smeth-dev.site");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject(obj.toString());

        String texte = String.format(
                "Bonjour %s, Votre code d'action est %s; ce code expirera dans 10 minute",
                validation.getUtilisateur().getUsername(),
                validation.getCode()
                
                );
        
        message.setText(texte);

        javaMailSender.send(message);
    }
}
