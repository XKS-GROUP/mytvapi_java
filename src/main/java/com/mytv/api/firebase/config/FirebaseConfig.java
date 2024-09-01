package com.mytv.api.firebase.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
    	
        if (FirebaseApp.getApps().isEmpty()) {
        	
        	InputStream is = getClass().getClassLoader().getResourceAsStream("firebase.json");
            //FileInputStream serviceAccount = new FileInputStream(getClass().getClassLoader().getResourceAsStream("firebase.json"));

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .build();

            return FirebaseApp.initializeApp(options);
            
        } else {
        	
            return FirebaseApp.getInstance();
        }
    }
}
