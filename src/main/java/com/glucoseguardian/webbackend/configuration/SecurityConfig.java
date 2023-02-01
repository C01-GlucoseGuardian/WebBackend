package com.glucoseguardian.webbackend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JavaSpring configuration.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


  @Autowired
  private JwtAuthenticationFilter jwtAuthFilter;
  @Autowired
  private AuthenticationProvider authenticationProvider;


  /**
   * Security Policy basata su {@link JwtAuthenticationFilter}.
   */
  @Bean
  public @NonNull SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http)
      throws Exception {
    http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
