package com.glucoseguardian.webbackend.exceptions;

/**
 * Eccezione da utilizzare quando l'utente con quella specifica mail o codice fiscale non è presente
 * nel database.
 */
public class UserNotFoundException extends EntityNotFoundException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
