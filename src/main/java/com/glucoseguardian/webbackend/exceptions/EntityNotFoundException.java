package com.glucoseguardian.webbackend.exceptions;

/**
 * Eccezione da utilizzare quando l'entity richiesta (che non è un utente) non è presente
 * nel database.
 */
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException(String message) {
    super(message);
  }
}
