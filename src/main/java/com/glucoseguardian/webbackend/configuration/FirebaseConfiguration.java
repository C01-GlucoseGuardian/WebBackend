package com.glucoseguardian.webbackend.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Firebase Configuration.
 */
@Configuration
public class FirebaseConfiguration {
  @Value("${firebase.credentials}")
  Resource firebaseCredentials;

  @Value("${firebase.checkValidity}")
  boolean checkValidity;

  @Bean
  GoogleCredentials googleCredentials() throws IOException {
    if (firebaseCredentials != null) {
      try (InputStream is = firebaseCredentials.getInputStream()) {
        return GoogleCredentials.fromStream(is);
      } catch (IOException ex) {
        if (checkValidity) {
          throw new FileNotFoundException("firebase credentials not available");
        }
      }
    } else if (checkValidity) {
      throw new IllegalArgumentException("No firebase properties set");
    }
    // Default credentials
    return GoogleCredentials.newBuilder().build();
  }

  @Bean
  FirebaseApp firebaseApp(GoogleCredentials credentials) {
    FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(credentials)
        .build();

    return FirebaseApp.initializeApp(options);
  }

  @Bean
  FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
    return FirebaseMessaging.getInstance(firebaseApp);
  }

}
