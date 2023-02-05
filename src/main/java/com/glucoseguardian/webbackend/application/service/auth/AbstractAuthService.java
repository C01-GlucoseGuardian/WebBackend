package com.glucoseguardian.webbackend.application.service.auth;

import com.glucoseguardian.webbackend.autenticazione.service.AuthServiceInterface;

/**
 * Classe astratta per Auth Service.
 */
public abstract class AbstractAuthService {

  public abstract AuthServiceInterface getImplementation();
}
