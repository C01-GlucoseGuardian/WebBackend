package com.glucoseguardian.webbackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

/**
 * Password encoder Configuration.
 */
@Configuration
public class PasswordConfiguration {
  @Value("${auth.pbkdf.iterations}")
  private int pbkdfIterations;

  @Value("${auth.pbkdf.secret}")
  private String pbkdfSecret;

  @Value("${auth.pbkdf.saltLength}")
  private int pbkdfSaltLength;

  /**
   * Usa PBKDF2 come raccomandato dal NIST con i valori configurati in application properties.
   */
  @Bean
  public PasswordEncoder encoder() {
    return new Pbkdf2PasswordEncoder(pbkdfSecret, pbkdfSaltLength, pbkdfIterations,
        SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
  }

}
