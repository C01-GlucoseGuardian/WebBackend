package com.glucoseguardian.webbackend.configuration;

import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * JavaSpring configuration.
 */
@Configuration
@EnableAsync
public class AppConfig {

  @Autowired
  private UtenteDao utenteDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

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
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }
}
