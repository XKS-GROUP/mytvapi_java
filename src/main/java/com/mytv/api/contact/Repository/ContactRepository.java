package com.mytv.api.contact.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.contact.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
