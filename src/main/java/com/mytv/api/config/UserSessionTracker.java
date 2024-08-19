package com.mytv.api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionTracker {

	@Value("${session.limite}")
	private int limite;
	
	private Map<String, Map<String, Integer>> userIpSessions = new HashMap<>();

    public synchronized boolean addSession(String username, String ipAddress) {
        Map<String, Integer> ipSessions = userIpSessions.getOrDefault(username, new HashMap<>());

        int sessionCount = ipSessions.getOrDefault(ipAddress, 0);

        if (sessionCount >= limite) {
            return false; // Limite de 4 sessions par IP atteinte pour cet utilisateur
        }

        ipSessions.put(ipAddress, sessionCount + 1);
        userIpSessions.put(username, ipSessions);
        return true;
    }

    public synchronized void removeSession(String username, String ipAddress) {
        Map<String, Integer> ipSessions = userIpSessions.get(username);

        if (ipSessions != null) {
            int sessionCount = ipSessions.getOrDefault(ipAddress, 0);
            if (sessionCount > 0) {
                ipSessions.put(ipAddress, sessionCount - 1);
            }
            if (ipSessions.get(ipAddress) == 0) {
                ipSessions.remove(ipAddress);
            }
            if (ipSessions.isEmpty()) {
                userIpSessions.remove(username);
            }
        }
    }
}
