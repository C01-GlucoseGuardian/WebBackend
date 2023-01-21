package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Rappresenta l'output delle funzioni di login.
 */
@JsonInclude(Include.NON_ABSENT)
public class TotpDto {

  private String totpKey;

  public TotpDto(String totpKey) {
    this.totpKey = totpKey;
  }

  public TotpDto() {
  }

  public String getTotpKey() {
    return totpKey;
  }

  public void setTotpKey(String totpKey) {
    this.totpKey = totpKey;
  }
}
