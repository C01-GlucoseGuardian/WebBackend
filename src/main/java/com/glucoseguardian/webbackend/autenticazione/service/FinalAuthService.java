package com.glucoseguardian.webbackend.autenticazione.service;

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