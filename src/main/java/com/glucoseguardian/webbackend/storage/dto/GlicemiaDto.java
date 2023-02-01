package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * Rappresenta l'output dell'entità glicemia.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GlicemiaDto extends RisultatoDto implements Serializable {

  private Integer livelloGlucosio;
  private String timestamp;

  public GlicemiaDto() {
  }

  public GlicemiaDto(Integer livelloGlucosio, String timestamp) {
    this.livelloGlucosio = livelloGlucosio;
    this.timestamp = timestamp;
  }

  public Integer getLivelloGlucosio() {
    return livelloGlucosio;
  }

  public void setLivelloGlucosio(Integer livelloGlucosio) {
    this.livelloGlucosio = livelloGlucosio;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
