package com.mytv.api.firebase.session.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseToken;
import com.mytv.api.firebase.session.model.Session;
import com.mytv.api.firebase.session.repository.SessionRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class SessionService {

	@Autowired
	SessionRepository sessionRep;
	
	private static final int MAX_SESSIONS = 4;
	
	private final Map<String, Map<String, String>> userSessions = new HashMap<>();
	
	public Session create(FirebaseToken t) {
		
		Session s = new Session();
		
		//s.setIpAddress(t.getClaims().get(s));
		
		return sessionRep.save(s);
	}
	
	public void delete(Long id) {
		
		 sessionRep.deleteById(id);
	}
	 

    public boolean isSessionLimitExceeded(String userId, String ipAddress) {
        Map<String, String> sessions = userSessions.getOrDefault(userId, new HashMap<>());
        return sessions.size() >= 4;
    }

    public void addSession(String userId, String token, String ipAddress) {
        userSessions.computeIfAbsent(userId, k -> new HashMap<>()).put(ipAddress, token);
    }

    public void removeSession(String userId, String ipAddress) {
        Map<String, String> sessions = userSessions.get(userId);
        if (sessions != null) {
            sessions.remove(ipAddress);
            if (sessions.isEmpty()) {
                userSessions.remove(userId);
            }
        }
    }
}
