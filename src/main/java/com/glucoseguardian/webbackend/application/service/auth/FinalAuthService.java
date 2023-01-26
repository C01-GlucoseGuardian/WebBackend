package com.glucoseguardian.webbackend.application.service.auth;

import org.springframework.stereotype.Service;

@Service
public class FinalAuthService extends AbstractAuthService{
  public AuthServiceInterface getImplementation() {
    return new AuthServiceConcrete();
  }
}
