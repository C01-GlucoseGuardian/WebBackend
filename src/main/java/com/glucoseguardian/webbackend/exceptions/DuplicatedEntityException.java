package com.glucoseguardian.webbackend.exceptions;

/**
 * Eccezione da utilizzare quando un enity con quel specifico id è già presente nel database.
 */
public class DuplicatedEntityException extends Exception {

  public DuplicatedEntityException(String message) {
    super(message);
  }

}