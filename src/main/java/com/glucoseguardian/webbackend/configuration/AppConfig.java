package com.glucoseguardian.webbackend.configuration;

import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JavaSpring configuration.
 */
@Configuration
@EnableWebSecurity
public class AppConfig {

  private final @NonNull UtenteDao utenteDao = new UtenteDao();

  private final @NonNull JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter();

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

  /**
   * Security Policy basata su {@link JwtAuthenticationFilter}.
   */
  @Bean
  public @NonNull SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http)
      throws Exception {
    // TODO: Customize security policy
    http
        .csrf().disable()
        .authorizeHttpRequests().anyRequest().authenticated().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
