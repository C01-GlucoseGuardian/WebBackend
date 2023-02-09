package com.glucoseguardian.webbackend.autenticazione.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementazione di {@link AbstractAuthService} che restituisce {@link AuthServiceStub}. Da
 * utilizzare a fini di test.
 */
@Service
public class TestAuthService extends AbstractAuthService {
  @Autowired
  private AuthServiceInterface authServiceService;

  @Override
  public AuthServiceInterface getImplementation() {
    return authServiceService;
  }
}
