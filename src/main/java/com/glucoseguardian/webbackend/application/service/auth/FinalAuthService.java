package com.glucoseguardian.webbackend.application.service.auth;

import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link AbstractAuthService} che restituisce {@link AuthServiceConcrete}.
 */
@Service
public class FinalAuthService extends AbstractAuthService{
  public AuthServiceInterface getImplementation() {
    return new AuthServiceConcrete();
  }
}
