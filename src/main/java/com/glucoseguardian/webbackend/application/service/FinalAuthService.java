package com.glucoseguardian.webbackend.application.service;

public class FinalAuthService {
  public AuthServiceInterface getAuthServiceImplementation() {
    return new AuthService();
  }
}
