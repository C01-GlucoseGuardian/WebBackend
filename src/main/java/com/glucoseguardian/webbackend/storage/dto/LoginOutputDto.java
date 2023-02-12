package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class LoginOutputDto extends RisultatoDto implements Serializable {

  private String idUtente;
  private Integer tipoUtente;
  private Boolean needOtp;
  private String token;

  /**
   * Costruttore completo.
   */
  public LoginOutputDto(String idUtente, Integer tipoUtente, Boolean needOtp, String token) {
    this.idUtente = idUtente;
    this.tipoUtente = tipoUtente;
    this.needOtp = needOtp;
    this.token = token;
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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LoginOutputDto that)) {
      return false;
    }

    return new EqualsBuilder().append(idUtente, that.idUtente).append(tipoUtente, that.tipoUtente)
        .append(needOtp, that.needOtp).isEquals();
  }
}
