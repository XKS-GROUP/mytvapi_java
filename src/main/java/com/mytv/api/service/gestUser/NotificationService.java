package com.mytv.api.service.gestUser;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.Validation;

import lombok.AllArgsConstructor;

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
    
    
    //Pour renvoyer l'adresse de reinitialisation de mot de passe
    public void sendUriResetPWD(Validation validation, String obj, String url) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("test@smeth-dev.site");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject(obj.toString());

        String ad = url+"/"+validation.getCode();
        String texte = String.format(
                "Bonjour %s, votre adresse unique de réinitialisation de mot de passe est  %s; celle-ci expirera dans 10 minute",
                validation.getUtilisateur().getUsername(),
                ad

                );

        message.setText(texte);

        javaMailSender.send(message);
    }
}
