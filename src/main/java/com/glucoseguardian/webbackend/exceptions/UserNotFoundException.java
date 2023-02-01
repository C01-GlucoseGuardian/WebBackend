package com.glucoseguardian.webbackend.exceptions;

import jakarta.security.auth.message.AuthException;

/**
 * Eccezione da utilizzare quando l'utente con quella specifica mail
 * o codice fiscale non è presente nel database.
 */
public class UserNotFoundException extends Exception {

  public UserNotFoundException(String message) {
    super(message);
  }
}
