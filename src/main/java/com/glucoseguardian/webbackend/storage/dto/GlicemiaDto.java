package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * Rappresenta l'output dell'entità glicemia.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GlicemiaDto implements Serializable {

  private int livelloGlucosio;
  private String timestamp;

  public GlicemiaDto() {
  }

  public GlicemiaDto(int livelloGlucosio, String timestamp) {
    this.livelloGlucosio = livelloGlucosio;
    this.timestamp = timestamp;
  }

  public int getLivelloGlucosio() {
    return livelloGlucosio;
  }

  public void setLivelloGlucosio(int livelloGlucosio) {
    this.livelloGlucosio = livelloGlucosio;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
