package com.mytv.api.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.contact.Repository.ContactRepository;
import com.mytv.api.contact.Repository.PropositionRepository;
import com.mytv.api.contact.Repository.SuggestionRepository;
import com.mytv.api.contact.model.Contact;
import com.mytv.api.contact.model.Proposition;
import com.mytv.api.contact.model.Suggestion;

@Service
public class ContactService {

    @Autowired
    private ContactRepository rep_contact;

    @Autowired
    private PropositionRepository rep_propo;
    
    @Autowired
    private SuggestionRepository rep_sugg;
    
    
    public Contact saveContact(Contact contact) {
    	
        return rep_contact.save(contact);
    }
    
    public Proposition saveProposition(Proposition propo) {
    	
        return rep_propo.save(propo);
    }
    
    
    public Suggestion saveSugg(Suggestion sugg) {
    	
        return rep_sugg.save(sugg);
    }
    
    public List<Contact> show(){
    	
    	return rep_contact.findAll();
    }
    
    public Page<Contact> show(Pageable p){
    	
    	return rep_contact.findAll(p);
    }
    
    public void delete(Long id) {
    	
    	rep_contact.deleteById(id);
    }
}
