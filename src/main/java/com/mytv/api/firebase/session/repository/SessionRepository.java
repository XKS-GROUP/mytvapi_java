package com.mytv.api.firebase.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.session.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
