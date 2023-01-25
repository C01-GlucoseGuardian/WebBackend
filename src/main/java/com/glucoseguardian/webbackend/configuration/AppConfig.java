package com.glucoseguardian.webbackend.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

/**
 * JavaSpring configuration.
 */
@Configuration
public class AppConfig {
  /**
   * Usa PBKDF2 come raccomandato dal NIST con i valori consigliati da OWASP.
   */
  @Bean
  public PasswordEncoder encoder() {
    return new Pbkdf2PasswordEncoder("", 16, 600_000,
        SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
  }
}
