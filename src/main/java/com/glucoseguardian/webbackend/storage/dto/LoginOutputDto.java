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
  private Integer tipoUtente;
  private Boolean needOtp;

  /**
   * Costruttore completo.
   */
  public LoginOutputDto(String idUtente, Integer tipoUtente, Boolean needOtp) {
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

  public Integer getTipoUtente() {
    return tipoUtente;
  }

  public void setTipoUtente(Integer tipoUtente) {
    this.tipoUtente = tipoUtente;
  }

  public Boolean isNeedOtp() {
    return needOtp;
  }

  public void setNeedOtp(Boolean needOtp) {
    this.needOtp = needOtp;
  }
}
