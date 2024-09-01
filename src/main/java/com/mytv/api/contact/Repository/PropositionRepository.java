package com.mytv.api.contact.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.contact.model.Proposition;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long>{

}
