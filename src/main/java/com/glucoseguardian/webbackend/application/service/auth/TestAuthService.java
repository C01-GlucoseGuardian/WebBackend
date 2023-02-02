package com.glucoseguardian.webbackend.application.service.auth;

import org.springframework.stereotype.Service;


/**
 * Implementazione di {@link AbstractAuthService} che restituisce {@link AuthServiceStub}. Da
 * utilizzare a fini di test.
 */
@Service
public class TestAuthService extends AbstractAuthService{
  public AuthServiceInterface getImplementation() {
    return new AuthServiceConcrete();
  }
}
