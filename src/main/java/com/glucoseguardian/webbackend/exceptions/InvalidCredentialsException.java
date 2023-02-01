package com.glucoseguardian.webbackend.exceptions;

import jakarta.security.auth.message.AuthException;

/**
 * Eccezione da utilizzare quando le credenziali (password, otp) non sono valide.
 */
public class InvalidCredentialsException extends Exception {

  public InvalidCredentialsException(String message) {
    super(message);
  }
}
