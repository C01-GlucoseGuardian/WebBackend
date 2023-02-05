package com.glucoseguardian.webbackend.application.service.auth;

import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceConcrete;
import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link AbstractAuthService} che restituisce {@link AuthServiceConcrete}.
 */
@Service
public class FinalAuthService extends AbstractAuthService {
  @Autowired
  @Qualifier("authServiceConcrete")
  AuthServiceInterface authServiceService;

  @Override
  public AuthServiceInterface getImplementation() {
    return authServiceService;
  }
}
