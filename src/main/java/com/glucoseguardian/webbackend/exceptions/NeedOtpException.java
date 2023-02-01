package com.glucoseguardian.webbackend.exceptions;

/**
 * Eccezione da utilizzare quando è richiesto l'inserimento dell'otp.
 */
public class NeedOtpException extends Exception {

  public NeedOtpException(String message) {
    super(message);
  }
}
