package com.mytv.api.firebase.token;

import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import java.util.concurrent.ExecutionException;
@Component
public class FirebaseTokenUtil {


    /**
     * Vérifie et décode le token Firebase JWT.
     * @param idToken Le token JWT reçu du client.
     * @return Un objet FirebaseToken si la vérification est réussie.
     * @throws ExecutionException Si une erreur survient lors de la vérification.
     * @throws InterruptedException Si le processus est interrompu.
     */
    public FirebaseToken verifyToken(String idToken) throws ExecutionException, InterruptedException {
       
    	// Utilise FirebaseAuth pour vérifier et décoder le token
        return FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
    }
}
