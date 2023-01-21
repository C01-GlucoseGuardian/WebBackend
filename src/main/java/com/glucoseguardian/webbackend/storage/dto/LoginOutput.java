package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class LoginOutput {

  private long idUtente;
  private int tipoUtente;
  private boolean needOtp;

  /**
   * Costruttore completo.
   */
  public LoginOutput(long idUtente, int tipoUtente, boolean needOtp) {
    this.idUtente = idUtente;
    this.tipoUtente = tipoUtente;
    this.needOtp = needOtp;
  }

  public LoginOutput() {
  }

  public long getIdUtente() {
    return idUtente;
  }

  public void setIdUtente(long idUtente) {
    this.idUtente = idUtente;
  }

  public int getTipoUtente() {
    return tipoUtente;
  }

  public void setTipoUtente(int tipoUtente) {
    this.tipoUtente = tipoUtente;
  }

  public boolean isNeedOtp() {
    return needOtp;
  }

  public void setNeedOtp(boolean needOtp) {
    this.needOtp = needOtp;
  }
}
