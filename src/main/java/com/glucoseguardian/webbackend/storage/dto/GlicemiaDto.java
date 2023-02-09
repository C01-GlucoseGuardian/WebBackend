package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glucoseguardian.webbackend.storage.entity.Glicemia;
import java.io.Serializable;
import org.apache.commons.lang3.Validate;

/**
 * Rappresenta l'output dell'entità glicemia.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GlicemiaDto extends RisultatoDto implements Serializable {

  private Integer livelloGlucosio;
  private Long timestamp;

  public GlicemiaDto() {
  }

  public GlicemiaDto(Integer livelloGlucosio, Long timestamp) {
    this.livelloGlucosio = livelloGlucosio;
    this.timestamp = timestamp;
  }

  public Integer getLivelloGlucosio() {
    return livelloGlucosio;
  }

  public void setLivelloGlucosio(Integer livelloGlucosio) {
    this.livelloGlucosio = livelloGlucosio;
  }

  public static GlicemiaDto valueOf(Glicemia glicemia) {
    return new GlicemiaDto(glicemia.getLivelloGlucosio(), glicemia.getDataOra().getTime());
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public void validate() throws IllegalArgumentException {
    Validate.notNull(livelloGlucosio, "il livello di glucosio non può essere assente");
    Validate.notNull(timestamp, "l'ora non può essere assente");
  }
}
