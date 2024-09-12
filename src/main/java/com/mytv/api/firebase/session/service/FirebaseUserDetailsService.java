package com.mytv.api.firebase.session.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class FirebaseUserDetailsService {

    public FirebaseToken getUserDetailsFromToken(String token) throws Exception {
        return FirebaseAuth.getInstance().verifyIdToken(token);
    }
}
