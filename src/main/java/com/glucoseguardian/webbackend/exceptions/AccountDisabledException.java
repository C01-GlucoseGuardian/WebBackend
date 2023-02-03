package com.glucoseguardian.webbackend.exceptions;

/**
 * Eccezione da utilizzare quando l'account dell'utente non è abilitato.
 */
public class AccountDisabledException extends Exception {
  public AccountDisabledException(String message) {
    super(message);
  }
}