package com.mytv.api.firebase.session;

import java.util.HashMap;
import java.util.Map;

public class FirebaseSessionManager {
    
    private final Map<String, Map<String, String>> userSessions = new HashMap<>();

    public boolean isSessionLimitExceeded(String userId, String ipAddress) {
        Map<String, String> sessions = userSessions.getOrDefault(userId, new HashMap<>());
        return sessions.size() >= 4; // Limite de 4 sessions par utilisateur
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
