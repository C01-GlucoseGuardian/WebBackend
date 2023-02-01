package com.glucoseguardian.webbackend.configuration;

import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

/**
 * JavaSpring configuration.
 */
@Configuration
public class AppConfig {

  @Autowired
  private UtenteDao utenteDao;

  /**
   * Usa PBKDF2 come raccomandato dal NIST con i valori consigliati da OWASP.
   */
  @Bean
  public PasswordEncoder encoder() {
    return new Pbkdf2PasswordEncoder("", 16, 600_000,
        SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
  }

  @Bean
  public @NonNull UserDetailsService userDetailsService() throws UsernameNotFoundException {
    return username -> utenteDao.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
  }

  /**
   * Authentication Provider.
   */
  @Bean
  public @NonNull AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }
}
