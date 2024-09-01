package com.mytv.api.util.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mytv.api.contact.model.Contact;
import com.mytv.api.contact.model.Proposition;
import com.mytv.api.contact.model.Suggestion;
import com.mytv.api.dto.EmailDTO;
import com.mytv.api.firebase.model.Otp;
import com.mytv.api.user.model.Validation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificationService {
	
    JavaMailSender javaMailSender;
    
    
    public void envoyer(Validation validation) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
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
    
    //Pour l'envoie des OTP
    public void envoyerOTP(EmailDTO email, String code ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
        message.setTo(email.getValue());
        message.setSubject("Votre code OTP");

        String texte = String.format(
                "Bonjour %s, Votre code OTP est le suivant %s; ce code expirera dans 10 minute",
                email.getValue(),
                code
                );

        message.setText(texte);

        javaMailSender.send(message);
    }
    
    public void envoyerOTP(Otp otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
        message.setTo(otp.getEmail());
        message.setSubject("Votre code OTP");

        String texte = String.format(
                "Bonjour %s, Votre code OTP est le suivant %s; ce code expirera dans 10 minute",
                otp.getEmail(),
                otp.getOtp()
                );

        message.setText(texte);

        javaMailSender.send(message);
    }

    public void envoyerPour(Validation validation, String obj) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
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
    
    public void envoyerContact(String email, Contact contact) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
        message.setTo(email);
        message.setSubject(contact.getSujet());

        String texte = String.format(
                contact.getMessage()
                );

        message.setText(texte);

        javaMailSender.send(message);
        
    }
    
    public void envoyerSuggestion(String email, Suggestion sugg) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
        message.setTo(email);
        message.setSubject("Suggestion");

        String texte = String.format(
        		
                sugg.getSuggestion()+"\n"
                );

        message.setText(texte);

        javaMailSender.send(message);
        
    }
    
    public void envoyerProposition(String email, Proposition pro) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
        message.setTo(email);
        message.setSubject("Proposition");

        String texte = String.format(
        		
        		pro.getNom()+"\n"+
                pro.getDescription()+"\n"+
                pro.getFlux()+"\n"+
                pro.getNom_responable()+"\n"+
                pro.getEmail_responable()
                );

        message.setText(texte);

        javaMailSender.send(message);
        
    }
    
    public String sendConfirmationMdpSucces(Validation validation, String obj) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject(obj.toString());

        String texte = String.format(
                "Bonjour %s, Votre mot de passe à été modifié avec succès",
                validation.getUtilisateur().getUsername()
                
                );

        message.setText(texte);

        javaMailSender.send(message);
        return "";
    }
    
    //Pour renvoyer l'adresse de reinitialisation de mot de passe
    public void sendUriResetPWD(Validation validation, String obj, String url) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@galsen.com");
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
