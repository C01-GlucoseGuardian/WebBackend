package com.glucoseguardian.webbackend.configuration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.glucoseguardian.webbackend.autenticazione.service.JwtService;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Requestfilter basato su token jwt. Utilizza {@link JwtService} per la verifica dei token.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String BEARER = "Bearer ";

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    // Don't check jwt if user is already authenticated
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      // Get jwt from request
      Optional<String> jwt = getJwtFromRequest(request);
      jwt.ifPresent(token -> {
        String email = jwtService.getUsername(token);
        if (email != null) {
          try {
            Utente user = (Utente) this.userDetailsService.loadUserByUsername(email);
            if (jwtService.isTokenValid(token, user)) {
              // jwt is valid we can authenticate the user
              SecurityContextHolder
                  .getContext()
                  .setAuthentication(
                      new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities())
                  );
            }
          } catch (UsernameNotFoundException ignored) {
            // Do nothing
          }
        }
      });
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Ritorna il token jwt dall'header Authorization.
   */
  private Optional<String> getJwtFromRequest(@NonNull HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION);
    if (bearerToken == null || !bearerToken.startsWith(BEARER)) {
      return Optional.empty();
    }
    return Optional.of(bearerToken.substring(7));
  }
}
