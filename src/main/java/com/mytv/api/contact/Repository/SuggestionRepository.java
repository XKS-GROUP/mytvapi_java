package com.mytv.api.contact.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.contact.model.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
