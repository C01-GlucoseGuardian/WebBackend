package com.glucoseguardian.webbackend.exceptions;

/**
 * Eccezione da utilizzare quando le credenziali (password, otp) non sono valide.
 */
public class InvalidCredentialsException extends Exception {

  public InvalidCredentialsException(String message) {
    super(message);
  }
}
