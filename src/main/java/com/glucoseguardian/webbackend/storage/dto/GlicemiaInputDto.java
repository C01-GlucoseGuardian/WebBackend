package com.glucoseguardian.webbackend.storage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * Rappresenta l'input dell'entità glicemia.
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GlicemiaInputDto extends RisultatoDto implements Serializable {

  private String idPaziente;
  private Long start;

  private Long end;

  public GlicemiaInputDto() {
  }

  /**
   * Costruttore di default di glicemia input dto.
   */
  public GlicemiaInputDto(String idPaziente, Long start, Long end) {
    this.idPaziente = idPaziente;
    this.start = start;
    this.end = end;
  }

  public String getIdPaziente() {
    return idPaziente;
  }

  public void setIdPaziente(String idPaziente) {
    this.idPaziente = idPaziente;
  }

  public Long getStart() {
    return start;
  }

  public void setStart(Long start) {
    this.start = start;
  }

  public Long getEnd() {
    return end;
  }

  public void setEnd(Long end) {
    this.end = end;
  }
}
