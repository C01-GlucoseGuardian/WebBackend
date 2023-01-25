package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class LoginOutputDto implements Serializable {

  private String idUtente;
  private int tipoUtente;
  private boolean needOtp;

  /**
   * Costruttore completo.
   */
  public LoginOutputDto(String idUtente, int tipoUtente, boolean needOtp) {
    this.idUtente = idUtente;
    this.tipoUtente = tipoUtente;
    this.needOtp = needOtp;
  }

  public LoginOutputDto() {
  }

  public String getIdUtente() {
    return idUtente;
  }

  public void setIdUtente(String idUtente) {
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
