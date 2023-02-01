package com.glucoseguardian.webbackend.application.service.auth;

/**
 * Classe astratta per Auth Service.
 */
public abstract class AbstractAuthService {

  public abstract AuthServiceInterface getImplementation();
}
